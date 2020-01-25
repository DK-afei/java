package t2;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * <pre>
 * ʹ��kruskal�㷨����ͼ��MST
 * ���У�
 * 1>.ͼ�Ķ�������������У�������int
 * 2>.ͼ�Ĵ�Ȩ��ʹ����EdgeNode���󣬴�����������
 * 3>.�߰���Ȩ�ؽ�������������С�ѽ�������ÿ��ȡ����С�ѵĸ��ڵ㣬����Ȩ��С�ı�
 * 4>.ÿ����MST�����ӱ߿϶�������СȨ�ıߣ�Ψһ�������ǲ����ɻ�
 * 5>.���������ɻ������õȼ���<���鼯>ʵ�ֵ�
 * 6>.���鼯ʵ�֣�����ÿ������������parent��&root��!find-union!��������</pre>
 * @author King
 *
 */
/*
1 2 3
1 5 6
1 6 5
2 3 1
2 6 4
3 4 6
3 6 4
4 5 8
4 6 5
5 6 2
 */

/*
1 2 5
1 4 6
2 3 1
2 4 3
3 4 4
3 5 6
4 5 2
 */

/*
1 2 3
1 3 5
1 4 4
2 5 3
2 6 6
3 4 2
3 7 4
4 8 5
4 5 1
5 6 2
5 9 4
6 10 5
7 11 6
7 8 3
8 9 6
8 11 7
9 10 3
9 12 5
10 12 9
11 12 8
 */
class EdgeNode {
    int weight;
    int u,v;
}
public class KruskalMST {
	static char[] ccc= {'a','b','c','d','e','f','g','h','i','j','k','l'};
    boolean[] root;
    int[] parent;
    int currentSize=0;
    int maxSize=0;
    EdgeNode[] minHeap=new EdgeNode[20];

    /**
     * ��ʼ��ÿ������Ϊһ����
     * @param verNum ���������
     */
    public void initialize(int verNum){
        root=new boolean[verNum+1];
        parent=new int[verNum+1];

        for(int vertex=1;vertex<=verNum;vertex++){
            parent[vertex]=1;
            root[vertex]=true;
        }
    }

    /**
     * Ѱ��ĳ������Ԫ�����ڵ���
     * @param vertex ����
     * @return ���ص��Ƕ������ڵ���
     */
    public int find(int vertex){
        while(!root[vertex]){
            vertex=parent[vertex];
        }
        return vertex;
    }

    /**
     * �������������������ڵ�Ϊi,j����ϲ�
     * @param i ���ڵ�Ϊi
     * @param j ���ڵ�Ϊj
     */
    public void union(int i,int j){
        if(parent[i]<parent[j]){
            parent[j]+=parent[i];
            root[i]=false;
            parent[i]=j;
        }else{
            parent[i]+=parent[j];
            root[j]=false;
            parent[j]=i;
        }
    }

    /**
     * ͨ��weight������EdgeNodeΪ�ڵ����С��
     * @param edgeNodeΪ��Ȩ�ı߼�
     */
    public void createMinHeap(EdgeNode[] edgeNode){
        currentSize=edgeNode.length;
        maxSize=minHeap.length;
        if(currentSize>=maxSize){
            maxSize*=2;
            minHeap=new EdgeNode[maxSize];
        }
        for(int i=0;i<currentSize;i++)
            minHeap[i+1]=edgeNode[i];

        int y,c;
        for(int i=currentSize/2;i>=1;i--){
            EdgeNode node=minHeap[i];
            y=node.weight;
            c=2*i;
            while(c<currentSize){
                if(c<=currentSize && minHeap[c].weight>minHeap[c+1].weight)
                    c++;
                if(minHeap[c].weight>=y)
                    break;
                minHeap[c/2]=minHeap[c];
                c=c*2;
            }
            minHeap[c/2]=node;
        }
    }

    /**
     * ��С��ɾ������˼·��һ�ֺ�ǰ��һ��������һֱ���ٷ��ڸ��ڵ���Ǹ����һ���ڵ����ղ����λ��
     * ��һ��˼·����ÿһ����������Ľ���Ȼ����һһ���ڽ���ͬ������
     */
    public EdgeNode deleteMinHeap(){
        if(currentSize<1)
            System.out.println("���Ѿ�Ϊ�գ��޷�ִ��ɾ��");
        EdgeNode node=minHeap[1];
        minHeap[1]=minHeap[currentSize];
        currentSize-=1;

        int c=2,j=1;
        EdgeNode node1=minHeap[currentSize+1];
        while(c<=currentSize){
            if(c<currentSize && minHeap[c].weight>minHeap[c+1].weight)
                c++;
            if(node1.weight<=minHeap[c].weight)
                break;
            minHeap[j]=minHeap[c];
            j=c;
            c=c*2;
        }
        minHeap[j]=node1;
        return node;
    }

    /**
     * ����ͼ�Ķ��㼯�ϴ�Ȩ�߼�����MST
     * @param verArray ���㼯
     * @param edgeNode ��Ȩ�߼�
     */
    public void minSpanningTree(int[] verArray,EdgeNode[] edgeNode){
        ArrayList<EdgeNode> nodeList=new ArrayList<EdgeNode>();

        initialize(verArray.length);
        createMinHeap(edgeNode);

		/*
		 * for(int i=1;i<=currentSize;i++){
		 * System.out.println(minHeap[i].u+" "+minHeap[i].v+" "+minHeap[i].weight); }
		 */

        for(int i=0;i<edgeNode.length;i++){
            EdgeNode node=deleteMinHeap();
            int jRoot=find(node.u);
            int kRoot=find(node.v);
            if(jRoot!=kRoot){
                nodeList.add(node);
                union(jRoot,kRoot);
            }
        }
        System.out.println("ʹ��Kruskal�㷨�õ�ͼ����С������Ϊ��");
        for(int i=0;i<nodeList.size();i++){
            System.out.println(ccc[nodeList.get(i).u-1]+" "+ccc[nodeList.get(i).v-1]+" "+nodeList.get(i).weight);
        }
    }

    public static void main(String[] args) {
        System.out.println("�����ͼ�Ķ������ͱ�����");
        @SuppressWarnings("resource")
        Scanner scan=new Scanner(System.in);
        int verNum=scan.nextInt();
        int edgeNum=scan.nextInt();

        int[] verArray=new int[verNum];
        System.out.println("���������붥�㣺");
        System.out.println("1-a,2-b,3-c,4-d,5-e,6-f,����");
        for(int i=0;i<verNum;i++){
            int vertex=scan.nextInt();
            verArray[i]=vertex;
        }

        EdgeNode[] edgeNode=new EdgeNode[edgeNum];
        System.out.println("����������ߵĶ����Ȩ�أ�");
        for(int i=0;i<edgeNum;i++){
            int u=scan.nextInt();
            int v=scan.nextInt();
            int weight=scan.nextInt();
            EdgeNode node=new EdgeNode();
            node.u=u;
            node.v=v;
            node.weight=weight;
            edgeNode[i]=node;
        }
        KruskalMST t2=new KruskalMST();
        t2.minSpanningTree(verArray,edgeNode);
    }
}