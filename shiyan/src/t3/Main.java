package t3;
 
public class Main {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		bookGraph();
	}
	
	private static void bookGraph(){
//		Graph graph = new Graph(5);
//		Edge[] edges = new Edge[10];
//		
//		edges[0] = new Edge(0,1,10);
//		edges[1] = new Edge(0,3,5);
//		edges[2] = new Edge(1,2,1);
//		edges[3] = new Edge(1,3,2);
//		edges[4] = new Edge(2,4,4);
//		edges[5] = new Edge(3,1,3);
//		edges[6] = new Edge(3,2,9);
//		edges[7] = new Edge(3,4,2);
//		edges[8] = new Edge(4,0,7);
//		edges[9] = new Edge(4,2,6);
		
//		Graph graph = new Graph(5);
//		Edge[] edges = new Edge[7];
//		
//		edges[0] = new Edge(1,0,3);
//		edges[1] = new Edge(0,3,7);
//		edges[2] = new Edge(1,2,4);
//		edges[3] = new Edge(3,1,2);
//		edges[4] = new Edge(2,4,6);
//		edges[5] = new Edge(3,2,5);
//		edges[6] = new Edge(4,3,4);
//		for(int i = 0;i<7;i++)
//			graph.insertEdge(edges[i]);
//		
//		Graph graph = new Graph(5);
//		Edge[] edges = new Edge[14];
//		
//		edges[0] = new Edge(0,1,3);
//		edges[1] = new Edge(0,3,7);
//		edges[2] = new Edge(1,2,4);
//		edges[3] = new Edge(1,3,2);
//		edges[4] = new Edge(2,3,5);
//		edges[5] = new Edge(2,4,6);
//		edges[6] = new Edge(3,4,4);
//		edges[7] = new Edge(1,0,3);
//		edges[8] = new Edge(3,0,7);
//		edges[9] = new Edge(2,1,4);
//		edges[10] = new Edge(3,1,2);
//		edges[11] = new Edge(3,2,5);
//		edges[12] = new Edge(4,2,6);
//		edges[13] = new Edge(4,3,4);
//		for(int i = 0;i<14;i++)
//			graph.insertEdge(edges[i]);
		
		Graph graph = new Graph(12);
		Edge[] edges = new Edge[40];
		
		edges[0] = new Edge(0,1,3);
		edges[1] = new Edge(0,2,5);
		edges[2] = new Edge(0,3,4);
		edges[3] = new Edge(1,4,3);
		edges[4] = new Edge(1,5,6);
		edges[5] = new Edge(2,3,2);
		edges[6] = new Edge(2,6,4);
		edges[7] = new Edge(3,4,1);
		edges[8] = new Edge(3,7,5);
		edges[9] = new Edge(4,5,2);
		edges[10] = new Edge(4,8,4);
		edges[11] = new Edge(5,9,5);
		edges[12] = new Edge(6,7,3);
		edges[13] = new Edge(6,10,6);
		edges[14] = new Edge(7,8,6);
		edges[15] = new Edge(7,10,7);
		edges[16] = new Edge(8,9,3);
		edges[17] = new Edge(8,11,5);
		edges[18] = new Edge(9,11,9);
		edges[19] = new Edge(10,11,8);
		edges[20] = new Edge(1,0,3);
		edges[21] = new Edge(2,0,5);
		edges[22] = new Edge(3,0,4);
		edges[23] = new Edge(4,1,3);
		edges[24] = new Edge(5,1,6);
		edges[25] = new Edge(3,2,2);
		edges[26] = new Edge(6,2,4);
		edges[27] = new Edge(4,3,1);
		edges[28] = new Edge(7,3,5);
		edges[29] = new Edge(5,4,2);
		edges[30] = new Edge(8,4,4);
		edges[31] = new Edge(9,5,5);
		edges[32] = new Edge(7,6,3);
		edges[33] = new Edge(10,6,6);
		edges[34] = new Edge(8,7,6);
		edges[35] = new Edge(10,7,7);
		edges[36] = new Edge(9,8,3);
		edges[37] = new Edge(11,9,5);
		edges[38] = new Edge(11,9,9);
		edges[39] = new Edge(11,10,8);
		for(int i = 0;i<40;i++)
			graph.insertEdge(edges[i]);
		
		graph.bianli();
		graph.DIJKSTRA(0);
	}
 
}