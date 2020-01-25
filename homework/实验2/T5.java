
import java.util.Scanner;

public class T5 {

	public static void main(String[] args) {
		//262 8.14
		Scanner in = new Scanner(System.in);
		int sum1 = 0;
		int sum2 = 0;
		System.out.println("请输入矩阵行列数:");
		int n = in.nextInt();
		int[][] a = new int [n][n];
		System.out.println("请输入矩阵元素:");
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				a[i][j] = in.nextInt();
			}
		}
		for(int i=0; i<n; i++)
		{
			int sum = 0;
			for(int j=0; j<n; j++)
			{
				sum += a[i][j];
			}
			if(sum==n)
				System.out.println("all 1 is in row"+i);
			else if(sum==0)
				System.out.println("all 0 is in row"+i);
		}
		for(int i=0; i<n; i++)
		{
			int sum = 0;
			for(int j=0; j<n; j++)
			{
				sum += a[j][i];
			}
			if(sum==n)
				System.out.println("all 1 is in column"+i);
			else if(sum==0)
				System.out.println("all 0 is in column"+i);
		}
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(i==j)
				sum1 += a[i][j];
				if((i+j)==(n-1))
				sum2 += a[i][j];
			}
			if(sum1==n)
				System.out.println("all 1 is in diagonal");
			if(sum1==0)
				System.out.println("all 0 is in diagonal");
			if(sum2==n)
				System.out.println("all 1 is in  counter-diagonal");
			if(sum2==0)
				System.out.println("all 0 is in  counter-diagonal ");
		}

	}

}
