package chat.client;

import java.rmi.RemoteException;

public class ClientImpl implements Client {
  private String name;

  public ClientImpl(String name) {
    this.name = name;
  }

  @Override
  public void send(String from, String message) throws RemoteException {
  }

  public String getName() {
    return name;
  }

  public Client make(String name) {
    return new ClientImpl(name);
  }
}
