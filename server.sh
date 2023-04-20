# compiling
javac -d out ./src/chat/client/Client.java \
  ./src/chat/client/ClientImpl.java \
  ./src/chat/exception/ClientNotFoundException.java \
  ./src/chat/exception/ClientAlreadyRegisteredException.java \
  ./src/chat/server/Server.java  \
  ./src/chat/server/ServerImpl.java \
  ./src/chat/server/Main.java

java -cp out chat.server.Main