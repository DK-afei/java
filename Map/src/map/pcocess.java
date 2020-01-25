package map;

import java.awt.Checkbox;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Robot;

public class pcocess {
	private MapColor mc;//��ͼ
	private Polygon[] p;//����γ�������
	private Checkbox cbx;//������ʾ
	private int current = 0;//��ǰ�ĳ���
	int[][] metrix;//��ͼ��������ͼ���ڽӾ���
	public pcocess(MapColor mc,Polygon[] p,Checkbox cbx,int[][] metrix){
		this.mc = mc;
		this.p = p;
		this.cbx = cbx;
		this.metrix =metrix; 
	}
	//ȷ�ϵ�ǰ�����ܷ�Ⱦɫ
	public boolean isOK(int province[])
	{
		for(int j=0;j<current;j++)
			if(metrix[current][j]==1&&province[j]==province[current])
				return false;
		return true;
	}
	//��ɫ����
	public int color(int province[],int n)//n��ʾ��ɫ���ڼ�������Ϊֹ
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
				    r.delay(150);//���ƶ�����ʾ��ʱ����ʱ
				}
				catch(Exception e){}
				if(isOK(province))
				{
					current++;
					int j = color(province,n);//�ݹ鵽��һ�����н�����ɫ
					if(  j==-1)//����ǰ���в��ɱ���ĳɫ�ͽ��л���
					{
						current--;
						province[current]=-1;
					}
					if(current>n) return 1;//���г��еݹ���ɫ���
				}
			}
		if(i>=4) return -1;
		return 1;
	}
	//�ݹ�����㷨
	public void backtrack(){
		int province[]=new int[34];
		int flag=current;//��¼���
			  color(province,33);
			  if((current-flag)!=34)//����㲻�Ǵ�0��ʼ��������ɫ������current�ٿ�ʼ��ɫ
			  {
				  current=0;
				  color(province,flag);
			  }
	}
	//���õ�ͼ����ʼδ��ɫ״̬
	public void resetMap(){
		Graphics g = mc.getGraphics();
		for(int k=0;k<34;k++){
			mc.fillColor(g, k, 4);
		}
		mc.paint(g);
		
	}

}
