package c;

import java.util.Scanner;

//这个算法用来解决无向图中任意两点的最短路径，同时输出路径(起点到所有点的)
public class c1 {

	public static String dijkstra(int[][] W1, int start, int end) {

		System.out.println("起点:" + (start+1) + "终点:" + (end+1));
		boolean[] isLabel = new boolean[W1[0].length];// 是否标号
		int[] indexs = new int[W1[0].length];// 所有标号的点的下标集合，以标号的先后顺序进行存储，实际上是一个以数组表示的栈
		int i_count = -1;// 栈的顶点
		int[] distance = W1[start].clone();// v0到各点的最短距离的初始值
		int index = start;// 从初始点开始
		int presentShortest = 0;// 当前临时最短距离

		indexs[++i_count] = index;// 把已经标号的下标存入下标集中
		isLabel[index] = true;

		while (i_count < W1[0].length) {
// 第一步：得到与原点最近的某个点
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < distance.length; i++) {
				if (!isLabel[i] && distance[i] != -1 && i != index) {
// 如果到这个点有边,并且没有被标号
					if (distance[i] < min) {
						min = distance[i];
						index = i;// 把下标改为当前下标
					}
				}
			}
			i_count = i_count + 1;
			if (i_count == W1[0].length) {
				break;
			}
			isLabel[index] = true;// 对点进行标号
			indexs[i_count] = index;// 把已经标号的下标存入下标集中

			if (W1[indexs[i_count - 1]][index] == -1
					|| presentShortest + W1[indexs[i_count - 1]][index] > distance[index]) {
// 如果两个点没有直接相连，或者两个点的路径大于最短路径
				presentShortest = distance[index];
			} else {
				presentShortest += W1[indexs[i_count - 1]][index];
			}

// 第二步：加入vi后，重新计算distance中的距离
			for (int i = 0; i < distance.length; i++) {

// 如果vi到那个点有边，则v0到后面点的距离加
				if (distance[i] == -1 && W1[index][i] != -1) {// 如果以前不可达，则现在可达了
					distance[i] = presentShortest + W1[index][i];
				} else if (W1[index][i] != -1 && presentShortest + W1[index][i] < distance[i]) {
// 如果以前可达，但现在的路径比以前更短，则更换成更短的路径
					distance[i] = presentShortest + W1[index][i];
				}

			}

		}
		getRoute(W1, indexs, end);
		return "最短距离是：" + (distance[end] - distance[start]);
	}

	public static int[][] maketree(int m, int n) {
		int[][] tree = new int[m][m];
		int[][] t = new int[n][3];
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				tree[i][j] = -1;
			}
		}
		System.out.println("请依次输入边的顶点和权重：");
		System.out.println("1-五六教,2-快乐时间,3-竹轩,4-后门,5-至善楼,6-图书馆，7-一食堂，8-大门，9-一二三四教，10-实验楼，11-操场");
		for (int i = 0; i < n; i++) {
			t[i][0] = in.nextInt();
			t[i][1] = in.nextInt();
			t[i][2] = in.nextInt();
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					if (t[k][0] == (i + 1) && t[k][1] == (j + 1)) {
						tree[i][j] = t[k][2];
						tree[j][i] = t[k][2];
					}
				}
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < m; i++) {
				if (k == i) {
					tree[k][i] = 0;
				}
			}
		}
		return tree;
	}

	public static void p(int[][] tree) {
		for (int i = 0; i < tree.length; i++) {
			for (int j = 0; j < tree[i].length; j++) {
				System.out.print(tree[i][j] + ",");
			}
			System.out.println();
		}
	}
/*w	
 	4 5
	1 2 1
	1 3 3
	1 4 4
	2 3 2
	3 4 5
*/
	
/*w1
	6 9
	1 2 1
	1 3 4
	2 3 2
	2 4 7
	2 5 5
	3 5 1
	4 5 3
	4 6 2
	5 6 1
*/
	
/*w0
11 18
1 2 80
1 6 130
1 5 190
2 3 180
2 6 110
3 4 250
3 7 190
3 6 240
4 7 260
4 9 270
4 11 250
5 8 50
6 7 190
6 9 200
7 9 100
8 10 220
9 10 210
10 11 280
 */
	public static void main(String[] args) {
// 建立一个权值矩阵
		int[][] W1 = { // 测试数据1
				{ 0, 1, 4, -1, -1, -1 }, 
				{ 1, 0, 2, 7, 5, -1 }, 
				{ 4, 2, 0, -1, 1, -1 },
				{ -1, 7, -1, 0, 3, 2 },
				{ -1, 5, 1, 3, 0, 6 }, 
				{ -1, -1, -1, 2, 6, 0 } };
		int[][] W = { // 测试数据2
				{ 0, 1, 3, 4 }, 
				{ 1, 0, 2, -1 }, 
				{ 3, 2, 0, 5 },
				{ 4, -1, 5, 0 } };
		int[][] W0 = { // 测试数据3
				{0,80,-1,-1,190,130,-1,-1,-1,-1,-1},
				{80,0,180,-1,-1,110,-1,-1,-1,-1,-1},
				{-1,180,0,250,-1,240,190,-1,-1,-1,-1},
				{-1,-1,250,0,-1,-1,260,-1,270,-1,250},
				{190,-1,-1,-1,0,-1,-1,50,-1,-1,-1},
				{130,110,240,-1,-1,0,190,-1,200,-1,-1},
				{-1,-1,190,260,-1,190,0,-1,100,-1,-1},
				{-1,-1,-1,-1,50,-1,-1,0,-1,220,-1},
				{-1,-1,-1,270,-1,200,100,-1,0,210,-1},
				{-1,-1,-1,-1,-1,-1,-1,220,210,0,280},
				{-1,-1,-1,250,-1,-1,-1,-1,-1,280,0}};
		Scanner in = new Scanner(System.in);
//		int m, n;
//		System.out.println("请输出图的顶点数和边数：");
//		m = in.nextInt();
//		n = in.nextInt();
//		int[][] W0 = maketree(m, n);
//		p(W0);
		int i,j;
		while(true) {
		System.out.println("请输入指定起点和终点：");
		System.out.println("注意：1-五六教,2-快乐时间,3-竹轩,4-后门,5-至善楼,6-图书馆，7-一食堂，8-大门，9-一二三四教，10-实验楼，11-操场");
		i = in.nextInt();
		j = in.nextInt();
		if(i==j)
			break;
			System.out.println(dijkstra(W0, i-1, j-1)); // (int[][] W1, int start, int end)
		}
	}

// indexs:1,0,2,4,3,5 放顶点的顺序
// end:最后要的顶点名称:5
// routeLength:长度:8
	/**
	 * seven 输出路径(起点到所有点的)
	 */
	public static String getRoute(int[][] WW, int[] indexs, int end) {
		String[] routeArray = new String[indexs.length];
		for (int i = 0; i < routeArray.length; i++) {
			routeArray[i] = "";
		}
		
//自己的路线
		routeArray[indexs[0]] = indexs[0] + "";
		for (int i = 1; i < indexs.length; i++) {
//看该点与前面所有点的连接线中的最短路径，然后得到该最短路径到底是连接了哪个点，进而此点的route就是找出那点的route+此点
			int[] thePointDis = WW[indexs[i]];
			int prePoint = 0;

			int tmp = 9999;
			for (int j = 0; j < thePointDis.length; j++) {

				boolean chooseFlag = false;
//边的距离最短，而且，所连的点在前面的点当中
				for (int m = 0; m < i; m++) {
					if (j == indexs[m]) {
						chooseFlag = true;
					}
				}
				if (chooseFlag == false) {
					continue;
				}
				if (thePointDis[j] < tmp && thePointDis[j] > 0) {
					prePoint = j;
					tmp = thePointDis[j];
				}
			}
			routeArray[indexs[i]] = routeArray[prePoint] + indexs[i];
		}
		for (int i = 0; i < routeArray.length; i++) {
			String myStr=routeArray[i];
			int myIntArr[]=new int[myStr.length()];
			for(int j=0;j<myStr.length();j++)
			{
				myIntArr[j]=myStr.charAt(j)-'0'+1;
				if(myIntArr[j]==1)
					System.out.print("五六教"+" ");
				if(myIntArr[j]==2)
					System.out.print("快乐时间"+" ");
				if(myIntArr[j]==3)
					System.out.print("竹轩"+" ");
				if(myIntArr[j]==4)
					System.out.print("后门"+" ");
				if(myIntArr[j]==5)
					System.out.print("至善楼"+" ");
				if(myIntArr[j]==6)
					System.out.print("图书馆"+" ");
				if(myIntArr[j]==7)
					System.out.print("一食堂"+" ");
				if(myIntArr[j]==8)
					System.out.print("大门"+" ");
				if(myIntArr[j]==9)
					System.out.print("一二三四教"+" ");
				if(myIntArr[j]==10)
					System.out.print("实验楼"+" ");
				if(myIntArr[j]==11)
					System.out.print("操场"+" ");

			}
			System.out.println();
		}
		return "";
	}
}
