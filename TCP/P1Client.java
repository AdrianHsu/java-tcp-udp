// P1Client.java
import java.io.*;
import java.net.Socket;

public class P1Client {
   private Socket clientSocket = null;
   private BufferedReader inFromServer = null;
   private int packageCount = 0;
   private int totalCount = 5;
   
   public void connectToServer() throws IOException {
      String serverAddress = "127.0.0.1";
      serverAddress = "140.112.18.178";
      int port = 9090;
      // TODO: clientSocket = ?
      clientSocket = new Socket(serverAddress, port);
      // TODO: inFromServer = ?
      inFromServer =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      System.out.println("Connect to server at " + serverAddress + "..");
   }
   public void start() throws Exception {
      // TODO: handle messages recv from and send to server
      
      while(true) {
         String recvFrom = inFromServer.readLine();
         System.out.println(recvFrom);
         if(packageCount == totalCount - 1) break;
         DataOutputStream outToServer =
  				  	new DataOutputStream(clientSocket.getOutputStream());
         BufferedReader buf = new BufferedReader(
                                      new InputStreamReader(System.in));
         String text = buf.readLine();
         outToServer.writeBytes(text + '\n');
         packageCount++;
      }
   }
   /**
    * Runs the client application.
    */
   public static void main(String[] args) throws Exception {
      P1Client client = new P1Client();
      client.connectToServer();
      client.start();
      client.clientSocket.close();
   }
}
