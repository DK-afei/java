package c;

import java.util.Scanner;

//����㷨�����������ͼ��������������·����ͬʱ���·��(��㵽���е��)
public class c1 {

	public static String dijkstra(int[][] W1, int start, int end) {

		System.out.println("���:" + (start+1) + "�յ�:" + (end+1));
		boolean[] isLabel = new boolean[W1[0].length];// �Ƿ���
		int[] indexs = new int[W1[0].length];// ���б�ŵĵ���±꼯�ϣ��Ա�ŵ��Ⱥ�˳����д洢��ʵ������һ���������ʾ��ջ
		int i_count = -1;// ջ�Ķ���
		int[] distance = W1[start].clone();// v0���������̾���ĳ�ʼֵ
		int index = start;// �ӳ�ʼ�㿪ʼ
		int presentShortest = 0;// ��ǰ��ʱ��̾���

		indexs[++i_count] = index;// ���Ѿ���ŵ��±�����±꼯��
		isLabel[index] = true;

		while (i_count < W1[0].length) {
// ��һ�����õ���ԭ�������ĳ����
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < distance.length; i++) {
				if (!isLabel[i] && distance[i] != -1 && i != index) {
// �����������б�,����û�б����
					if (distance[i] < min) {
						min = distance[i];
						index = i;// ���±��Ϊ��ǰ�±�
					}
				}
			}
			i_count = i_count + 1;
			if (i_count == W1[0].length) {
				break;
			}
			isLabel[index] = true;// �Ե���б��
			indexs[i_count] = index;// ���Ѿ���ŵ��±�����±꼯��

			if (W1[indexs[i_count - 1]][index] == -1
					|| presentShortest + W1[indexs[i_count - 1]][index] > distance[index]) {
// ���������û��ֱ�������������������·���������·��
				presentShortest = distance[index];
			} else {
				presentShortest += W1[indexs[i_count - 1]][index];
			}

// �ڶ���������vi�����¼���distance�еľ���
			for (int i = 0; i < distance.length; i++) {

// ���vi���Ǹ����бߣ���v0�������ľ����
				if (distance[i] == -1 && W1[index][i] != -1) {// �����ǰ���ɴ�����ڿɴ���
					distance[i] = presentShortest + W1[index][i];
				} else if (W1[index][i] != -1 && presentShortest + W1[index][i] < distance[i]) {
// �����ǰ�ɴ�����ڵ�·������ǰ���̣�������ɸ��̵�·��
					distance[i] = presentShortest + W1[index][i];
				}

			}

		}
		getRoute(W1, indexs, end);
		return "��̾����ǣ�" + (distance[end] - distance[start]);
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
		System.out.println("����������ߵĶ����Ȩ�أ�");
		System.out.println("1-������,2-����ʱ��,3-����,4-����,5-����¥,6-ͼ��ݣ�7-һʳ�ã�8-���ţ�9-һ�����Ḷ̌�10-ʵ��¥��11-�ٳ�");
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
// ����һ��Ȩֵ����
		int[][] W1 = { // ��������1
				{ 0, 1, 4, -1, -1, -1 }, 
				{ 1, 0, 2, 7, 5, -1 }, 
				{ 4, 2, 0, -1, 1, -1 },
				{ -1, 7, -1, 0, 3, 2 },
				{ -1, 5, 1, 3, 0, 6 }, 
				{ -1, -1, -1, 2, 6, 0 } };
		int[][] W = { // ��������2
				{ 0, 1, 3, 4 }, 
				{ 1, 0, 2, -1 }, 
				{ 3, 2, 0, 5 },
				{ 4, -1, 5, 0 } };
		int[][] W0 = { // ��������3
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
//		System.out.println("�����ͼ�Ķ������ͱ�����");
//		m = in.nextInt();
//		n = in.nextInt();
//		int[][] W0 = maketree(m, n);
//		p(W0);
		int i,j;
		while(true) {
		System.out.println("������ָ�������յ㣺");
		System.out.println("ע�⣺1-������,2-����ʱ��,3-����,4-����,5-����¥,6-ͼ��ݣ�7-һʳ�ã�8-���ţ�9-һ�����Ḷ̌�10-ʵ��¥��11-�ٳ�");
		i = in.nextInt();
		j = in.nextInt();
		if(i==j)
			break;
			System.out.println(dijkstra(W0, i-1, j-1)); // (int[][] W1, int start, int end)
		}
	}

// indexs:1,0,2,4,3,5 �Ŷ����˳��
// end:���Ҫ�Ķ�������:5
// routeLength:����:8
	/**
	 * seven ���·��(��㵽���е��)
	 */
	public static String getRoute(int[][] WW, int[] indexs, int end) {
		String[] routeArray = new String[indexs.length];
		for (int i = 0; i < routeArray.length; i++) {
			routeArray[i] = "";
		}
		
//�Լ���·��
		routeArray[indexs[0]] = indexs[0] + "";
		for (int i = 1; i < indexs.length; i++) {
//���õ���ǰ�����е���������е����·����Ȼ��õ������·���������������ĸ��㣬�����˵��route�����ҳ��ǵ��route+�˵�
			int[] thePointDis = WW[indexs[i]];
			int prePoint = 0;

			int tmp = 9999;
			for (int j = 0; j < thePointDis.length; j++) {

				boolean chooseFlag = false;
//�ߵľ�����̣����ң������ĵ���ǰ��ĵ㵱��
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
					System.out.print("������"+" ");
				if(myIntArr[j]==2)
					System.out.print("����ʱ��"+" ");
				if(myIntArr[j]==3)
					System.out.print("����"+" ");
				if(myIntArr[j]==4)
					System.out.print("����"+" ");
				if(myIntArr[j]==5)
					System.out.print("����¥"+" ");
				if(myIntArr[j]==6)
					System.out.print("ͼ���"+" ");
				if(myIntArr[j]==7)
					System.out.print("һʳ��"+" ");
				if(myIntArr[j]==8)
					System.out.print("����"+" ");
				if(myIntArr[j]==9)
					System.out.print("һ�����Ľ�"+" ");
				if(myIntArr[j]==10)
					System.out.print("ʵ��¥"+" ");
				if(myIntArr[j]==11)
					System.out.print("�ٳ�"+" ");

			}
			System.out.println();
		}
		return "";
	}
}
