import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;

public class server2 {
    public static void main(String[] args){

        // checks if port no is provided or not
        if(args.length<1){
            System.out.println("Instruction to invoke server should be in this format: javac server2 <portNo>");
            return;
        }

        // parses the port no from string to int
        int portNo = Integer.parseInt(args[0]);

        // checks if port no is 16 bit no.
        if(portNo>65535 || portNo<0){
            System.out.println("Port Number should be withing greater than 0 and less than 65535");
            return ;
        }

        // Creates an hashmap to store the info send by client
        HashMap<String, String> map = new HashMap<String, String>();

        //Parses the message sent by client and creates a map
        InfoMapping getMap = new InfoMapping();

        try{
            
            //creates a UPD socket and bind to portNo. 
            DatagramSocket socket = new DatagramSocket(portNo);

            System.out.println("Listening on port no."+portNo);

            while(true){

                //Assigns datagram packet and byte buffer to store information coming from client
                byte[] buff = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);

                //Reads byte from network onto the packet
                socket.receive(packet);

                //Prepares message string from the incoming packet
                String message = new String(packet.getData(),0,packet.getLength());

                //Parses the message to getMap() which prepares map object with key value pairs
                map = getMap.getMap(message);

                System.out.println("--------------------------------------------------");

                //Checks if the message is intended for this port and prints the information     
                if(map.get("port")!=null && (map.get("port")).equals(args[0])){
                        System.out.println("Information Intended for this address");
                        for(String key: map.keySet()){
                            System.out.println(key+": "+map.get(key));
                        }
                }else{
                    System.out.println("Information not intended for this address");
                    // for(String key: map.keySet()){
                    //     System.out.println(key+": "+map.get(key));
                    // }
                }
                
            }

        }catch(SocketException e){
            System.out.println("An issue raised with the socket connection");
        }catch(IOException e){
            System.out.println("An Exception raised with port");
        }
    }
    
}

