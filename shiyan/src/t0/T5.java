package t0;

import java.util.Scanner;
	
public class T5 {
	public static int ff(int n)
	{
		if(n==1)
			return 1;
		if(n==2)
			return 1;
		else
			return ff(n-2)+ff(n-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int n;
		Scanner in = new Scanner(System.in);
		System.out.println("input n:");
		n=in.nextInt();
		for(i=1;i<=n;i++)
		{
			long startTime=System.nanoTime(); 
			System.out.printf("%d:%d\n",i,ff(i));
			long endTime=System.nanoTime(); 
			System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
		}
	}

}

