package dengxiaoping_1_7;

import java.util.Scanner;

public abstract class T7 {

	public static void main(String[] args) {
		// P170 5.51
		int i = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first string:");
		String s1 = in.nextLine();
		System.out.println("Enter the second string:");
		String s2 = in.nextLine();
		while(true)
		{
			if(s1.charAt(i)==s2.charAt(i))
			{
				i++;
			}
			else
				break;
				
		}
		System.out.println("The common prefix is "+s1.substring(0, i));
		if(i==0)
		{
			System.out.printf("%s and %s hava no common prefix",s1,s2);
		}
	}

}
