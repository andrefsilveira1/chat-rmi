javac -d out ./src/chat/exception/ClientAlreadyRegisteredException.java \
  ./src/chat/server/Server.java \
  ./src/chat/client/Main.java \

java -cp out chat.client.Main