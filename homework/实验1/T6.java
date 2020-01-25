package dengxiaoping_1_6;

import java.util.Scanner;

public class T6 {
	
	public static void main(String[] args) {
		// P131 4.21
		int i = 0;
		int j = 0;
		int k = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a SSN:");
		String s = in.nextLine();
		if(s.matches("\\d{3}-\\d{2}-\\d{4}"))
			System.out.printf("%s is a valid social security number",s);
		else
			System.out.printf("%s is an invalid social security number",s);
//		while(true)
//		{
//			if((i==3||i==6)&&s.charAt(i)=='-')
//				k++;
//			if(Character.isDigit(s.charAt(i)))
//				j++;
//				i++;
//			if(k==2&&j==8)
//			{
//				System.out.printf("%s is a valid social security number",s);
//				break;
//			}
//			else if(i==10)
//			{
//				System.out.printf("%s is an invalid social security number",s);
//				break;
//			}
//				
//		}

	}

}
