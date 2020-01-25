package t1;

public class T2 {

	public static void maopao(int[]a, int n){
		int t=0;
		int flag=0;
		for(int i=n-1;i>0;i--)
		{
			flag=0;
			for(int j=0;j<i;j++)
			{
				if(a[j]>a[j+1])
				{
					 t=a[j];
					a[j]=a[j+1];
					a[j+1]=t;
					flag=1;
				}
			}
			if(flag==0)
			{
				break;
			}
		}
//		for(int i=0;i<n;i++)
//		{
//			System.out.print(a[i]+" ");
//		}
//		System.out.println();
	}
	public static void xuanze(int[]a, int n){
		int i,j,min,t;
		for(i=0;i<n;i++)
		{
			min=i;
			for(j=i+1;j<n;j++)
			{
				if(a[j]<a[min])
				{
					min=j;
				}
			}
			if(a[min]!=a[i])
			{
				t=a[min];
				a[min]=a[i];
				a[i]=t;
			}
		}
//		for(i=0;i<n;i++)
//		{
//			System.out.print(a[i]+" ");
//		}
//		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {2,3,1,8,4,5,9,7,6,0};
		System.out.println("Ã°ÅÝÅÅÐò");
		maopao(a,10);
		System.out.println();
		System.out.println("Ñ¡ÔñÅÅÐò");
		xuanze(a,10);
	}

}
