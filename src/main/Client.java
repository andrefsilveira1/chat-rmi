package main;

import main.ChatServerSide;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String [] args) throws RemoteException, NotBoundException, MalformedURLException {
        String url = "rmi://localhost/RMIChat";
        ChatServerSide ChatServer = (ChatServerSide) Naming.lookup(url);
        new ChatClient(args[0], ChatServer);
    }

}
