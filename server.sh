# compiling
javac -d out ./src/chat/exception/ClientAlreadyRegisteredException.java \
  ./src/chat/server/Server.java  \
  ./src/chat/server/ServerImpl.java \
  ./src/chat/server/Main.java

java -cp out chat.server.Main