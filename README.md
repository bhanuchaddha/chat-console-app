# Chat Console app

Its a simple chat application base on Java Socket. This application only provide the group chat functionality.
Message from one user is broadcast to all the connected users.

### Below are core components in the Application

#### Server
Server is used to create socket server and keep track of all the user in the chat.
Server create a Thread ```UserThread.java``` for each connected client. This thread is responsible for sending and receiving message from the client.

Sample Output:
```
Server has been started on 8090
New connection received.
New connection received.
New connection received.
```


#### Client
Client application connect to Chat Server.
They register by providing their username and They are notified about all the connected users.
Each client is consist of 2 threads ```ClientReadThread``` and ```ClientWriteThread```. ```ClientReadThread``` read the messages from the server.
```ClientWriteThread``` send  message to serve to broadcast.

Sample Output

Client 1
```
No Users are connected.
Please Enter User Name:
Bob
[Jan] has been joined
[Bob]:
[Tina] has been joined
[Bob]:
Hi All, I am Bob from UK.
[Tina]Hey there, I am Tina from UK as well. What do you do?
[Bob]:

[Bob]:
[Jan]Hey all, wassap?
```

Client 2
```
[Bob] Users are connected.
Please Enter User Name:
Jan
[Tina] has been joined
[Jan]:
[Bob]Hi All, I am Bob from UK.
[Jan]:

[Jan]:
[Tina]Hey there, I am Tina from UK as well. What do you do?
[Jan]:

[Jan]:
Hey all, wassap?
```

Client 3
```
[Bob, Jan] Users are connected.
Please Enter User Name:
Tina
[Bob]Hi All, I am Bob from UK.
[Tina]:

[Tina]:
Hey there, I am Tina from UK as well. What do you do?
[Jan]Hey all, wassap?
```

### Run

Start server
```
java -cp . com.bhanuchaddha.chat.Server 8090
```

Start a Client
```
java -cp . com.bhanuchaddha.chat.Client localhost 8090
```


##### Inspiration
<a  href="https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket"> Java Socket Chat</a>