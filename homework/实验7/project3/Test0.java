package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fp = new File("e:\\example.txt");
		if(fp.exists())
		{
			System.out.println("File already exits");
			System.exit(1);
		}
		try {
			PrintWriter output = new PrintWriter(fp);
			output.print("JohnAlice");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer t1 = new StringBuffer();
		try {
			Scanner input = new Scanner(fp);
			while(input.hasNext())
			{
				String t2 = input.nextLine();
				t1.append(t2.replace("John"," ")+"\r\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PrintWriter output = new PrintWriter(fp);
			output.print(t1);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
