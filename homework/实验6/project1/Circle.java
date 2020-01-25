package project1;

public class Circle extends GeometricObject{
	private double radius;
	
	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	@Override
	public double getArea() {
		double s;
		s = Math.PI*radius*radius;
		return s;
	}

	@Override
	public double getPerimeter() {
		double l;
		l = 2*Math.PI*radius;
		return l;
	}

}
