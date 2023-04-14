package main;

import main.ChatServerSide;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientSide, Runnable {

    private ChatServerSide chat;
    private  String clientName;
    public ChatClient(String clientName, ChatServerSide chat) throws RemoteException {
        this.clientName = clientName;
        this.chat = chat;
        chat.newChat(this);
    }

    public void getMessage(String message) throws  RemoteException {
        System.out.println(message);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String sentMessage;
        for(;;) {
            sentMessage = scanner.nextLine();
            try {
                chat.sendMessage(clientName + "->" + sentMessage);
            } catch (RemoteException error) {
                System.out.println("Error in Client side while the message was broadcasting" + error);

            }
        }
    }
}
