package com.bhanuchaddha.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bhanu Chaddha on 11-11-2018 01:56 AM.
 */
public class ClientWriteThread extends Thread {
    PrintWriter serverWriter;
    Socket socket;
    Client client;

    public ClientWriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
        try {
           this.serverWriter= new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter User Name: ");
        String username  = scanner.nextLine();
        client.setUserName(username);
        serverWriter.println(username);
        String text;
        do{
            text = scanner.nextLine();
            serverWriter.println(text);
        }while (!"bye".equals(text));

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
