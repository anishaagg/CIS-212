// Anisha Aggarwal (CIS 212 - Assignment 1)
/* Calculate the sum of a list of positive integers
 * entered by the user. Assign special-case integers
 * to determine when to print, clear the sum, or quit.
 */
import java.util.Scanner;

public class pos_sum {

	public static void main(String[] args) {
		// Variables
		int user_input = 0;	// variable that holds user's input
		int sum = 0;		// sum of user input
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a positive integer (-1 to print, -2 to reset the sum, -3 to quit): ");
		user_input = input.nextInt();
		/* Variable check:
		System.out.println("User input: " + user_input);
		*/
		
		// If statements
		while (user_input != -3) {
			if (user_input == -1) {
				System.out.println("Sum: " + sum);
				System.out.println("Enter another number: ");
				user_input = input.nextInt();
			} else if (user_input == -2) {
				sum = 0;
				/*Variable check:
				System.out.println("Sum: " + sum);
				*/
				System.out.println("Enter another number: ");
				user_input = input.nextInt();
			} else if (user_input >= 0) {
				sum = sum + user_input;
				/*Variable check:
				System.out.println("Sum: " + sum);
				*/
				System.out.println("Enter another number: ");
				user_input = input.nextInt();
			} else {
				System.out.println("Enter another number: ");
				user_input = input.nextInt();
			}
		}
		/* Variable check
		System.out.println("User inputted -3 to quit: " + user_input);
		*/
		System.out.println("The final Sum is " + sum);
		input.close();
	}
	
}
