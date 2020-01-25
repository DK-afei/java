package zuoye;

public class T4 {

	public static void main(String[] args) {
		//P340 10.4
		MyPoint mp1 = new MyPoint();
		MyPoint mp2 = new MyPoint(10,30.5);
		System.out.println(mp1.distance2(mp2.getX(), mp2.getY()));
	}

}
class MyPoint
{
	private double x;
	private double y;
	public MyPoint()
	{
		this.x = 0;
		this.y = 0;
	}
	public MyPoint(double x,double y)
	{
		this.x = x;
		this.y = y;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double distance1(double x,double y)
	{
		double d = 0;
		d = Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
		return d;
	}
	public double distance2(double x,double y)
	{
		double d = 0;
		d = Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
		return d;
	}
	
}