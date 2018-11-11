package com.bhanuchaddha.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Bhanu Chaddha on 11-11-2018 01:47 AM.
 */
public class ClientReadThread extends Thread {

    Socket socket;
    Client client;
    BufferedReader serverReader;

    public ClientReadThread(Socket socket, Client client) {
        this.client = client;
        this.socket = socket;
        try {
            this.serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println(serverReader.readLine());
                if(client.getUserName()!=null){
                    System.out.println("["+client.getUserName()+"]: ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
