// calculate the area of a circle
public class Circle implements Measurable {
	// constructor takes arguments appropriate for creating a 2D

	protected double radius;	// will be used in circle and sphere
	private double circle_area = 0;
	
	public Circle (double x) {
		this.radius = x;
	}
	
	@Override
	public double getArea(){
		// return area of circle
		this.circle_area = 3.14*radius*radius;
		return circle_area;
	}
}
