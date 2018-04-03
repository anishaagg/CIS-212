// Anisha Aggarwal	CIS 212	Assignment 3

import java.util.ArrayList;
import java.util.Random;
import java.lang.Double;

public class main_3 {

	public static void main(String[] args) {
		int rects = 0;
		int boxes = 0;
		int circs = 0;
		int spheres = 0;
		double random_num;
		
		// populate each instance of measurable with an area of one of the 4 classes
		// roughly 25% of the time for each class
		Random r = new Random();
		ArrayList <Measurable> measurable = new ArrayList <Measurable>();
		for (int i = 0; i<1000; ++i) {
			random_num = r.nextDouble();
			if (random_num <= .25) {
				//rectangle
				measurable.add(new Rectangle(nextDouble(), nextDouble()));
				++rects;
			} else if (random_num > .25 && random_num <= .5) {
				//box
				measurable.add(new Box(nextDouble(), nextDouble(), nextDouble()));
				++boxes;
			} else if (random_num > .5 && random_num <= .75) {
				//circle
				measurable.add(new Circle(nextDouble()));
				++circs;
			} else {
				//sphere
				measurable.add(new Sphere(nextDouble()));
				++spheres;
			}
		}
		
		//print statements
		System.out.println("Rectangles: " + rects);
		System.out.println("Boxes: " + boxes);
		System.out.println("Circles: " + circs);
		System.out.println("Spheres: " + spheres);
		System.out.println("Sum: " + calculateSum(measurable));
	}
	
	// method that returns a random double in range (0.0, 1.0]
	private static double nextDouble() {
		double num_double = 0;
		
		Random r = new Random();
		num_double = r.nextDouble() + Double.MIN_VALUE; 
		
		return num_double;
	}
	
	// method that takes the arraylist type meausurable and calculates the sum
	// of the areas in each element combined
	private static double calculateSum(ArrayList <Measurable> x) {
		double sum = 0;
		double area;
		
		for (int i = 0; i < x.size(); ++i) {
			area = x.get(i).getArea();
			sum = sum + area;
		}
		
		return sum;
	}
}





