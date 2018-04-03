// Anisha Aggarwal	CIS 212	Assignment 8
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.ArrayList;

public class Client {

	private static Socket client;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private static String chatServer;
	
	public static void main(String[] args) {
		//System.out.println("Client is running...");
		
		try {
			connectToServer();
			getStreams();
			processConnection();
		} catch (IOException e) {
			System.out.println("Client terminated connection");
		}
		
	}
	
	// method that checks if connection was a success
	// gets user input and stores it in an array and sends it to server
	// receives prime array from server
	private static void processConnection() throws IOException {
		String message = null;
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> og_array = new ArrayList<Integer>();
		ArrayList<Integer> prime_array = new ArrayList<Integer>();
		
		try {
			// checking if connection was successful
			message = (String) input.readObject();
			if (message.equals("Connection was successful!")) {
				System.out.println("Client>>> connected to server");
			} else {
				System.out.println("ERROR IN CONNECTION");
			}
			
			// getting user input for all the integers until user inputs 0
			System.out.println("Enter a number, press any non-integer to quit");
			while (scanner.hasNextInt()) {
				og_array.add(scanner.nextInt());
				System.out.println("Enter a number, press any non-integer to quit");

			}
			
			// sending array of integers to server
			output.writeObject(og_array);
			output.flush();
			System.out.println("Client sent: " + og_array);
			
			//receive prime array from server
			prime_array = (ArrayList<Integer>) input.readObject();
			System.out.println("Client recieved: " + prime_array);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Client>>> Unknown object type received...");
		}
		
	}

	// gets the input and output stream 
	private static void getStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		
		input = new ObjectInputStream(client.getInputStream());
		
		System.out.println("\nGot I/O streams\n");
	}
	
	// method that will connect to server telling it the address and port
	private static void connectToServer() throws IOException {
		
		System.out.println("Attempting connection...");
		
		client = new Socket("127.0.0.1", 12345);
		
		System.out.println("Connected to: " + 
				client.getInetAddress().getHostName());
	}
	

}
