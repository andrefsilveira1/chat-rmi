package chat.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import chat.exception.ClientAlreadyRegisteredException;
import chat.exception.ClientNotFoundException;
import chat.client.Client;

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
  public void sendMessage(String from, String to, String msg)
      throws RemoteException, ClientNotFoundException, NotBoundException {
    if (!clientExists(to)) {
      throw new ClientNotFoundException();
    }
    var registry = LocateRegistry.getRegistry(9001);
    Client c = (Client) registry.lookup(to);
    c.send(from, msg);
  }

  private boolean clientExists(String name) {
    for (String s : this.clients) {
      if (s.equals(name))
        return true;
    }
    return false;
  }
}
