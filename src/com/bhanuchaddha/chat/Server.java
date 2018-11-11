package com.bhanuchaddha.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bhanu Chaddha on 11-11-2018 12:43 AM.
 */
public class Server {

    private int port;
    private Set<UserThread> users = new HashSet<>();
    private Set<String> userNames = new HashSet<>();

    public Server(int port) {
        this.port = port;
    }

    private void execute(){

        try (ServerSocket serverSocket = new ServerSocket(this.port)){

            System.out.println("Server has been started on "+port);

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New connection received.");
                UserThread newUser = new UserThread(this, socket);
                users.add(newUser);
                newUser.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length<1){
            System.out.println("Please enter port number");
            System.exit(0);
        }
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.execute();
    }

    public void broadcastMessage(String message, UserThread sender){
        this.users.stream()
                .filter(user->user!=sender)
                .forEach(user-> user.sendMessageToClient(message));
    }

    public Set<String> getUserNames() {
        return userNames;
    }

    public void addUserName(String userName){
        this.userNames.add(userName);
    }

    public void removeUserName (String userName){
        this.userNames.remove(userName);
    }

    public void removeUserThread(UserThread user){
        this.users.remove(user);
    }
}
