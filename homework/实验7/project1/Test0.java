package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while(true)
			{
				try
				{
					System.out.println("请输入两个整数：");
					int a = in.nextInt();
					int b = in.nextInt();
					System.out.println(a+b);
					break;
				}
				catch(InputMismatchException e)
				{
					String s = in.nextLine();
					continue;
				}
			}
	}
}
