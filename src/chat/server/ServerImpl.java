package chat.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import chat.exception.ClientAlreadyRegisteredException;

public class ServerImpl extends UnicastRemoteObject implements Server {
  private List<String> clients = new ArrayList<>();
  private List<String> messages = new ArrayList<>();
  private List<String> syncClient = new ArrayList<>();

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

  public void sendMessage(String message, String client) throws RemoteException {
    messages.add(message);
    syncClient.add(client);
  }
  public String transmitMessage() throws RemoteException {
    if (messages.size() != 0) {
      String message = syncClient.get(0) + ": " +messages.get(0);
      messages.remove(0);
      syncClient.remove(0);
      return message;
    }
    return null;
  }

  private boolean clientExists(String name) {
    for (String s : this.clients) {
      if (s.equals(name))
        return true;
    }
    return false;
  }
}
