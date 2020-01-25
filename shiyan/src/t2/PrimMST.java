package t2;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 
/**
 * ͼ����С�������㷨
 * @author win7
 *
 */
public class PrimMST {
	/**
	 * ��ͼ��С��������PRIM�㷨
	 * ����˼�룺����N=(V,{E})����ͨ����TE��N�ϵ������������еı�ü��ϡ��㷨��U={u0}(u0����V)��
	 * TE={}��ʼ���ظ�ִ�����������������е�u����U��v����V-U�ıߣ�u��v������E���ҵ�һ��������С
	 * �ıߣ�u0��v0�����뼯��TE��ͬ��v0����U��ֱ��U=VΪֹ����ʱTE�б���n-1���ߣ���T=(V,{TE})
	 * ΪN����С��������
	 * @param  graph  ͼ
	 * @param start ��ʼ�ڵ�
	 * @param n     ͼ�нڵ���
	 */
/*
1 2 5
1 3 7
1 5 2
2 4 6
2 5 3
3 5 4
3 4 4
4 5 5
*/

	static char[] cc= {'a','b','c','d','e','f','g','h','i','j','k','l'};
	public static void PRIM(int [][] graph,int start,int n){
		int [][] mins=new int [n][2];//���ڱ��漯��U��V-U֮�����С�ߺ�����ֵ��mins[i][0]ֵ��ʾ���ýڵ�i�ߵ���ʼ�ڵ�
		                             //ֵΪ-1��ʾû�е�������ʼ�㣬mins[i][1]ֵ��ʾ���ñߵ���Сֵ��
		                             //mins[i][1]=0��ʾ�ýڵ��ѽ��ڼ���U��
		for(int i=0;i<n;i++){//��ʼ��mins
		
			if(i==start){
				mins[i][0]=-1;
				mins[i][1]=0;
			}else if( graph[start][i]!=-1){//˵�����ڣ�start��i���ı�
				mins[i][0]=start;
				mins[i][1]= graph[start][i];
			}else{
				mins[i][0]=-1;
				mins[i][1]=Integer.MAX_VALUE;
			}
//			System.out.println("mins["+i+"][0]="+mins[i][0]+"||mins["+i+"][1]="+mins[i][1]);
		}
		for(int i=0;i<n-1;i++){
			int minV=-1,minW=Integer.MAX_VALUE;
			for(int j=0;j<n;j++){//�ҵ�mins����Сֵ��ʹ��O(n^2)ʱ��
				
			    if(mins[j][1]!=0&&minW>mins[j][1]){
					minW=mins[j][1];
					minV=j;
				}
			}
//			System.out.println("minV="+minV);
			mins[minV][1]=0;
			System.out.println("��С�������ĵ�"+i+"����С��=<"+cc[(mins[minV][0]+1)-1]+","+cc[(minV+1)-1]+">��Ȩ��="+minW);
			for(int j=0;j<n;j++){//����mins����
				if(mins[j][1]!=0){
//					System.out.println("MINV="+minV+"||tree[minV][j]="+tree[minV][j]);
					if( graph[minV][j]!=-1&& graph[minV][j]<mins[j][1]){
						mins[j][0]=minV;
						mins[j][1]= graph[minV][j];
					}
				}
			}
		}
	}	
	public static int[][] maketree(int m,int n)
	{
		int [][] tree = new int[m][m];
		int [][] t= new int[n][3];
		Scanner in=new Scanner(System.in);
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
					tree[i][j]=-1;
			}
		}
		System.out.println("����������ߵĶ����Ȩ�أ�");
		System.out.println("1-a,2-b,3-c,4-d,5-e,6-f,����");
		for(int i=0;i<n;i++)
		{
			t[i][0]=in.nextInt();
			t[i][1]=in.nextInt();
			t[i][2]=in.nextInt();
		}
		for(int k=0;k<n;k++)
		{
			for(int i=0;i<m;i++)
			{
				for(int j=0;j<m;j++)
				{
					if(t[k][0]==(i+1)&&t[k][1]==(j+1))
					{
						tree[i][j]=t[k][2];
						tree[j][i]=t[k][2];
					}
				}
			}
		}
		return tree;
	}
	public static void p(int [][] tree)
	{
		for(int i=0;i<tree.length;i++)
		{
			for(int j=0;j<tree[i].length;j++)
			{
				System.out.print(tree[i][j] +" ");
			}
			System.out.println();
		}
	}
	public static void main(String [] args){
		int m,n;
		Scanner in=new Scanner(System.in);
		System.out.println("�����ͼ�Ķ������ͱ�����");
		m=in.nextInt();
		n=in.nextInt();
		PrimMST.PRIM(maketree(m,n), 0, m);
		System.out.println("+++++++++++++++++++++++++++++++++");
	}
 
}