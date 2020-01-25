package t0;

import java.util.Scanner;

public class T2 {
	public static int f(int m, int n)
	{
		if(n==0)
			return m;
		else
			return f(n,m%n);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m,n;
		Scanner in = new Scanner(System.in);
		while(true)
		{
			System.out.println("input m,n:");
			m=in.nextInt();
			n=in.nextInt();
			System.out.printf("%d, %d : %d\n",m,n,f(m,n));
			if(m==0&&n==0)
				break;
		}
	}
}