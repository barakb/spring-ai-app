curl -X 'POST' \
'http://localhost:8080/api/index' \
-H 'accept: */*' \
-d ''

curl -X 'POST' \
'http://localhost:8080/api/chat' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d 'What can you tell about nubank credit card and personal loan ?'