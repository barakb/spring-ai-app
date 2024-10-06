https://www.youtube.com/watch?v=TPcqBuxl5B8&ab_channel=CodeWiz
http://localhost:8080/swagger-ui/index.html
./gradlew bootRun

#### docker

https://hub.docker.com/r/falkordb/falkordb/
https://docs.falkordb.com/cypher/indexing.html#vector-indexing

```bash
docker run -p 6379:6379 -p 3000:3000 -it --rm falkordb/falkordb:edge

#### spring-ai-vector-store-key

GRAPH.QUERY spring-ai-vector-store-key "MATCH (p) RETURN count(p)"
GRAPH.QUERY spring-ai-vector-store-key "MATCH (p) DELETE p"
GRAPH.QUERY spring-ai-vector-store-key "DROP VECTOR INDEX FOR (p:Document) ON (p.embedding)"
GRAPH.QUERY spring-ai-vector-store-key "CALL db.indexes()"
GRAPH.QUERY spring-ai-vector-store-key "CREATE INDEX FOR (d:Document) ON (d.id)"


#### rest mappings
http://localhost:8080/actuator/mappings

#### curl
user1
curl -X 'POST' \
  'http://localhost:8080/api/index' \
  -H 'accept: */*' \
  -H 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ=' \
  -d ''

curl -X 'POST' \
  'http://localhost:8080/api/chat' \
  -H 'accept: */*' \
  -H 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ=' \
  -H 'Content-Type: application/json' \
  -d 'what can you tell about nubank  credit card clients and personal loans'  

user2 
curl -X 'POST' \
  'http://localhost:8080/api/index' \
  -H 'accept: */*' \
  -H 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
  -d ''
curl -X 'POST' \
  'http://localhost:8080/api/chat' \
  -H 'accept: */*' \
  -H 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
  -H 'Content-Type: application/json' \
  -d 'What can you tell me about coffee machines '





