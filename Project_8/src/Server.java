// Anisha Aggarwal	CIS 212	Assignment 8
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class Server {
	private static ServerSocket server;
	private static Socket connection;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	public static void main(String[] args) {
		System.out.println("Server is running...");
		
		try {
			server = new ServerSocket(12345, 10);
			waitForConnection();
			getStreams();
			processConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// method that will have server wait until it makes a connection
	// with client
	private static void waitForConnection() throws IOException {
		System.out.println("Waiting for connection");
		connection = server.accept();
		System.out.println("Connection received from: " +
				connection.getInetAddress().getHostName());
		
	}
	
	// gets the input and output stream 
	private static void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		
		input = new ObjectInputStream(connection.getInputStream());
		
		//System.out.println("Got I/O Streams");
	}
	
	// method that checks if connection was a success
	// it also reads in user's array from client and creates a prime array
	// sends that prime array to client
	private static void processConnection() throws IOException {
		ArrayList<Integer> user_array = new ArrayList<Integer>();
		ArrayList<Integer> prime_array = new ArrayList<Integer>();
		
		String message = "Connection was successful!";
		sendData(message);
		
		try {
			user_array = (ArrayList<Integer>) input.readObject();
			System.out.println("Server received message: " + user_array);
			
			// checking whether numbers are prime
			for(Integer i : user_array) {
				if (isPrime(i)) {
					System.out.println("Yes is prime: " + i);
					prime_array.add(i);
				} else {
					System.out.println("Not prime: " + i);
				}
			}
			System.out.println("Server>>> prime array created: " + prime_array);
			
			//send prime array to client
			output.writeObject(prime_array);
			output.flush();

		} catch (ClassNotFoundException e){
			System.out.println("Server>>> Unknown object type received...");
		}
		
	}
	
	// http://www.javawithus.com/programs/prime-numbers
	private static boolean isPrime(Integer n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= (n/2); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	// method that will send message from server to client
	private static void sendData(String message) {
		try {
			output.writeObject(message);
			output.flush();
			System.out.println("Server>>> " + message);
		} catch (IOException e) {
			System.out.println("Error writing object");
		}
		
	}
}
