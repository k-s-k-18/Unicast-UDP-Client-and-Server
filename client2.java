import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

class client2{
    public static void main(String[] args) {

        try{

            //Reads messages from .txt file
            String messagePath = "./messages.txt";
            File messageFile = new File(messagePath);
            FileReader mr = new FileReader(messageFile);
            BufferedReader mbr = new BufferedReader(mr);
            ArrayList<String> messages = new ArrayList<String>();
            String line;

            //checks if messages file is empty and returns
            while(messageFile.length()==0){
                System.out.println("Message File is empty.");
                return ;
            }

            //reads messages line by line and adds space at the end of each line
            while((line=mbr.readLine())!=null){
                if(line.charAt(line.length()-1)==' '){
                    messages.add(line);
                }else{
                    messages.add(line+" ");
                }
            }

            mbr.close();

            //reads server addresses from config file
            String path = "./config.file";
            File file = new File(path);
            
            //returns if config file doesn't have any addresses
            if(file.length()==0){
                System.out.println("Server List is empty.");
                return ;
            }

            //Opens a new file reader to read serverlist
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //Uses this class to prepare the address to send the packet
            ServerAddress serverAddress = new ServerAddress();

            while((line = br.readLine())!=null){
                serverAddress.getAddress(line);
                try{

                    //Prepares IP address from parsed string containing ip address and port no
                    InetAddress addr = InetAddress.getByName(serverAddress.ipAddress);

                    //Checks if it is a valid port no
                    if(serverAddress.portNo<0 || serverAddress.portNo > 65535){
                        System.out.println("Invalid Port No.");
                    }

                    //Creates an UDP socket to send information over
                    DatagramSocket socket = new DatagramSocket();
                    
                    int j = 0; //Counter to keep track of no. of lines in messages.txt

                    System.out.println("Sending message to IP Address: "+addr+" port no."+serverAddress.portNo);

                    while(j<messages.size()){
                        //Loads message into byte buffer
                        byte[] buff = new byte[(messages.get(j)).length()];
                        buff = (messages.get(j)).getBytes();

                        //Prepares a datagram packet to send over the connection
                        DatagramPacket packet = new DatagramPacket(buff,buff.length,addr,serverAddress.portNo);
                        socket.send(packet);

                        j++;
                    }

                    socket.close();

                } catch(SocketException e){
                    System.out.println("Socket Connection Cannot be Established");
                }//catch(InterruptedException e){
                //     System.out.println("Interrupted");
                // }
            }
        }catch(IOException ex){
            System.out.println("Couldn't read file");
        }
    }
}

