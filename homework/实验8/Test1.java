package zuoye;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("e://Exercise17_02.dat");
		if(file.exists())
		{
			System.out.println("文件已存在！");
		}
		int[] a = new int[100];
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
			for(int i=0; i<100; i++)
			{
				a[i] = (int)(Math.random()*10);
				dos.writeInt(a[i]);
			}
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] b = new int[100];
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			for(int i=0; i<100; i++)
			{
				b[i] = dis.readInt();
				System.out.print(b[i]+" ");
			}
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}
