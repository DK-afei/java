package project;

import java.util.Arrays;
import java.util.Scanner;

public class Test0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[10];
		Scanner in = new Scanner(System.in);
		System.out.println("Enter ten integers:");
		for(int i=0; i<a.length; i++)
		{
			a[i] = in.nextInt();
		}
		Arrays.sort(a);
		for( int i=0; i<a.length; i++)
		{
			System.out.print(a[i]+" ");
		}
		int c[] = trim(a);
		System.out.println();
		System.out.println("The distinct Integers are:");
		for( int i=0; i<c.length; i++)
		{
			System.out.print(c[i]+" ");
		}
	}

	private static int[] trim(int[] a) {
		// TODO Auto-generated method stub
		int i,j,k,ln;
		System.out.println();
		for( ln=a.length,k=i=1; i<ln; i++)
		{
			for(j=0; j<k; j++)
				if(a[j]==a[i])
					break;
				if(j>=k)
					a[k++]=a[i];
		}
			for(i=0; i<k; System.out.printf("%d ", a[i++]));
		int[] b = new int[k];
		for(int z=0; z<k; z++)
		{
			b[z] = a[z];
		}
		return b;
	}

}
