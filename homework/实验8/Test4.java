package zuoye;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入源文件名：");
		String s1 = in.nextLine();
		System.out.println("请输入目标文件名：");
		String s2 = in.nextLine();
		File f1 = new File("e://"+s1);
		File f2 = new File("e://"+s2);
		byte[] b = new byte[1024*1024];
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
			bis.read(b);
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
			for(int i=0; i<b.length; i++)
			{
				b[i] = (byte) (b[i] + 5);	
			}
			bos.write(b);
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
