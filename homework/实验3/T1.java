public class T1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r0 = new Rectangle(4,40);
		Rectangle r1 = new Rectangle(3.5,35.9);
		System.out.println("第一个矩形的宽、高、面积、周长为：");
		System.out.println(r0.wedth+","+r0.height+","+r0.getArea()+","+r0.getPerimeter());
		System.out.println("第二个矩形的宽、高、面积、周长为：");
		System.out.println(r1.wedth+","+r1.height+","+r1.getArea()+","+r1.getPerimeter());
	}

}

class Rectangle
{
	 double wedth = 1;
	 double height = 1;
	Rectangle(){}
	Rectangle(double wedth, double height)
	{
		this.wedth = wedth;
		this.height = height;
	}
	public double getArea()
	{
		double s;
		s = wedth * height;
		return  s;
	}
	public double getPerimeter()
	{
		double l;
		l = 2*(wedth +height);
		return  l;
	}
}