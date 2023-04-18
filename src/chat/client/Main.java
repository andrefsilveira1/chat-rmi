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

    ClientImpl c = initializeClient(server);
    while (true) {
      String line = sc.nextLine();
      String[] tokens = line.split(" ");
      String command = tokens[0];
      if (command.equals("leave")) {
        break;
      } else if (command.equals("send")) {

      } else {
        System.out.println("Invalid command");
      }
    }

    server.disconnect(c.getName());
  }

  private static ClientImpl initializeClient(Server server) throws RemoteException {
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
    return new ClientImpl(clientName);
  }
}
