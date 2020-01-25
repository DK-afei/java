package t1;

public class SortT {
	static int num = 50000;
	static int[] suiji = new int[num];
	static int[] a = new int[num];
	static int[] b = new int[num];
	static int[] c = new int[num];
	static int[] d = new int[num];
	static int[] e = new int[num];
	static int[] t = new int[num];
	public static void ready()
	{
		for(int i=0;i<num;i++)
		{
			int j=(int)(Math.random()*100000);
			suiji[i]=j;	
		}
		for(int i=0;i<num;i++)
		{
			a[i]=suiji[i];
			b[i]=suiji[i];
			c[i]=suiji[i];
			d[i]=suiji[i];
			
		}
		for(int i=0;i<num;i++)
		{
			e[i]=i;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long s,e;
		SortT st0 = new SortT();
		st0.ready();
		s=System.currentTimeMillis();
		T2.maopao(st0.a, st0.num);
		e=System.currentTimeMillis();
		System.out.println("maopao: "+(e-s)+"ms");
		s=System.currentTimeMillis();
		T2.xuanze(st0.b, st0.num);
		e=System.currentTimeMillis();
		System.out.println("xuanze: "+(e-s)+"ms");
		s=System.currentTimeMillis();
		T3.kuaisu(st0.c, 0, st0.num-1);
		e=System.currentTimeMillis();
		System.out.println("kuaisu: "+(e-s)+"ms");
		s=System.currentTimeMillis();
		T3.guibing(st0.d, st0.t);
		e=System.currentTimeMillis();
		System.out.println("guibing: "+(e-s)+"ms");
	}
	

}
