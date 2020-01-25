package t0;

import java.util.Scanner;

public class T4 {

	public static int f1(int m, int n)
	{
		if(n==0)
			return m;
		else
			return f1(n,m%n);
	}
	public static int f2(int m, int n)
	{
		int t;
		if(m<n)
		{
			t=m;
			m=n;
			n=t;
		}
		if(m-n==0)
			return m;
		else
			return f2(m-n,n);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m,n;
		Scanner in = new Scanner(System.in);
		while(true) {
		System.out.println("input m,n:");
		m=in.nextInt();
		n=in.nextInt();
		
		long startTime=System.nanoTime(); 
		System.out.printf("(除法版)%d, %d : %d\n",m,n,f1(m,n));
		long endTime=System.nanoTime(); 
		System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
		startTime=System.nanoTime(); 
		System.out.printf("(减法版)%d, %d : %d\n",m,n,f2(m,n));
		endTime=System.nanoTime(); 
		System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
		if(m==0&&n==0)
			break;
		}
	}

}


