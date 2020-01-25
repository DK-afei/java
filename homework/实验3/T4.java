public class T4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegularPolygon r0 = new RegularPolygon();
		RegularPolygon r1 = new RegularPolygon(6,4);
		RegularPolygon r2 = new RegularPolygon(10,4,5.6,7.8);
		System.out.println("第一个正多边形的周长和面积为：");
		System.out.println(r0.getPerimeter()+","+r0.getArea());
		System.out.println("第二个正多边形的周长和面积为：");
		System.out.println(r1.getPerimeter()+","+r1.getArea());
		System.out.println("第三个正多边形的周长和面积为：");
		System.out.println(r2.getPerimeter()+","+r2.getArea());
	}

}
class RegularPolygon
{
	private int n = 3;
	private double size = 1;
	private double x  = 0;
	private double y = 0;
	RegularPolygon(){}
	RegularPolygon(int n,double size)
	{
		this.n = n;
		this.size  = size;
	}
	RegularPolygon(int n,double size,double x,double y)
	{
		this.n = n;
		this.size  = size;
		this.x = x;
		this.y = y;
	}
	public void setN(int n)
	{
		this.n = n;
	}
	public void setSize(double size)
	{
		this.size = size;
	}
	public void setZuoBiao(double x,double y)
	{
		this.x = x;
		this.y  = y;
	}
	public int getN()
	{
		return n;
	}
	public double getSize()
	{
		return size;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getPerimeter()
	{
		double l;
		l = n*size;
		return l;
	}
	public double getArea()
	{
		double s;
		s = n*size*size/(4*Math.tan(Math.PI/n));
		return s;
	}
}