# UDP Client and Server Application

The Client program sends information in messages.txt file line-by-line to the servers provided in the config.file. The server program reads the information sent by the client and prepares a key:value object. The server then identifies the messages intended for its address and prints the information.

### To Run
- type ```make all``` in terminal
- start server by typing ```java server2 <portNo>``` in terminal
- start server by typing ```java client2``` in terminal
