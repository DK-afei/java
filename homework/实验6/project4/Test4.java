package project4;

import java.util.Scanner;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the first complex number:");
		double a = in.nextDouble();
		double b = in.nextDouble();
		System.out.print("Enter the second complex number:");
		double c = in.nextDouble();
		double d = in.nextDouble();
		Complex c1 = new Complex(a,b);
		Complex c2 = new Complex(c,d);
		System.out.println(c1.add(c2));
		System.out.println(c1.substract(c2));
		System.out.println(c1.multiply(c2));
		System.out.println(c1.divide(c2));
		System.out.println(c1.abs());
	}

}
