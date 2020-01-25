package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

class Maze {
	static Stack<Point> trace;
	public static int nn = 0;
	/*随机生成迷宫*/
	public static Point[][] createMaze(int size){
		
		int realSize = 2 * size + 1;
		Point[][] maze = new Point[realSize][realSize];
		//初始化迷宫雏形
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
		// 保存轨迹
		List<Point> trace = new ArrayList<Point>();
		Point p = maze[1][1];
		List<Point> tmpList = new ArrayList<Point>();
		while(trace.size() < size * size){
			
			tmpList.clear();
			// 如果没有访问过，设置为访问过
			if(!p.visited){
				
				p.visited = true;
				// 加入到轨迹中
				trace.add(p);
			}
			//依次查看上下左右四个方向的节点是否被访问过，如果没有访问过，加入列表中
			// 上
			if(p.x - 2 > 0) {
				
				Point upPoint = maze[p.x - 2][p.y];
				if(!upPoint.visited)
					tmpList.add(upPoint);
				
			}
			// 下
			if(p.x + 2 < realSize){
				
				Point downPoint = maze[p.x + 2][p.y];
				if(!downPoint.visited)
					tmpList.add(downPoint);
			}
			// 左
			if(p.y - 2 > 0){
				
				Point leftPoint = maze[p.x][p.y - 2];
				if(!leftPoint.visited)
					tmpList.add(leftPoint);
			}
			// 右
			if(p.y + 2 < realSize){
				
				Point rightPoint = maze[p.x][p.y + 2];
				if(!rightPoint.visited)
					tmpList.add(rightPoint);
			}
			// 如果上下左右还有点没有访问
			if(tmpList.size() != 0){
			
				// 随机取一个邻点，这样就可以随机生成路径达到随机生成迷宫的目的
				Point nextPoint = tmpList.get(new Random().nextInt(tmpList.size()));
				// 打通与邻点的“墙”
				maze[(p.x + nextPoint.x) / 2][(p.y + nextPoint.y) / 2].value = 0;
				// 设置这个邻点为下一个访问点
				p = nextPoint;
			} else{
				// 如果没有邻点，从之前轨迹中的点中随机取一个，也是为了随机生成迷宫
				p = trace.get(new Random().nextInt(trace.size()));
			}
			
		}
		
		return maze;
	}
	
	public static Point[][] search(Point[][] maze, Point enterPoint, Point exitPoint){
		
		int realSize = maze.length;
		// 保存轨迹
		trace = new Stack<Point>();
		// 出发点
		Point p = enterPoint;
		boolean hasFind = false;
		do{
			// 如果没有访问过，设置为访问过
			if(!p.visited){
				
				p.visited = true;
				// 设置value为2（表示可行的路径
				p.value = 2;
				// 如果到了终点，中断
				if(p == exitPoint){
					hasFind = true;
					break;
				}
				// 加入到轨迹中
				trace.push(p);
				
			}
			Point nextPoint = null;
			//依次查看上下左右四个方向的节点是否可以走
			// 上
			if(p.x - 1 > 0 && maze[p.x - 1][p.y].value == 0 && !maze[p.x-1][p.y].visited) {
				
				nextPoint = maze[p.x - 1][p.y];
			}
			// 下
			else if(p.x + 1 < realSize && maze[p.x + 1][p.y].value == 0 && !maze[p.x+1][p.y].visited){
				
				nextPoint = maze[p.x + 1][p.y];
			}
			// 左
			else if(p.y - 1 > 0 && maze[p.x][p.y - 1].value == 0 && !maze[p.x][p.y - 1].visited){
				
				nextPoint = maze[p.x][p.y - 1];
			}
			// 右
			else if(p.y + 1 < realSize && maze[p.x][p.y + 1].value == 0 && !maze[p.x][p.y + 1].visited){
				
				nextPoint = maze[p.x][p.y + 1];
			} 
			// 如果上下左右有节点可以走
			if(nextPoint != null){
				// 设置这个邻点为下一个访问点
				p = nextPoint;
			} else{
				// 如果没有邻点，回退到上一个节点
				// 改变value
				p.value = 0;
				p = trace.pop();
			}
			
		} while(!trace.isEmpty());
		if(hasFind)
		{
			System.out.println("找到路径");
		}
	
		else
			System.out.println("没找到路径");
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


