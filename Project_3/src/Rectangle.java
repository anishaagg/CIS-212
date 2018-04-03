// calculate the area of a rectangle
public class Rectangle implements Measurable {
	// create a 2D rectangle and implement the getArea() method
	
	protected double side_1;	// will be used in rectangle and box
	protected double side_2;	// will be used in rectangle and box
	private double rectangle_area = 0;
	
	public Rectangle (double x, double y) {
		this.side_1 = x;
		this.side_2 = y;
	}

	@Override
	public double getArea() {
		// return area of rectangle
		this.rectangle_area = side_1 * side_2;
		return rectangle_area;
	}
}
