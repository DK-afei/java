package t1;

public class T3 {

public static void merge(int[] inputList, int left, int mid, int right, int[] tem) {
	int i = left;	//i是第一段序列的下标
	int j = mid + 1;//j是第二段序列的下标
	int k = 0;		//临时存放合并数组的下标
	while (i <= mid && j <= right) {// 判断第一段和第二段取出的数哪个更小，将其存入合并序列，并继续向下扫描
		if (inputList[i] <= inputList[j]) {// 这里的等号容易忘
			tem[k++] = inputList[i++];
		} else {
			tem[k++] = inputList[j++];
		}
	}
	while (i <= mid) {// 若第一段序列还没扫描完，将其全部复制到合并序列
		tem[k++] = inputList[i++];
	}
	while (j <= right) {// 若第二段序列还没扫描完，将其全部复制到合并序列
		tem[k++] = inputList[j++];
	}
	k = 0;
	while (left <= right) {//将临时数组保存好的有序的数组赋值给原有的数组 
		inputList[left++] = tem[k++];
	}
}

public static void mergeSort(int[] inputList, int left, int right, int[] tem) {
	if (left < right) {
		int mid = (right + left) / 2;
		mergeSort(inputList, left, mid, tem);		//将左边进一步分
		mergeSort(inputList, mid + 1, right, tem);	//将右边进一步分
		merge(inputList, left, mid, right, tem);	//将左右两边进行排序
	}
}

	public static void f(int []a, int l, int r)
	{
		int i,j,x;
		if(l<r)
		{
			i=l;
			j=r;
			x=a[i];
			while(i<j)
			{
				while(i<j&&a[j]>x)
					j--;
				if(i<j)
					a[i++]=a[j];
				while(i<j&&a[i]<x)
					i++;
				if(i<j)
					a[j--]=a[i];
			}
			a[i]=x;
			f(a,l,i-1);
			f(a,i+1,r);
		}
		
	}
	public static void kuaisu(int[] a, int l, int r)
	{
		f(a,l,r);
//		for(int i=0;i<a.length;i++)
//		{
//			System.out.print(a[i]+" ");
//		}
//		System.out.println();
	}
	public static void guibing(int[] a, int[] b)
	{
		mergeSort(a, 0, a.length - 1, b);
//		for(int i=0;i<a.length;i++)
//		{
//			System.out.print(a[i]+" ");
//		}
//		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i;
		int[] a = { 8, 9, 1, 5, 7, 6, 3, 0, 2, 4 };
		int[] b = new int[a.length];
		int []aa={5,1,2,6,7,3,9,0,4,8};
		System.out.println("快速排序：");
		kuaisu(aa,0,9);
		System.out.println("归并排序：");
		guibing(a,b);
		
	}

}
