// Anisha Aggarwal	CIS 212	Assignment 2

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class main_2 {
	
	public static void main(String[] args) {
		int array_len = 0;
		double density = 0;
		long start_time;
		long end_time;
		double elapsed_time_milli;
		
		// get user input for array length
		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				
				System.out.println("Please enter an array length (between 1 and 1000000): ");
				array_len = input.nextInt();
				
				if ((array_len < 1) || (array_len > 1000000)) {
					System.out.println("Array length was invalid. Please re-enter a new array length: ");
					continue;
				}
				break;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Array length was invalid. Please re-enter a new array length: ");
				continue;
			}
		}
		// get user input for density of array
		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				
				System.out.println("Please enter an array density: ");
				density = input.nextFloat();
				
				if ((density < 0) || (density >= 1)) {
					System.out.println("Density was invalid. Please re-enter a new density: ");
					continue;
				}
				break;
			}
			catch (java.util.InputMismatchException e) {
				System.out.println("Density was invalid. Please re-enter a new density: ");
				continue;
			}
		}
		// Variable check:
		System.out.println("User Array Length: \n" + array_len);
		System.out.printf("User Density:\n" + density + "\n");
		
		/*Findings from functions below
		 * It takes more time to create a dense array than it takes to create a sparse array.
		 * It takes less time to convert dense to sparse array than from sparse to dense array.
		 * It takes more time to find the max value in the dense array than the sparse array.
		*/
		
		// Print dense array
		start_time = System.nanoTime();
		int [] da = dense_array(array_len, density);
		end_time = System.nanoTime();
		elapsed_time_milli = (end_time - start_time)/1000000;
		System.out.println("Create dense length: " + array_len + " Time: " + elapsed_time_milli);
		//Test Function
		//System.out.println("Dense Array: ");
		//print_dense_array(da);

		// Convert dense array to sparse array
		start_time = System.nanoTime();
		ArrayList <ArrayList <Integer>> d_to_s = dense_to_sparse(da, array_len);
		end_time = System.nanoTime();
		elapsed_time_milli = (end_time - start_time)/1000000;
		System.out.println("Convert to sparse length: " + d_to_s.size() + " Time: " + elapsed_time_milli);// Test Function
		//System.out.println("Dense to Sparse Array: ");
		//print_sparse_array(d_to_s);
		
		//Print sparse array
		start_time = System.nanoTime();
		ArrayList <ArrayList <Integer>> sa = sparse_array(array_len, density);
		end_time = System.nanoTime();
		elapsed_time_milli = (end_time - start_time)/1000000;
		System.out.println("Create sparse length: " + sa.size() + " Time: " + elapsed_time_milli);//Test Function
		//System.out.println("Sparse Array: ");
		//print_sparse_array(sa);
		
		// Convert sparse to dense array
		start_time = System.nanoTime();
		int [] s_to_d = sparse_to_dense(sa);
		end_time = System.nanoTime();
		elapsed_time_milli = (end_time - start_time)/1000000;
		System.out.println("Convert to dense length: " + s_to_d.length + " Time: " + elapsed_time_milli);//Test function
		//System.out.print("Sparse to Dense Array: ");
		//print_dense_array(s_to_d);
		
		// Find Max value and index of dense array
		start_time = System.nanoTime();
		dense_max (da);
		end_time = System.nanoTime();
		elapsed_time_milli = (end_time - start_time)/1000000;
		System.out.println(" Time: " + elapsed_time_milli);
		
		//Find Max value and index of sparse array
		start_time = System.nanoTime();
		sparse_max(sa);
		end_time = System.nanoTime();
		elapsed_time_milli = (end_time - start_time)/1000000;
		System.out.println(" Time: " + elapsed_time_milli);
		
	}
	// Function to create the dense array based on user input of array length and density
	public static int[] dense_array(int array_len, double density) {
		int [] dense = new int [array_len];
		int min = 1;
		int max = 1000000;
		
		Random r = new Random();
		
		for (int i = 0; i < array_len; i++) {
			if (r.nextDouble() < density) {
				dense[i] = (min + (int)(Math.random() * ((max - min) + 1)));
			}
			else {
				dense[i] = 0;
			}
		}
		return dense;	
	}
	
	// Function to create the sparse array based on user input of array length and density
	public static ArrayList <ArrayList <Integer>> sparse_array(int length, double density) {
		
		ArrayList <ArrayList <Integer>> sparse = new ArrayList <ArrayList <Integer>>();
		Random r = new Random();
		
		for (int i = 0; i < length; i++) {
			if (r.nextDouble() < density) {
				ArrayList <Integer> row = new ArrayList <Integer>();
				row.add(i);
				row.add(r.nextInt(1000000) + 1);
				sparse.add(row);
			} else {
				continue;
			}
		}
		return sparse;

	}
	
	// Function to print the dense array
	public static void print_dense_array(int[] da) {
		for (int i : da) {
			System.out.println(i);
		} 
	}
	
	// Function to print the sparse array
	public static void print_sparse_array(ArrayList <ArrayList <Integer>> sa) {
		for (int i = 0; i < sa.size(); i++) {
			ArrayList <Integer> row = sa.get(i);
			for (int j = 0; j < row.size(); j++) {
				System.out.printf(row.get(j) + ", ");
			}
			System.out.println();
		}
	}
	
	// Function to convert dense array to sparse array
	public static ArrayList <ArrayList <Integer>> dense_to_sparse(int[] dense_a, int length) {
		ArrayList <ArrayList <Integer>> sparse_array = new ArrayList <ArrayList <Integer>>();
		
		for (int i = 0; i < length; i++) {
			if (dense_a[i] != 0) {
				ArrayList <Integer> row = new ArrayList <Integer>();
				row.add(i);
				row.add(dense_a[i]);
				sparse_array.add(row);
			}

		}
		return sparse_array;
	}
	
	// Function to convert sparse array to dense array
	public static int[] sparse_to_dense(ArrayList <ArrayList <Integer>> sparse_a) {

		ArrayList <Integer> last_row = sparse_a.get(sparse_a.size() - 1);
		int length = last_row.get(0) + 1;
		int[] dense_array = new int[length];
		
		// initialize dense array to 0
		for (int i = 0; i < length; ++i) {
			dense_array[i] = 0;
		}
		
		// fill in non-zero elements
		for (int i = 0; i < sparse_a.size(); i++) {
			ArrayList <Integer> temp_row = sparse_a.get(i);
			// get the integer in the 2nd column and dense_array[i] = that integer
			dense_array[temp_row.get(0)] = temp_row.get(1);
		}
		return dense_array;
	}
	
	// Function to find the max value of the dense array and print it
	public static void dense_max (int[] dense_a) {
		int max = dense_a[0];
		int max_index = 0;
		
		for (int i = 1; i < dense_a.length; i++) {
			if (dense_a[i] > max){
				max = dense_a[i];
				max_index = i;
			}
		}
		System.out.println("Find Max (dense): " + max + " at: " + max_index);
	}
	
	// Function to find the max value of the sparse array and print it
	public static void sparse_max (ArrayList <ArrayList <Integer>> sparse_a) {
		int max;
		int max_index;
		
		//Initialize max and max index to the first element of sparse array
		ArrayList <Integer> temp_row = sparse_a.get(0);
		max = temp_row.get(1);
		max_index = temp_row.get(0);
		
		//Find max and max index
		for (int i = 1; i < sparse_a.size(); i++) {
			temp_row = sparse_a.get(i);
			if (temp_row.get(1) > max) {
				max = temp_row.get(1);
				max_index = temp_row.get(0);			}
		}
		
		System.out.println("Find Max (sparse): " + max + " at: " + max_index);
	}
	
}

	
	
	
	
