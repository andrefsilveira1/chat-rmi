package chat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import chat.exception.ClientAlreadyRegisteredException;
import chat.server.Server;

public class Main {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws RemoteException, NotBoundException {
    System.out.println("Welcome to our distributed chat!");
    var registry = LocateRegistry.getRegistry(9001);
    var server = (Server) registry.lookup("RMIChat");

    String clientId = initializeClient(server);
    while (true) {
      System.out.print("You-> ");
      String line = sc.nextLine();
      String[] tokens = line.split(" ");
      String command = tokens[0];
      String receivedMessage = server.transmitMessage();
      if (receivedMessage != null) {
        System.out.println(receivedMessage);
      }
      if (command.equals("leave")) {
        System.out.println("Disconnecting...");
        break;
      } else if (command.equals("send")) {
        if(tokens[1] != null) {
          server.sendMessage(tokens[1], clientId);
        }
      } else {
        System.out.println("Invalid command");
      }
    }

    server.disconnect(clientId);
  }

  private static String initializeClient(Server server) throws RemoteException {
    String clientName = "", aux;
    while (clientName.isEmpty()) {
      System.out.print("Please, insert your username: ");
      aux = sc.nextLine();
      try {
        server.connect(aux);
        clientName = aux;
      } catch (ClientAlreadyRegisteredException e) {
        System.out.println("It seems this username is already taken by an active user. Please, choose another.");
      }
    }
    return clientName;
  }
}
