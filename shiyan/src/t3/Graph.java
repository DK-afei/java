package t3;

import java.util.*;
 
/**
 * Dijkstra�㷨��ⵥԴ���·��
 * @author sdu20
 *
 */
 
public class Graph {
 
	private LinkedList<Edge>[] edgeLinks;
	private int vNum;	//������
	private int edgeNum;	//����
	private int[] distance;	//���v.d
	private int[] prenode;	//���ǰ���ڵ�
	private LinkedList<Integer> S;	//�Ѿ������·���Ķ��㼯��
	private LinkedList<Integer> Q;	//��δ�����·���Ķ��㼯��
	public static final int INF = 10000;	//�����
	public static final int NIL = -1;	//��ʾ������
	static char[] cc= {'a','b','c','d','e','f','g','h','i','j','k','l'};
	public Graph(int vnum){
		this.vNum = vnum;
		edgeLinks = new LinkedList[vnum];
		edgeNum = 0;
		distance = new int[vnum];
		prenode = new int[vnum];
		for(int i = 0;i<vnum;i++)
			edgeLinks[i] = new LinkedList<>();
	}
	
	public void insertEdge(Edge edge){		
		int v1 = edge.getV1();
		edgeLinks[v1].add(edge);
		edgeNum++;		
	}
	
	public void bianli(){
		System.out.println("���� "+vNum+" �����㣬 "+edgeNum+" ����");
		for(int i = 0;i<vNum;i++){
			LinkedList<Edge> list = (LinkedList<Edge>) edgeLinks[i].clone();
			while(!list.isEmpty()){
				Edge edge = list.pop();
				System.out.println(edge.toString());
			}
		}
	}
	
	/**
	 * �����·�����ƺ�ǰ���ڵ���г�ʼ��
	 * @param start
	 */
	public void INITIALIZE_SINGLE_SOURCE(int start){
		for(int i = 0;i<vNum;i++){
			distance[i] = INF;
			prenode[i] = NIL;
		}
		distance[start] = 0;
	}
	
	/**
	 * �ɳ�
	 * @param edge
	 */
	public void RELAX(Edge edge){
		int v1 = edge.getV1();
		int v2 = edge.getV2();
		int w = edge.getWeight();
		if(distance[v2]>distance[v1]+w){
			distance[v2] = distance[v1]+w;
			prenode[v2] = v1;
		}
	}
	
	/**
	 * Dijkstra�㷨ʵ��
	 * @param start
	 */
	public void DIJKSTRA(int start){
		
		INITIALIZE_SINGLE_SOURCE(start);
		
		S = new LinkedList<>();
		Q = new LinkedList<>();
		for(int i = 0;i<vNum;i++){
			Q.add(i);
		}
		
		while(!Q.isEmpty()){
			int u = EXTRACT_MIN(Q);
			S.add(u);
			LinkedList<Edge> list = (LinkedList<Edge>) edgeLinks[u].clone();
			while(!list.isEmpty()){
				Edge edge = list.pop();
				RELAX(edge);
			}
		}
		
		ShowResult();
	
	}
	
	private int EXTRACT_MIN(LinkedList<Integer> q){
		
		if(q.isEmpty())
			return -1;
		
		int min = q.getFirst();
		for(int i = 0;i<q.size();i++){
			int v = q.get(i);
			if(distance[min]>distance[v]){
				min = v;
			}
		}
		int min2 = min;
		q.remove(q.indexOf(min));
		return min;
	}
	
	private void ShowResult(){
		System.out.println("=========Result==========");
		Stack<Integer>[] routes = new Stack[vNum];
		for(int i = 0;i<vNum;i++){
			routes[i] = new Stack<>();
			int j = i;
			while(j != NIL){
				routes[i].push(j);
				j = prenode[j];
			}
			
			System.out.print(cc[i]+"("+distance[i]+") : ");
			while(!routes[i].isEmpty()){
				int k = routes[i].pop();
				System.out.print("-->"+cc[k]);
			}
			System.out.println();
		}	
	}
}