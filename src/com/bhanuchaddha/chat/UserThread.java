package com.bhanuchaddha.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Bhanu Chaddha on 11-11-2018 12:44 AM.
 *
 * Single Thread to handle a user session.
 *
 */
public class UserThread extends Thread {

    private Socket socket;
    private Server server;
    private PrintWriter socketWriter;

    public UserThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.socketWriter = new PrintWriter(socket.getOutputStream(), true); //true?

            printConnectedUsers();

            String userName = socketReader.readLine();
            server.addUserName(userName);
            server.broadcastMessage("["+userName+"] has been joined",this);

            do{
                server.broadcastMessage("["+userName+"]"+socketReader.readLine()+"\n",this);
            } while (!"bye".equals(socketReader.readLine()));

            server.removeUserName(userName);
            server.removeUserThread(this);
            server.broadcastMessage("["+userName+"] has left",this);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printConnectedUsers() {
        if(server.getUserNames().isEmpty()){
            this.sendMessageToClient("No Users are connected.");

        }else{
            this.sendMessageToClient(server.getUserNames()+" Users are connected.");

        }
    }

    public void sendMessageToClient(String message){
        this.socketWriter.println(message);
    }
}
