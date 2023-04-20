package chat.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;

public class ClientImpl extends UnicastRemoteObject implements Client {
  private String name;

  public ClientImpl(String name) throws RemoteException {
    this.name = name;
  }

  @Override
  public void send(String from, String message) throws RemoteException {
    System.out.println(from + ": " + message);
  }

  public String getName() {
    return name;
  }

  public void register() throws RemoteException {
    var registry = LocateRegistry.getRegistry(9001);
    registry.rebind(name, this);
  }

}
