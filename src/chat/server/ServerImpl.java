package chat.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import chat.exception.ClientAlreadyRegisteredException;

public class ServerImpl extends UnicastRemoteObject implements Server {
  private List<String> clients = new ArrayList<>();
  private List<String> messages = new ArrayList<>();

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

  public synchronized void sendMessage(String message, String client) throws RemoteException {
    String receivedMessage = client + ":"+ message;
    messages.add(receivedMessage);
  }

  public synchronized String transmitMessage() throws RemoteException {
    if (messages.size() != 0) {
//      String[] allMessages = messages.toArray(new String[0]);
      String allMessages = messages.get(messages.size() -1);
      return allMessages;
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
