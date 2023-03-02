docker run -d -p 8080:8080 --network protobuf --network-alias message-verifier mytyan/message-verifier:0.0.1

docker run -d -p 8081:8081 -p 8082:8082 --network protobuf --network-alias messenger mytyan/messenger:0.0.1

docker run -d -p 7070:8080 --network protobuf --network-alias messenger-client mytyan/messenger-client:0.0.1
