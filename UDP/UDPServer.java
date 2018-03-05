import java.io.*;
import java.nio.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9091);
        int rcvdPktNum = 0;
        int lostPktNum = 0;
        System.out.println("Server Ready...");
        while (true) {
            DatagramPacket rcvdPkt =
                new DatagramPacket(new byte[128], 128);
            serverSocket.setSoTimeout(5000);
            try {
                serverSocket.receive(rcvdPkt);
            } catch (SocketTimeoutException ste) {
                System.out.println("### Timed out after 5 seconds");
                if(rcvdPktNum != 0) break;
                else {
                    System.out.println("### Client Not Ready, continue.");
                    continue;
                }
            }
            String sentence = new String(rcvdPkt.getData());
            InetAddress ipAddr = rcvdPkt.getAddress();
            int port = rcvdPkt.getPort();
            rcvdPktNum ++;
            System.out.println("RECV from " + ipAddr + ":" + port +
               ":" + sentence);
            System.out.println("# of Received Packages: " + rcvdPktNum);

            // Captalize the received sentence
            String modifiedSentence = sentence.toUpperCase() + '\n';
            System.out.println("MODIFY TO:" + modifiedSentence);
            byte[] sendData = modifiedSentence.getBytes();

            DatagramPacket sendPkt =
               new DatagramPacket(sendData, sendData.length, ipAddr, port);
            serverSocket.send(sendPkt);
        }
        lostPktNum = 10000 - rcvdPktNum;
        System.out.println("=======SUMMARY=======");
        System.out.println("# of Received Packages: " + rcvdPktNum);
        System.out.println("# of Lost Packages: " + lostPktNum);

    }

}
