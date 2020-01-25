package dengxiaoping_1_4;

import java.util.Scanner;

public class T4 {

	public static void main(String[] args) {
		//P94 3.11
		Scanner in = new Scanner(System.in);
		System.out.println("请输入年份：");
		int y = in.nextInt();
		System.out.println("请输入月份：");
		int m = in.nextInt();
		switch(m)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				System.out.printf("%d年的%d月有31天",y,m);
				break;
			case 2:
				if((y%4==0&&y%100!=0)||y%400==0)
					System.out.printf("%d年的%d月有29天",y,m);
				else 
					System.out.printf("%d年的%d月有28天",y,m);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.printf("%d年的%d月有30天",y,m);
				break;
		}
	}

}
