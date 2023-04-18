package chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.exception.ClientAlreadyRegisteredException;
import chat.client.Client;

public interface Server extends Remote {
  // Enables the client to register to the chat by using an username as an id
  public void connect(String clientName) throws RemoteException, ClientAlreadyRegisteredException;

  // Disconnects from server and frees the client name
  public void disconnect(String clientName) throws RemoteException;

  // Sends a message to a particular user
  public void sendMessage(String from, String to, String msg) throws RemoteException;
}
