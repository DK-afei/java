package t2;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 
/**
 * 图的最小树生成算法
 * @author win7
 *
 */
public class PrimMST {
	/**
	 * 求图最小生成树的PRIM算法
	 * 基本思想：假设N=(V,{E})是联通网，TE是N上的最想生成树中的变得集合。算法从U={u0}(u0属于V)，
	 * TE={}开始，重复执行下述操作：在所有的u属于U，v属于V-U的边（u，v）属于E中找到一条代价最小
	 * 的边（u0，v0）并入集合TE，同事v0并入U，直至U=V为止。此时TE中必有n-1条边，则T=(V,{TE})
	 * 为N的最小生成树。
	 * @param  graph  图
	 * @param start 开始节点
	 * @param n     图中节点数
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
		int [][] mins=new int [n][2];//用于保存集合U到V-U之间的最小边和它的值，mins[i][0]值表示到该节点i边的起始节点
		                             //值为-1表示没有到它的起始点，mins[i][1]值表示到该边的最小值，
		                             //mins[i][1]=0表示该节点已将在集合U中
		for(int i=0;i<n;i++){//初始化mins
		
			if(i==start){
				mins[i][0]=-1;
				mins[i][1]=0;
			}else if( graph[start][i]!=-1){//说明存在（start，i）的边
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
			for(int j=0;j<n;j++){//找到mins中最小值，使用O(n^2)时间
				
			    if(mins[j][1]!=0&&minW>mins[j][1]){
					minW=mins[j][1];
					minV=j;
				}
			}
//			System.out.println("minV="+minV);
			mins[minV][1]=0;
			System.out.println("最小生成树的第"+i+"条最小边=<"+cc[(mins[minV][0]+1)-1]+","+cc[(minV+1)-1]+">，权重="+minW);
			for(int j=0;j<n;j++){//更新mins数组
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
		System.out.println("请依次输入边的顶点和权重：");
		System.out.println("1-a,2-b,3-c,4-d,5-e,6-f,……");
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
		System.out.println("请输出图的顶点数和边数：");
		m=in.nextInt();
		n=in.nextInt();
		PrimMST.PRIM(maketree(m,n), 0, m);
		System.out.println("+++++++++++++++++++++++++++++++++");
	}
 
}