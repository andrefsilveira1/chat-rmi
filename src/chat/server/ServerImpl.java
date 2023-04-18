package chat.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import chat.client.Client;
import chat.exception.ClientAlreadyRegisteredException;

public class ServerImpl extends UnicastRemoteObject implements Server {
  private List<String> clients = new ArrayList<>();

  public ServerImpl() throws RemoteException {
    super();
  }

  @Override
  public void connect(String clientName) throws RemoteException, ClientAlreadyRegisteredException {
    if (clientExists(clientName)) {
      throw new ClientAlreadyRegisteredException();
    }
    this.clients.add(clientName);
    System.out.println("Client " + clientName + " is now connected!");
  }

  @Override
  public void disconnect(String clientName) throws RemoteException {
    clients.remove(clientName);
    System.out.println("Client " + clientName + " has disconnected.");
  }

  @Override
  public void sendMessage(String from, String to, String msg) throws RemoteException {

  }

  private boolean clientExists(String name) {
    for (String s : this.clients) {
      if (s.equals(name))
        return true;
    }
    return false;
  }
}
