package chat.client;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.Arrays;
import chat.exception.ClientAlreadyRegisteredException;
import chat.server.Server;
import java.util.HashSet;
import java.util.Set;


public class Main {

  public static Scanner sc = new Scanner(System.in);
  public static String newMessage;
  public static boolean checked = true;

  public synchronized static void main(String[] args) throws RemoteException, NotBoundException {
    System.out.println("Welcome to our distributed chat!");
    var registry = LocateRegistry.getRegistry(9001);
    var server = (Server) registry.lookup("RMIChat");

    String clientId = initializeClient(server);
    while (true) {
      String line = sc.nextLine();
      String[] tokens = line.split(" ");
      String command = tokens[0];
      new Thread(new printThread(server, clientId)).start();
      if (command.equals("leave")) {
        checked = false;
        System.out.println("Disconnecting...");
        break;
      } else if (command.equals("send")) {
        if(tokens[1] != null) {
          server.sendMessage(line.replace("send", ""), clientId);
        }
      } else {
        System.out.println("Invalid command");
      }
    }

    server.disconnect(clientId);
  }

  @SuppressWarnings("unchecked")
  private static class printThread implements Runnable {
    private Server server;
    private String clientId;
    private String receivedMessage;
    private Set<String> storyMessages;

    public printThread(Server server, String clientId) throws RemoteException {
      this.server = server;
      this.clientId = clientId;
      this.receivedMessage = receivedMessage;
      this.storyMessages = new HashSet();
    }

    @Override
    public void run() {
        try {
          while (checked) {
            String receivedMessage = server.transmitMessage();
            if(receivedMessage != null && !receivedMessage.startsWith(clientId)) {
              synchronized (storyMessages) {
                if(storyMessages.add(receivedMessage)) {
                  System.out.println(receivedMessage);
                }
              }
            }
          }
        } catch (RemoteException error) {
          System.out.println("Something goes wrong... " + error);
        }
      }
  };

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
