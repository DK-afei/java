package project1;

public class Test1 {

	public static void main(String[] args) {
		Circle c1 = new Circle(1);
		Circle c2 = new Circle(2);
		System.out.println(GeometricObject.max(c1, c2));
		Triangle t1 = new Triangle(1,2,3);
		Triangle t2 = new Triangle(2,3,4);
		System.out.println(GeometricObject.max(t1, t2));
	}

}
