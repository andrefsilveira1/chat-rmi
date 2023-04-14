package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String [] args) throws RemoteException, MalformedURLException {
        Naming.rebind("RMIChat", new ChatServer());
    }
}
