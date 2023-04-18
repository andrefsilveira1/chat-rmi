package chat.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
  public void send(String from, String message) throws RemoteException;

  public String getName();
}
