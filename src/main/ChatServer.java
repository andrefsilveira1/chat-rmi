package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ChatServer extends UnicastRemoteObject implements ChatServerSide {
    private Vector<ChatClientSide> chatList;
    private String name;
    private Integer Id;

    public ChatServer() throws RemoteException {
        super();
        chatList = new Vector<ChatClientSide>();
    }

    public  synchronized void sendMessage(String message) throws  RemoteException {
        for (ChatClientSide client : chatList) {
            client.getMessage(message);
        }
    }
    public  synchronized  void newChat(ChatClient chat) throws RemoteException {
        this.chatList.add(chat);
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public Integer getId() throws RemoteException {
        return this.Id;
    }
}
