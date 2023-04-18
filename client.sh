javac -d out ./src/chat/client/Client.java \
  ./src/chat/client/ClientImpl.java \
  ./src/chat/exception/ClientAlreadyRegisteredException.java \
  ./src/chat/server/Server.java \
  ./src/chat/client/Main.java

java -cp out chat.client.Main