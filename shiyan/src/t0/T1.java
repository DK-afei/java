package t0;

import java.util.Scanner;

public class T1 {
	public static int f(int m, int n)
	{
		int t;
		int a=0;
		if(m<n)
			t=m;
		else
			t=n;
		while(true)
		{
			if(m%t==0)
			{
				if(n%t==0)
				{
					a=t;
					break;
				}
				else 
					t--;
			}
			else
				t--;
		}
		return a;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m,n;
		Scanner in = new Scanner(System.in);
		while(true)
		{
			System.out.println("input m,n:(m*n!=0)");
			m=in.nextInt();
			n=in.nextInt();
			if(m*n!=0)
				break;
			
		}
		System.out.printf("%d, %d : %d\n",m,n,f(m,n));
	}

}
