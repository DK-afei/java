package b;

import java.util.Scanner;

public class b2_1 {
	static int [][] data=new int[6][6];//数值数组
	static int [][] dt=new int[6][6];//距离数组
	static int [][] dr=new int[6][6];//方向数组，0-向下，1-向右
	static void print(int [][] a)
	{
		
		for(int i=1;i<a.length;i++) {
			for(int j=1;j<a[i].length;j++)
			{
				System.out.printf("%2d ",a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
			int i,j,n;
			int count=1;
			System.out.println("请输入数塔元素的行数：");
			n=in.nextInt();
			for(i = 1;i<=n;i++)
			{
				for(j = 1;j<=i;j++){
					data[i][j]=in.nextInt();     //输入数塔元素的值
					dt[i][j] = data[i][j];
					dr[i][j] = 0;    //初始化方向向下走 
				}
			}
			System.out.println("最原始的数塔、距离以及方向数组如下");
			print(data);
			print(dt);
			print(dr);
			for(i = n-1;i>=1;i--){
				for(j = 1;j<=i;j++){
					if(dt[i+1][j] > dt[i+1][j+1])
					{
						dt[i][j] = dt[i][j] + dt[i+1][j];
						dr[i][j] = 0;
					}
					else
					{
						dt[i][j] = dt[i][j] + dt[i+1][j+1];
						dr[i][j] = 1;
					}
					System.out.println("第"+count+"步：");
					count++;
					print(data);
					print(dt);
					print(dr);
					
				}
			} 
			System.out.println("max distance:"+dt[1][1]);
			j = 1;
			System.out.println("路线：");
			for(i = 1;i<=n-1;i++){
				System.out.println(data[i][j]+"->");
				j = j + dr[i][j];
			}
			System.out.println(data[n][j]);
	}

}
/*

5
9
12 15
10 6 8
2 18 9 5
19 7 10 4 16
*/