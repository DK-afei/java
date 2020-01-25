package t3;

import java.util.*;
 
/**
 * Dijkstra算法求解单源最短路径
 * @author sdu20
 *
 */
 
public class Graph {
 
	private LinkedList<Edge>[] edgeLinks;
	private int vNum;	//顶点数
	private int edgeNum;	//边数
	private int[] distance;	//存放v.d
	private int[] prenode;	//存放前驱节点
	private LinkedList<Integer> S;	//已经求到最短路径的顶点集合
	private LinkedList<Integer> Q;	//尚未求到最短路径的顶点集合
	public static final int INF = 10000;	//无穷大
	public static final int NIL = -1;	//表示不存在
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
		System.out.println("共有 "+vNum+" 个顶点， "+edgeNum+" 条边");
		for(int i = 0;i<vNum;i++){
			LinkedList<Edge> list = (LinkedList<Edge>) edgeLinks[i].clone();
			while(!list.isEmpty()){
				Edge edge = list.pop();
				System.out.println(edge.toString());
			}
		}
	}
	
	/**
	 * 对最短路径估计和前驱节点进行初始化
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
	 * 松弛
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
	 * Dijkstra算法实现
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