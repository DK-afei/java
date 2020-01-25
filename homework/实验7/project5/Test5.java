package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fp = new File("e:\\Lincoln.txt");
		if(fp.exists())
		{
			System.out.println("File already exits");
			System.exit(1);
		}
		try {
			PrintWriter output = new PrintWriter(fp);
			output.print("abcdefghijklmnopqrstuvwxyz");
			output.print("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer s = new StringBuffer();
		try {
			Scanner input = new Scanner(fp);
			while(input.hasNext())
			{
				s.append(input.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		char c = 'A';
		for(int i=0;;i++)
		{
			int n = 0;
			for(int j=0;j<s.length();j++)
			{
				if(s.charAt(j)==c)
				{
					n++;
				}
			}
			System.out.println(c+":"+n);
			c++;
			if(c>'z')
				break;
		}
	}

}
