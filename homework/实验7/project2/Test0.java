package project2;

import java.util.Random;
import java.util.Scanner;

public class Test0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int[] a = new int [100];
		for(int i=0; i<a.length; i++)
		{
			a[i] = (int)(Math.random()*10);
		}
		try
		{
			System.out.println("�����������±��Բ鿴Ҫ�鿴��ֵ��");
			int b = in.nextInt();
			System.out.println(a[b]);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println(("Out of Bounds"));
		}
	}

}
