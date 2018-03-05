// P1Server.java
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class P1Server {

	public static void main (String args[]) throws Exception {
		System.out.println("The Grading server for HW2 is running..");
		ServerSocket welcomeSocket = new ServerSocket(9090);

		while(true) {
			Socket socket = welcomeSocket.accept();
			DataOutputStream outToClient =
					new DataOutputStream(socket.getOutputStream());
			outToClient.writeBytes("Welcome to HW2 P1 Local Server. Please give me your identity. What's your name?\n");
			BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String student_name = inFromClient.readLine();
			outToClient.writeBytes("What's your student ID?\n");
			String student_id = inFromClient.readLine();
			student_id = student_id.toUpperCase();
			outToClient.writeBytes("What's your favorite food?\n");
			String favorite_food = inFromClient.readLine();
			favorite_food = favorite_food.toLowerCase();
	        if (student_name == null || student_id == null) {
	            throw new IllegalStateException("");
	        }

	        outToClient.writeBytes("Hi " + student_name +
	                ", your student id is " + student_id +
	                ". And you love " + favorite_food + ". Is it correct? (Y/N)\n");
	        String YorN = inFromClient.readLine();

	        if (YorN == null) {
	        	outToClient.writeBytes("Please retry it again.\n");
	        } else if (!YorN.equals("Y") && !YorN.equals("y")) {
	 	        outToClient.writeBytes("Please retry it again.\n");
	        } else {
	        	outToClient.writeBytes("Thanks. Your response has been recorded. " +
	                    "Please remeber to print-screen this execution, and have a nice day! " +
	                    "(Session End)\n");
	        }

		}
	}
}
