package a;

import java.util.Arrays;

public class a6_1 {
	static int [] jdg = new int[10];//访问数组，判断是否被访问
	static int [][] a = new int[3][3];//九宫格数组
	static int [][][] b = new int[100][3][3];//符合要求的结果数组
	static boolean flag=false;//判断标志
	static int count=0;//计算正确结果数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init1(jdg);//初始化访问数组全为0
		init2(a);//初始化九宫格数组全为0
		System.out.println("深度优先求解九宫格的过程如下：");
		dfs(0);
		System.out.println("最后总共有"+count+"种结果，如下：");
		for(int k=0;k<count;k++)
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					System.out.printf("%d ",b[k][i][j]);
				}
				System.out.println();
			}
			System.out.println();
			
		}
		
	}
	//初始化函数1
	static void init1(int[] c)
	{
		for(int i=0;i<c.length;i++)
		{
			c[i]=0;
		}
	}
	//初始化函数2
	static void init2(int[][] c)
	{
		for(int i=0;i<c.length;i++)
		{
			for(int j=0;j<c[i].length;j++)
			c[i][j]=0;
		}
	}
	//判断函数，判断目前的九宫格是否满足每一行每一列以及对角线上的和都相等
	static int judge()
	{
		if(a[0][2]+a[1][2]+a[2][2]!=15)
			return 0; 
		if(a[0][0]+a[1][1]+a[2][2]!=15) 
			return 0;
		if(a[0][2]+a[1][1]+a[2][0]!=15)
			return 0;
		return 1;
	}
	//深度优先算法
	static void dfs(int n)
	{
		int i,j;
		if(n%3==0 && n!=0)
		{
			if(a[n/3-1][0]+a[n/3-1][1]+a[n/3-1][2]!=15)
				return ;
		}
			if(n/3==2 && (n%3==1 || n%3==2))
		{
			if((a[(n-1)/3-2][(n-1)%3]+a[(n-1)/3-1][(n-1)%3]+a[(n-1)/3][(n-1)%3])!=15)
				return ;
		}
		if(n>8)//找到一种满足的结果，并保存下来
		{
			if(judge()==1)
			{
				for(i=0;i<3;i++)
				{
					for(j=0;j<3;j++)
					{
						b[count][i][j]=a[i][j];
					}
				}
				count++;
				
			}
			return ;
		}
		for(i=1;i<10;i++)
		{
			//打印深度优先遍历的过程
			for(int m=0;m<3;m++)
			{
				for(int p=0;p<3;p++)
				{
					System.out.printf("%d ",a[m][p]);
				}
				System.out.println();
			}
			for(int k=0;k<10;k++)
				System.out.print(jdg[k]+" ");
			System.out.println();
			
			//给访问数组赋值，并递归调用深度优先算法知道得到所有正确的九宫格结果
			if(jdg[i]==0)
				flag=true;
			else
				flag=false;
			if(flag)
			{
				a[n/3][n%3]=i;
				jdg[i]=1;
				dfs(n+1);
				jdg[i]=0;
			}
		}
	}
}
