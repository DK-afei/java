package t1;

public class T1 {

	public static  int shunxu(int[] a, int n,int x)
	{
		for(int i=0;i<n;i++)
		{
			if(x==a[i])
			{
				return i;
			}
		}
		return -1;
	}	
	public static  int zheban(int[] a, int n,int x)
	{
		int low=0,high=n-1;
		int mid;
		while(low<=high)
		{
			mid=(low+high)/2;
			if(x<a[mid])
				high=mid-1;
			else if(x>a[mid])
				low=mid+1;
			else
				return mid;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long ss,ee;
		int a,b;
		SortT st = new SortT();
		st.ready();
		ss=System.nanoTime();
		a=zheban(st.e,st.e.length,520);
		ee=System.nanoTime();
		System.out.println("located: "+a+",zheban: "+(ee-ss));
		ss=System.nanoTime();
		b=shunxu(st.e,st.e.length,520);
		ee=System.nanoTime();
		System.out.println("located: "+b+",shunxu: "+(ee-ss));
		/*
		 * 1000 3879 6700
		 * 5000 4232  8815
		 * 10000 5642 10226
		 * 50000 7757 13400
		 * 100000 9873 14457
		 */
	}

}
