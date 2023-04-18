package chat.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;

public class Main {
  public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    var registry = LocateRegistry.createRegistry(9001);
    registry.bind("RMIChat", new ServerImpl());
    System.out.println("Chat server is running.");
  }
}
