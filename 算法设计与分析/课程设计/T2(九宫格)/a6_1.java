package a;

import java.util.Arrays;

public class a6_1 {
	static int [] jdg = new int[10];//�������飬�ж��Ƿ񱻷���
	static int [][] a = new int[3][3];//�Ź�������
	static int [][][] b = new int[100][3][3];//����Ҫ��Ľ������
	static boolean flag=false;//�жϱ�־
	static int count=0;//������ȷ�����
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init1(jdg);//��ʼ����������ȫΪ0
		init2(a);//��ʼ���Ź�������ȫΪ0
		System.out.println("����������Ź���Ĺ������£�");
		dfs(0);
		System.out.println("����ܹ���"+count+"�ֽ�������£�");
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
	//��ʼ������1
	static void init1(int[] c)
	{
		for(int i=0;i<c.length;i++)
		{
			c[i]=0;
		}
	}
	//��ʼ������2
	static void init2(int[][] c)
	{
		for(int i=0;i<c.length;i++)
		{
			for(int j=0;j<c[i].length;j++)
			c[i][j]=0;
		}
	}
	//�жϺ������ж�Ŀǰ�ľŹ����Ƿ�����ÿһ��ÿһ���Լ��Խ����ϵĺͶ����
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
	//��������㷨
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
		if(n>8)//�ҵ�һ������Ľ��������������
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
			//��ӡ������ȱ����Ĺ���
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
			
			//���������鸳ֵ�����ݹ������������㷨֪���õ�������ȷ�ľŹ�����
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
