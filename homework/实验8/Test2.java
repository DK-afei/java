package zuoye;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("e://Exercise17_02.dat");
		int[] b = new int[100];
		int sum = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			for(int i=0; i<100; i++)
			{
				b[i] = dis.readInt();
				sum += b[i];
			}
			System.out.println("sum: "+sum);
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}
