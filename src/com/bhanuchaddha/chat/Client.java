package com.bhanuchaddha.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Bhanu Chaddha on 11-11-2018 01:37 AM.
 *
 * Client to connect to chat Server.
 * Each client run 2 infinite loop for read and write operations.
 */
public class Client {

    private String hostname;
    private int port;
    private String userName;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute(){
        try {
            Socket socket = new Socket(hostname, port);
            new ClientReadThread(socket, this).start();
            new ClientWriteThread(socket, this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        if (args.length <2){
            System.out.println("Enter host and port number");
            System.exit(0);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new Client(host, port).execute();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
