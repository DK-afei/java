package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

class Maze {
	static Stack<Point> trace;
	public static int nn = 0;
	/*��������Թ�*/
	public static Point[][] createMaze(int size){
		
		int realSize = 2 * size + 1;
		Point[][] maze = new Point[realSize][realSize];
		//��ʼ���Թ�����
		for(int i = 0; i < realSize; i ++){
			
			for(int j = 0; j < realSize; j ++){
				Point p = new Point();
				if(i % 2 == 0){
					
					p.value = 1;
					
				}else{
					
					if(j % 2 == 0){
						
						p.value = 1;
						
					} else{
						
						p.value = 0;
						
					}
				}
				p.x = i;
				p.y = j;
				maze[i][j] = p;
			}
		}
		// ����켣
		List<Point> trace = new ArrayList<Point>();
		Point p = maze[1][1];
		List<Point> tmpList = new ArrayList<Point>();
		while(trace.size() < size * size){
			
			tmpList.clear();
			// ���û�з��ʹ�������Ϊ���ʹ�
			if(!p.visited){
				
				p.visited = true;
				// ���뵽�켣��
				trace.add(p);
			}
			//���β鿴���������ĸ�����Ľڵ��Ƿ񱻷��ʹ������û�з��ʹ��������б���
			// ��
			if(p.x - 2 > 0) {
				
				Point upPoint = maze[p.x - 2][p.y];
				if(!upPoint.visited)
					tmpList.add(upPoint);
				
			}
			// ��
			if(p.x + 2 < realSize){
				
				Point downPoint = maze[p.x + 2][p.y];
				if(!downPoint.visited)
					tmpList.add(downPoint);
			}
			// ��
			if(p.y - 2 > 0){
				
				Point leftPoint = maze[p.x][p.y - 2];
				if(!leftPoint.visited)
					tmpList.add(leftPoint);
			}
			// ��
			if(p.y + 2 < realSize){
				
				Point rightPoint = maze[p.x][p.y + 2];
				if(!rightPoint.visited)
					tmpList.add(rightPoint);
			}
			// ����������һ��е�û�з���
			if(tmpList.size() != 0){
			
				// ���ȡһ���ڵ㣬�����Ϳ����������·���ﵽ��������Թ���Ŀ��
				Point nextPoint = tmpList.get(new Random().nextInt(tmpList.size()));
				// ��ͨ���ڵ�ġ�ǽ��
				maze[(p.x + nextPoint.x) / 2][(p.y + nextPoint.y) / 2].value = 0;
				// ��������ڵ�Ϊ��һ�����ʵ�
				p = nextPoint;
			} else{
				// ���û���ڵ㣬��֮ǰ�켣�еĵ������ȡһ����Ҳ��Ϊ����������Թ�
				p = trace.get(new Random().nextInt(trace.size()));
			}
			
		}
		
		return maze;
	}
	
	public static Point[][] search(Point[][] maze, Point enterPoint, Point exitPoint){
		
		int realSize = maze.length;
		// ����켣
		trace = new Stack<Point>();
		// ������
		Point p = enterPoint;
		boolean hasFind = false;
		do{
			// ���û�з��ʹ�������Ϊ���ʹ�
			if(!p.visited){
				
				p.visited = true;
				// ����valueΪ2����ʾ���е�·��
				p.value = 2;
				// ��������յ㣬�ж�
				if(p == exitPoint){
					hasFind = true;
					break;
				}
				// ���뵽�켣��
				trace.push(p);
				
			}
			Point nextPoint = null;
			//���β鿴���������ĸ�����Ľڵ��Ƿ������
			// ��
			if(p.x - 1 > 0 && maze[p.x - 1][p.y].value == 0 && !maze[p.x-1][p.y].visited) {
				
				nextPoint = maze[p.x - 1][p.y];
			}
			// ��
			else if(p.x + 1 < realSize && maze[p.x + 1][p.y].value == 0 && !maze[p.x+1][p.y].visited){
				
				nextPoint = maze[p.x + 1][p.y];
			}
			// ��
			else if(p.y - 1 > 0 && maze[p.x][p.y - 1].value == 0 && !maze[p.x][p.y - 1].visited){
				
				nextPoint = maze[p.x][p.y - 1];
			}
			// ��
			else if(p.y + 1 < realSize && maze[p.x][p.y + 1].value == 0 && !maze[p.x][p.y + 1].visited){
				
				nextPoint = maze[p.x][p.y + 1];
			} 
			// ������������нڵ������
			if(nextPoint != null){
				// ��������ڵ�Ϊ��һ�����ʵ�
				p = nextPoint;
			} else{
				// ���û���ڵ㣬���˵���һ���ڵ�
				// �ı�value
				p.value = 0;
				p = trace.pop();
			}
			
		} while(!trace.isEmpty());
		if(hasFind)
		{
			System.out.println("�ҵ�·��");
		}
	
		else
			System.out.println("û�ҵ�·��");
		return maze;
	}
	static class Point{
		private int x;
		private int y;
		private int value;
		private boolean visited = false;
		
		public Point() {
		}
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public int getValue() {
			return value;
		}
		public boolean isVisited() {
			return visited;
		}
		
	}
}


