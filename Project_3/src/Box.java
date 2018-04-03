// calculate the area of a box using rectangle
public class Box extends Rectangle{
	// provide class constructor that takes arguments appropriate for 3D box
	private double side_3;
	private double box_sa = 0;
	
	public Box (double x, double y, double z) {
		super (x, y);	// from rectangle
		
		this.side_3 = z;
	}
	
	@Override
	public double getArea(){
		// return surface area
		box_sa = super.getArea() * side_3; 
		return box_sa;
	}
}
