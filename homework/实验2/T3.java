

import java.util.Scanner;

public class T3 {

	public static void main(String[] args) {
		//P240 7.30
		Scanner in = new Scanner(System.in);
		int i = 0;
		int b = 0;
    	int m = 0;
		boolean flag = false;
		System.out.println("请输入数的数量：");
		int num = in.nextInt();
		int[] c = new int[100];
		
		while(i<num){
			b = in.nextInt();
				c[i++] = b;
		}
		int[] a = new int[i];
		for(int n=0; n<a.length;n++){
			a[n] = c[n];
		}
		
		for(int j=0; j<a.length; j++){
			    m=0;
			for(int k=j; k<a.length-1; k++){
				if(a[k]==a[k+1])
					m++;
			}
			if(m==3)
				flag=true;
		}
		
		if(flag)
			System.out.println("存在四个连续整数");
		else
				System.out.println("不存在四个连续整数");

	}

}
