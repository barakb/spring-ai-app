package com.falkordb.springai.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.FalkorDBVectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class AppController {
    private static Logger logger = LoggerFactory.getLogger(AppController.class);
    private final FalkorDBVectorStore vectorStore;
    private final ChatClient chatClient;
    private final String documentsDirectoryPath;
    private static final String SYSTEM_PROMPT = """
            You are a helpful assistant who responds to user queries primarily based on the documents section below
            
            Documents:
            
            {documents}
            """;

    public AppController(VectorStore vectorStore,
                         ChatClient.Builder chatClientBuilder,
                         @Value("${documents.directory.path}") String documentsDirectoryPath,
                         EmbeddingModel embeddingModel
    ) {
        this.vectorStore = (FalkorDBVectorStore) vectorStore;
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
        this.documentsDirectoryPath = documentsDirectoryPath;
        logger.info("using embedding model: {}", embeddingModel);
        logger.info("store={}", vectorStore);
        logger.info("documents.directory.path={}", documentsDirectoryPath);
        logger.info("use openAPI to index and query documents: http://localhost:8080/swagger-ui/index.html");
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {

        var relatedDocuments = vectorStore.similaritySearch(
                SearchRequest
                        .defaults()
                        .withQuery(message)
                        .withTopK(5)
                        .withSimilarityThreshold(0.5));

        var relatedContent = relatedDocuments.stream().
                map(Document::getContent)
                .collect(Collectors.joining(System.lineSeparator()));

        return chatClient
                .prompt()
                .system(spec -> spec.text(SYSTEM_PROMPT).param("documents", relatedContent))
                .user(message)
                .call()
                .content();
    }

    // endpoint to index a file
    @PostMapping("/index")
    public void indexDocument(@AuthenticationPrincipal UserOfCustomer userOfCustomer) throws IOException {

        vectorStore.deleteGraph();
        vectorStore.createIndexes();
        Path directory = Paths.get(documentsDirectoryPath, userOfCustomer.getTenantId());
        logger.info("indexing documents in directory: {}", directory);
        try (Stream<Path> paths = Files.list(directory)) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                                try {
                                    logger.info("Processing file: {}", path);
                                    var resource = new UrlResource(path.toUri());
                                    var pdfReader = new PagePdfDocumentReader(resource,
                                            PdfDocumentReaderConfig.builder()
                                                    .withPageTopMargin(0)
                                                    .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                                            .withNumberOfTopTextLinesToDelete(0)
                                                            .build())
                                                    .withPagesPerDocument(1)
                                                    .build());
                                    var textSplitter = new TokenTextSplitter();
                                    var docs = textSplitter.apply(pdfReader.read());
                                    var ids = docs.stream().map(Document::getId).toList();
                                    vectorStore.accept(docs);
/*                                    var quary = "Who is David?";
//                                    List<Document> res = vectorStore.similaritySearch(quary);
                                    List<Document> res = vectorStore.similaritySearch(
                                            SearchRequest
                                                    .defaults()
                                                    .withQuery(quary)
                                                    .withTopK(5)
                                                    .withSimilarityThreshold(0.5));
                                    logger.info("got n={} document related to the query={}", res.size(), quary);
//                                    vectorStore.delete(ids);

 */
                                } catch (MalformedURLException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                    );
        }
    }

    // endpoint for deleting all document
    @DeleteMapping("/documents")
    public void deleteAllDocuments() {
        FalkorDBVectorStore falkorDBVectorStore = this.vectorStore;
        falkorDBVectorStore.getDriver().graphQuery(FalkorDBVectorStore.SPRING_AI_VECTOR_STORE_KEY, "MATCH (n) DELETE n");
    }

    public static byte[] floatArrayToLittleEndianByteArray(float[] floatArray) {
        ByteBuffer buffer = ByteBuffer.allocate(4 * floatArray.length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        for (float f : floatArray) {
            buffer.putFloat(f);
        }

        return buffer.array();
    }

    @PostMapping("to-binary")
    public String writeBinary() throws IOException {
        float[] values = {0.1f, 0.2f, 0.3f};
        byte[] binaryData = floatArrayToLittleEndianByteArray(values);
        StringBuilder sb = new StringBuilder();
        for (byte b : binaryData) {
            sb.append(String.format("\\x%02X", b));
        }
        return sb.toString();
    }

}
