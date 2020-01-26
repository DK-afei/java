package map;

import java.awt.Checkbox;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Robot;

public class pcocess {
	private MapColor mc;//地图
	private Polygon[] p;//多边形城市数组
	private Checkbox cbx;//动画演示
	private int current = 0;//当前的城市
	int[][] metrix;//地图城市无向图的邻接矩阵
	public pcocess(MapColor mc,Polygon[] p,Checkbox cbx,int[][] metrix){
		this.mc = mc;
		this.p = p;
		this.cbx = cbx;
		this.metrix =metrix; 
	}
	//确认当前城市能否被染色
	public boolean isOK(int province[])
	{
		for(int j=0;j<current;j++)
			if(metrix[current][j]==1&&province[j]==province[current])
				return false;
		return true;
	}
	//着色函数
	public int color(int province[],int n)//n表示着色到第几个城市为止
	{
		int i = 0;
		if(current<=n)
			for(i=0;i<4;i++)
			{
				province[current]=i;
				Graphics g = mc.getGraphics();
				mc.fillColor(g, current, i);
				if(cbx.getState())
				try{
					Robot r = new Robot();
				    r.delay(150);//控制动画演示的时间延时
				}
				catch(Exception e){}
				if(isOK(province))
				{
					current++;
					int j = color(province,n);//递归到下一个城市进行着色
					if(  j==-1)//若当前城市不可被着某色就进行回溯
					{
						current--;
						province[current]=-1;
					}
					if(current>n) return 1;//所有城市递归着色完成
				}
			}
		if(i>=4) return -1;
		return 1;
	}
	//递归回溯算法
	public void backtrack(){
		int province[]=new int[34];
		int flag=current;//记录起点
			  color(province,33);
			  if((current-flag)!=34)//若起点不是从0开始，则着完色，重置current再开始着色
			  {
				  current=0;
				  color(province,flag);
			  }
	}
	//重置地图到初始未着色状态
	public void resetMap(){
		Graphics g = mc.getGraphics();
		for(int k=0;k<34;k++){
			mc.fillColor(g, k, 4);
		}
		mc.paint(g);
		
	}

}
