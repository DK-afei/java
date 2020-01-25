package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fp = new File("Exercise12_15.txt");
		if(fp.exists())
		{
			System.out.println("File already exits");
			System.exit(1);
		}
		Scanner in = new Scanner(System.in);
		int[] a = new int [100];
		for(int i=0; i<a.length; i++)
		{
			a[i] = (int)(Math.random()*10);
		}
		try
		{
			PrintWriter output = new PrintWriter(fp);
			for(int i=0; i<a.length; i++)
			{
				output.print(a[i]+" ");
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] b = new int [100];
		try {
			Scanner input = new Scanner(fp);
			for(int i=0; i<b.length; i++)
			{
				b[i] = input.nextInt();
			}
			Arrays.sort(b);
			for(int k : b)
			{
				System.out.print(k+" ");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
