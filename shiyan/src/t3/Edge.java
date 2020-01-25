package t3;

/**
 * ±ß
 * @author sdu20
 *
 */
public class Edge {
	
	private int v1;
	private int v2;
	private int weight;
	static char[] ccc= {'a','b','c','d','e','f','g','h','i','j','k','l'};
	public Edge(int v1,int v2,int weight){
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public boolean equals(Edge edge){
		return this.v1==edge.getV1() && this.v2==edge.getV2() &&this.weight == edge.getWeight();
	}
	
	public int getV1(){
		return v1;
	}
	
	public int getV2(){
		return v2;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public String toString(){
		String str = "[ "+ccc[v1]+" , "+ccc[v2]+" , "+weight+" ]";
		return str;
	}
 
}