package project1;

public class Triangle extends GeometricObject{
	private double a;
	private double b;
	private double c;
	
	public Triangle(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public double getArea() {
		double s;
		double p;
		p = (a+b+c)/2;
		s = Math.sqrt((p-a)*(p-b)*(p-c));
		return s;
	}

	@Override
	public double getPerimeter() {
		double l;
		l = a + b + c;
		return l;
	}

}
