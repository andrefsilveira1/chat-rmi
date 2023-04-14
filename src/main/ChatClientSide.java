package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientSide extends Remote {
    void getMessage(String message) throws RemoteException;
}
