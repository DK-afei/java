package t0;

public class T3 {
	public static int count=0;
	public static int f(int m, int n)
	{
		if(n==0)
			return count;
		else
		{
			count++;
			return f(n,m%n);
		}
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int max=0;
			int mi=0,mj=0;
			for(int i=10;i>=1;i--)
			{
				for(int j=i;j>=1;j--)
				{
				
				  if(f(j,i)>max) { count=0;max=f(j,i); mi=i; mj=j; }
				  	count=0;
					System.out.printf("(%d,%d): %d\t",j,i,f(j,i));	
					count=0;
				}
				System.out.println();
				
			}
			System.out.println("max:"+max+",("+mj+","+mi+")");
	}
}