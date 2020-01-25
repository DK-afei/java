package zuoye;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File a = new File("e://t1.txt");
		File b = new File("e://t2.txt");
		try {
				Change.changeTo(a, b);
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				System.out.println(a.getName()+" : "+a.length());
		System.out.println(b.getName()+" : "+b.length());
	}

}
class Change
{
	public static void changeTo(File a,File b)throws IOException
	{
		StringBuilder s = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(a),"utf-8")); 
		bw.write("你好");
		bw.newLine();
		bw.write("java");
		bw.newLine();
		bw.write("我是张三");
		bw.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(a),"utf-8"));
		while(true)
		{
			s.append(br.readLine());
			if(br.read()==-1)
				break;
					
		}
		br.close();
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(b));
		dos.writeUTF(s.toString());
		dos.close();
		
	}
}