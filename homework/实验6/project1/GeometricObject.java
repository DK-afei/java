package project1;

import java.util.Date;

public abstract class GeometricObject implements Comparable<GeometricObject> {
	private String color = "white";
	private boolean filled;
	private Date dateCreated;
	protected GeometricObject() {
		dateCreated = new Date();
	}
	protected GeometricObject(String color, boolean filled) {
		dateCreated = new Date();
		this.color = color;
		this.filled = filled;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isFilled() {
		return filled;
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	@Override
	public String toString() {
		return "created on"+dateCreated+"\ncolor: "+color+" and filled: "+filled;
	}
	public abstract double getArea();
	public abstract double getPerimeter();
	public static int max(GeometricObject g1,GeometricObject g2) {
		if(g1.getArea()>g2.getArea())
			return 1;
		else if(g1.getArea()==g2.getArea())
			return 0;
		else
			return -1;
	}
	@Override
	public int compareTo(GeometricObject g) {
		if(this.getArea()>g.getArea())
			return 1;
		else if(this.getArea()==g.getArea())
			return 0;
		else
			return -1;
	}
	
}
