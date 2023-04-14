package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerSide extends Remote {
    void newChat(ChatClient NewChat) throws RemoteException;
    void sendMessage(String message) throws RemoteException;
    String getName() throws RemoteException;
    Integer getId() throws RemoteException;
}
