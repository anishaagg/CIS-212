// calculate the area of a sphere using circle
public class Sphere extends Circle {
	// provide class constructor that takes arguments appropriate for 3D box
	double sphere_sa = 0;
	
	public Sphere (double x) {
		super (x);	// from circle
	}
	
	@Override
	public double getArea(){
		// return surface area
		this.sphere_sa = 4*3.14*radius*radius;
		return sphere_sa;
	}
		
}