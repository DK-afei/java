package project2;


public class Rectangle extends GeometricObject{
	private double width;
	private double height;
	
	public Rectangle() {}
	

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public Rectangle(double width, double height,String color,boolean filled) {
		super(color,filled);
		this.width = width;
		this.height = height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public double getWidth() {
		return width;
	}


	public double getHeight() {
		return height;
	}


	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getPerimeter() {
		return 2*(width + height);
	}
	public boolean equals(Rectangle r) {
		if(this.getArea()==r.getArea())
		return true;
		else
		return false;
	}

	
}