import java.io.*;
import java.nio.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();

        // 1. LOCAL: Send the sentence to Server 10000 times continuously
        InetAddress serverIP = InetAddress.getByName("127.0.0.1");

        // 2. mslab workstation (CSIE Department, prof. Shou-De Lin)
        // InetAddress serverIP = InetAddress.getByName("140.112.31.184");

        // 3. Amazon AWS EC2 Services (Zone: us-west-2a, 54.70.108.108)
        // InetAddress serverIP = InetAddress.getByName("ec2-54-70-108-108.us-west-2.compute.amazonaws.com");

        for(int i = 1; i <= 10000; ++i) {
            String sentence = "Hello from Client, Index of this package: " + i;
            System.out.println("Client Side SENT: Index of this package: " + i);
            byte[] bytes = sentence.getBytes();
            DatagramPacket sendPkt =
                new DatagramPacket(bytes, bytes.length, serverIP, 9091);
            clientSocket.send(sendPkt);

            /* Suspend for 1us */
            Thread.sleep(1); /*Prevent client side buffer overflow. */
        }

        clientSocket.close();
    }
}
