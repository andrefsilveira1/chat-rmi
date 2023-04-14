package chat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import chat.exception.ClientAlreadyRegisteredException;
import chat.server.Server;

public class Main {
  public static void main(String[] args) throws RemoteException, NotBoundException {
    System.out.println("Welcome to our distributed chat!");
    var registry = LocateRegistry.getRegistry(9001);
    var server = (Server) registry.lookup("RMIChat");

    String client = initializeClient(server);

    server.disconnect(client);
  }

  private static String initializeClient(Server server) throws RemoteException {
    String clientName = "", aux;
    Scanner sc = new Scanner(System.in);
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
    sc.close();
    return clientName;
  }

}
