https://www.youtube.com/watch?v=TPcqBuxl5B8&ab_channel=CodeWiz

#### docker

https://hub.docker.com/r/falkordb/falkordb/
https://docs.falkordb.com/cypher/indexing.html#vector-indexing

```bash
docker run -p 6379:6379 -p 3000:3000 -it --rm falkordb/falkordb:edge

```
`GRAPH.QUERY barak "CREATE VECTOR INDEX FOR (p:Product) ON (p.description) OPTIONS {dimension:3, similarityFunction:'euclidean'}"`
`GRAPH.QUERY barak "CREATE (p: Product {description: vecf32([2.1, 0.82, 1.3])})"`
`GRAPH.QUERY barak "CALL db.idx.vector.queryNodes('Product','description', 10, vecf32([2.1, 0.82, 1.3])) YIELD node"`
`GRAPH.QUERY barak "DROP VECTOR INDEX FOR (p:Product) ON (p.description)"`


`GRAPH.QUERY spring-ai "CREATE VECTOR INDEX FOR (p:RAGNode) ON (p.vector) OPTIONS {dimension:3, similarityFunction:'euclidean'}"`
`GRAPH.QUERY spring-ai "CREATE (RAGNode {vector: vecf32([2.1, 0.82, 1.3]) , name: 'barak'})"`
`GRAPH.QUERY spring-ai "CREATE (RAGNode {vector: vecf32([2.1, 0.82, 1.3]) , name: 'shaked'})"`
`GRAPH.QUERY spring-ai "CALL db.idx.vector.queryNodes('RAGNode','vector', 10, vecf32([2.1, 0.82, 1.3])) YIELD node, score`



GRAPH.QUERY spring-ai "CREATE VECTOR INDEX FOR (p:Document) ON (p.embedding) OPTIONS {dimension:3, similarityFunction:'euclidean'}"
GRAPH.QUERY spring-ai "CREATE INDEX FOR (p:Document) ON (p.id)"
GRAPH.QUERY spring-ai "CREATE (p:Document {embedding: vecf32([2.1, 0.82, 1.3]) , name: 'barak', id: '1'})"
GRAPH.QUERY spring-ai "CREATE (p:Document {embedding: vecf32([2.1, 0.82, 1.3]) , name: 'shaked', id: '2'})"
GRAPH.QUERY spring-ai "CREATE (p:Document {embedding: vecf32([2.1, 0.82, 1.3]) , name: 'neta', id: '3'})"
GRAPH.QUERY spring-ai "CREATE (p:Document {embedding: vecf32([2.1, 0.82, 1.3]) , name: 'shahaf', id: '4'})"
GRAPH.QUERY spring-ai "MATCH (p:Document) RETURN p"
GRAPH.QUERY spring-ai "MATCH (p:Document) WHERE p.name='barak' RETURN p"
GRAPH.QUERY spring-ai "CALL db.idx.vector.queryNodes('Document','embedding', 10, vecf32([2.1, 0.82, 1.3])) YIELD node"
GRAPH.QUERY spring-ai "CALL db.idx.vector.queryNodes('Document','embedding', 10, vecf32([2.1, 0.82, 1.3])) YIELD node WHERE node.name='barak' RETURN node"
GRAPH.QUERY spring-ai "UNWIND [{embedding: vecf32([2.1, 0.82, 1.3]) , name: 'barak', id: '1'}] as doc MERGE (d:Document {id: doc.id}) ON CREATE SET d += doc ON MATCH SET d = {} SET d += doc SET d.a = 'b'"


GRAPH.QUERY spring-ai "CALL db.idx.vector.queryNodes('Document','embedding', 10, vecf32([8.51944E-4, 0.82, 1.3])) YIELD node, score WHERE score >= 0.0 RETURN node"



#### spring-ai-vector-store-key

GRAPH.QUERY spring-ai-vector-store-key "MATCH (p) RETURN count(p)"
GRAPH.QUERY spring-ai-vector-store-key "MATCH (p) DELETE p"
GRAPH.QUERY spring-ai-vector-store-key "DROP VECTOR INDEX FOR (p:Document) ON (p.embedding)"
GRAPH.QUERY spring-ai-vector-store-key "CALL db.indexes()"
GRAPH.QUERY spring-ai-vector-store-key "CREATE INDEX FOR (d:Document) ON (d.id)"


#### rest mappings
http://localhost:8080/actuator/mappings

#### curl
curl -X POST http://localhost:8080/api/chat \
-H "Content-Type: text/plain" \
-d "Who is David"



GRAPH.QUERY spring-ai-vector-store-key "CALL db.idx.vector.queryNodes('Document', 'embedding', 4, vecf32([-0.008624711, 0.03858567, -0.14266475, -0.019458938, 0.016860817, 0.0767449, 0.008568231, -0.028973792, 0.012501228, -0.05257358, -0.03451144, 0.0040252698, 0.059931733, 2.213806E-4, -0.033633757, -0.007848855, -0.020526808, -0.017989388, 0.019671794, 9.004376E-4, -0.077396974, -0.008279371, -0.022834653, 0.032705396, 0.13574922, 0.0034378541, -0.019010542, 0.046331227, -0.061601464, -0.034196932, 0.05939407, 0.05377388, -0.011581967, 0.028098457, -0.024844801, -0.017584473, 0.030025892, 0.060482245, -0.025009109, 0.005872657, -0.0020468216, 0.024214836, -0.059990734, -0.09243527, 0.06422267, -0.05457751, -0.0012671695, 0.034741577, -0.057754524, -0.009260095, -0.0033717442, -0.023822356, 0.08130721, -0.061180815, 0.020841708, 0.02610983, -0.023156216, -3.7061982E-4, -0.031964477, -0.0045732497, 0.05810651, 0.07088915, 0.023293408, 0.021449706, 0.039007954, -0.06615517, -0.014491623, 0.056735788, -0.0021184473, -0.0925396, 0.09529494, -0.038228765, 0.079702154, 0.0408853, 0.034636866, -8.7343174E-4, 0.018362224, -0.0089326855, -0.0052721486, -0.03397162, 0.06797088, 0.009123472, 0.049241632, -0.043802284, 0.03259004, 0.043485343, 0.044522062, 0.048073042, 0.0296352, 0.018844195, 0.0047709937, 0.038865317, -0.007374973, 0.025399432, -0.053699974, 0.02653871, -0.08758, 5.139774E-4, -0.030407453, 0.0048644454, -0.019152412, -0.021330226, 0.0073853466, 0.016908303, 0.08774185, 0.033540018, 0.05836262, -0.021893922, -0.012149818, -0.042899705, -0.06732079, -0.0028099392, -0.03035138, -0.04713353, 0.043120716, -0.0026653442, 0.047923822, -0.05012444, -0.020548942, -0.03292126, -0.013851523, -0.036845, 0.0012300914, 0.019422475, -0.051874496, 0.0208659, -0.013856599, 0.04242132, -0.016681956, 0.037214607, -0.057783086, -0.0034609793, -0.0019170531, -0.054169662, 0.040284634, 0.017364409, 0.006321556, -0.0558132, -0.006599665, 0.0483906, -0.023548046, 0.002778816, -0.026003737, -0.013245597, 0.009091499, -0.021838907, 0.07532662, -0.0413479, -0.064054616, -0.0014965438, 0.10164525, 0.03982148, 0.044342283, -0.023934, 0.006745749, -0.035576873, -0.032543004, 0.004533178, 0.012631269, -0.010233641, 0.007374132, 0.009432732, -0.023953088, -0.0070776516, 0.0048395633, -0.027594686, 0.056615535, 0.03640911, 0.04245337, 0.066808686, -6.225303E-4, -0.047581427, 0.01124811, -0.05817634, 0.02142449, 0.0063040927, -0.06152942, -0.04055529, 0.0076664886, -0.031243758, 0.018161697, -0.07180684, 0.056899387, 0.03569956, 0.026078016, 7.8936474E-4, 0.033507485, -0.035202924, -0.020781904, -0.044896577, -0.0048958603, -0.016077917, 0.027543437, -0.026232094, -0.031875316, -0.03984971, 0.031994186, -0.004427394, 0.031786453, -6.446111E-4, -0.062262155, -0.0082241595, -0.038257282, 0.029976023, -0.018734336, 0.06054577, 0.011428792, 0.019019809, -0.03257109, 0.019523386, 0.0858966, -0.029705768, -0.016922219, -0.032593515, -0.0035806452, 0.013843861, 0.01152741, -0.0475458, -0.04251646, 0.057716098, -0.02938684, -0.0045538098, -0.015952995, 0.001667649, 0.039108057, -0.043508258, -0.007793945, -0.010718766, -0.041232545, -0.014254947, 4.3517502E-4, -0.058508527, 0.040572137, 0.071912214, 0.024609018, 0.04474352, -0.005953294, 0.026612153, 0.013188959, -0.016124718, -0.07303512, 0.06249568, -0.027383575, -0.031182088, -3.9991966E-4, 0.02136103, -0.007155242, -0.004570448, 0.021387558, -0.0028937974, 0.00454943, -0.033548083, 8.660987E-6, 0.034617644, 0.018158147, -0.03502341, -0.030259633, 0.026491785, 0.013005378, -0.058301978, 0.068596594, 0.0040520225, 0.054379214, -0.023195699, 0.004209179, -0.042938326, -0.035215486, 0.05623705, 0.034368254, -0.00815474, -0.013135881, -0.005069891, -0.017870246, -0.016070142, -0.006356663, -0.047019675, 0.015836794, -0.037643455, -0.03248967, 0.023848023, 0.0069416026, 0.012583146, -0.03471575, -0.0053908303, 0.011741901, 0.0906028, 0.019911002, -0.033652842, -9.95064E-4, -0.026238244, 0.030580478, -0.01810316, -0.00732224, 0.03921264, -0.037657313, 0.02222198, 0.0436228, 0.018638233, -0.0038537986, -0.055297326, 0.0048578563, -0.009002922, 0.02750456, 0.022221504, -0.01234883, 0.01661587, 0.012187116, 0.025170341, 0.046501584, -0.06865711, 0.021276025, 0.020564945, 0.024566608, 0.025311159, -0.0481674, -0.009629209, 0.006432372, 0.011860775, 0.017812373, 0.05170932, -0.0077209547, -0.0030175212, -0.045530196, -0.0010439784, 0.06207165, 0.057120763, 8.51944E-4, 0.03152028, -0.018035466, -0.006786031, -0.0012803179, -0.012169878, -0.037487797, -0.010111826, -0.005483152, -0.012262228, -0.07821145, 0.01924333, 0.009357933, 0.046916462, 0.034973286, -0.005733612, -0.011494261, -0.04695337, 0.019693494, -0.007888447, -0.027023152, -0.024898846, 0.004295938, -0.008953713, -0.013778794, 0.033350483, -0.011640349, 0.04413798, -0.013081906, 0.026071925, 0.042507473, -0.0018794648, -0.02019001, -0.015017155, -0.021054946, 0.006805363, -0.04319695, -0.056969773, -0.014072539, 0.03755253, 0.075604506, -0.032948192, 0.0019185296, -0.016346166, -0.0034557506, -0.0035930185, 0.029432623, 0.034133583, 0.055236246, 0.009279307, -0.052929148, -0.019286618, -0.0035496561, 0.009446372, -0.008085746, 0.019448748, -0.006364642, 0.014303807, -0.03580366, -0.0061434787, -0.02243106, -0.0039580627, 0.027920738, -0.0020538839, -0.008175347, -0.072293736, -0.016833048, -0.04924334, 0.03359484, -0.03410391, -0.011484928, 0.03764128, -0.0148586985, 0.017490264, -0.013032067, -0.09105206, -0.0088873645, -0.03736614, -0.015618976, 0.118292324, -0.040159076, -0.06681665, 0.038760398, 0.022439897, 0.0012762654, 0.057990763, 0.026264085, -0.057745967, 0.015656514, 0.03551229, 0.05168848, 0.032500487, -0.04556434, -0.0077674887, 0.072407804, 0.031061202, -0.042840563, -0.008968679, 0.02004645, 0.043043207, 0.062009677, 0.023188569, -0.018926416, -0.03645523, -0.003772656, 0.016659783, 0.0011998306, 0.030706188, -0.01687393, -0.0065706964, 0.06498551, 0.02146609, 0.0018420474, 0.06956359, 0.030505871, 0.03181131, -0.0084231645, 0.019565077, 0.055360034, 0.042668477, 0.055579375, -0.041649915, -0.029570602, 0.026654286, 0.0085109, 0.06891783, 0.01562751, -0.0047937646, 0.08175066, -0.016638827, 0.033954926, -0.016398747, -0.009894529, -0.0023855618, 0.031948287, -0.026399564, -0.04868551, 0.01006785, 0.02875177, -0.05345715, 0.00482979, -0.034633275, -0.018741712, 0.052263714, -0.015109107, 0.053283334, 0.005155254, 0.007011135, -0.035937637, -0.0022699807, -0.012427659, -0.051584363, 0.0056594866, -0.0015975096, 0.055849016, 0.023693874, -0.06066858, -0.03965721, 0.025629088, 0.030381521, 0.03284052, -0.02953552, -0.031259194, 0.01958515, 0.012321437, -0.0552239, 0.016527435, -0.04187452, -0.037892777, 0.06808726, -0.009707408, -0.052464634, 0.016393412, -0.009496745, 0.021260276, -0.004650598, 0.023322511, -0.05898274, 0.023211868, 0.042022932, 0.05304737, -0.015061799, 0.015690647, -0.021075308, -0.060652632, 5.7044806E-5, -7.4714335E-4, 0.026785715, 0.049926773, -0.023691395, -0.0017073317, 0.025414517, -0.0132661, 0.051979158, -0.06514933, -0.057310153, -0.005410102, -0.01002499, -0.015965521, 0.009124939, 0.004021583, -0.049964227, 0.018338472, 0.033711664, 0.018608766, -0.009403372, -0.05795204, -0.031998653, -0.041498333, -0.030774757, -0.020064862, 0.0968829, 0.016682813, 0.06737531, 0.033524543, -0.0052334433, 0.032414418, -0.014911994, -0.08802935, -0.013099716, -0.044838578, -0.020767746, 0.034130313, -0.0057370774, 0.019231876, -0.06021196, -0.008102675, -0.043981675, 0.02167199, -0.019970907, -0.024646215, 0.013326668, -0.03256053, -0.05858371, 1.7325405E-4, 0.028501423, -0.013760477, -0.02602335, -0.057233248, -0.024837015, 0.032442886, -0.03728097, 0.0029669756, -0.043823153, 0.062639214, -0.0701693, -0.009500691, -0.008566462, -0.0094260955, 0.0057673813, -0.008016468, -0.032072015, -0.10132934, -0.03283472, 0.001356247, -0.016138297, 0.068226404, 0.010910757, 0.016068567, 0.036957067, -0.015797773, -0.043831393, -0.03972056, -0.036470186, 0.017063007, -0.0020071557, -0.009410839, -0.051065158, -0.00234822, -0.022765521, -0.023150582, -0.018679947, 0.0024426107, 0.020021912, 0.01569653, 0.0020582757, 0.043158844, -0.00984134, -0.01000289, 0.04024244, 0.0038884766, 0.03500417, 3.4323437E-4, -0.016151434, 0.064092755, 0.014622906, -0.02072474, 0.0016879194, 0.044452276, -0.011247197, 0.0120194955, -0.003933055, 0.003764535, -0.03540672, 0.01689258, -0.045818917, -0.014629848, -0.01620752, -0.0030619996, -0.016106907, -0.0621319, -0.067801364, 0.030991241, 0.09905713, -0.037303288, 0.021949535, -0.029223302, -0.01206112, -0.017700564, 0.005813383, -0.056184188, 0.03234257, -0.025648976, 0.05769814, 0.007860239, -0.028868817, 0.0010326377, 0.033176873, 0.01384986, 0.0103220595, -0.0026274046, -0.0061741923, 0.035329156, -0.02787713, 0.03348574, 0.07781599, 0.050373565, 0.051883385, 0.043536536, -0.020770656, -0.0039498876, -0.012731575, -0.042988405, -0.021389563, -0.03229018, 0.024010181, -0.03181924, -0.02636937, 0.023345957, -9.2830946E-4, 0.035655715, -0.0022616305, -0.044601765, -0.011448551, 0.016078245, -7.2967797E-4, 0.00883289, 0.009296099, -0.023958413, 0.055084262, -0.009435086, 0.085026205, -0.024575328, -0.031603333, 0.02004229, 0.050165202, 0.029859208, -0.05853844, -0.01753742, -0.010964313, 0.046214394, -0.03974615, -0.01242709, -0.027121155, 0.0029222483, -0.06433297, 0.030529944, -0.022565546, -0.012940396, -0.023246191, 0.012328781, 0.014665365, 0.0069110394, 0.053461082, 3.8296595E-4, 0.012912056, -0.012952692, 0.0938786, -0.033602223, 0.0054862993, 0.01265222, -0.04519859, -0.0024237698, 0.02565842, 0.04269724, 0.001916383, 0.0018657016, 0.047939, 0.05288961, 0.017852297, -0.032504395, -0.0081891315, -0.009203087, -0.023566753, 0.088523865, -0.020333108, -0.015647009, -0.010722534, 0.0069267317, -0.026998958, 0.0050339345, -0.008277905, 0.04214488, -0.018930897, -0.0035757527, 0.029617611, 0.00840818, -0.033221275, -0.03303736, -0.016562456, -0.02466149, 0.0033675001, -0.014009942, -0.021684127, -0.0364375, 2.3460274E-5, 0.025723921, 0.03275824, 0.016084969, -0.026741, -0.039216027, 0.006075858, 0.06376719, 0.016939903, -0.014304544, -0.07015928, -0.04796151, -0.022328792, 0.013517419, -0.014965366, 0.029499816, 0.0190234, 0.02278679, 4.0610498E-4, 0.016560936, -0.04807314, 0.032110468, 0.057012018, -0.008403806, -0.00820693, -0.050393887, -3.9353815E-4])) YIELD node, score WHERE score >= 0.0 RETURN node"



