package dengxiaoping_1_8;

import java.util.Scanner;

public class T8 {

	public static void main(String[] args) {
		// P60 2.6
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number between 0 and 1000:");
		int a = in.nextInt();
		System.out.println("The sum of the digits is:"+(a/100+a%100/10+a%100%10));
	}

}
