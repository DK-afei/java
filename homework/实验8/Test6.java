package zuoye;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test6 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个ASCALL文本文件名：");
		String s0 = in.nextLine();
		StringBuilder s1 = new StringBuilder();
		int n = 0;
		File f = new File(s0);
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNext())
			{
				s1.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(char c = 0; c<256; c++)
		{
			n = 0;
			for(int j=0;j<s1.length();j++)
			{
				if(s1.charAt(j)==c)
				{
					n++;
				}
			}
			System.out.print(c+" : "+n+" ");
			if(c%10==0)
				System.out.println();
		}
	}

}

