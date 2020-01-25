package t1;

public class T3 {

public static void merge(int[] inputList, int left, int mid, int right, int[] tem) {
	int i = left;	//i�ǵ�һ�����е��±�
	int j = mid + 1;//j�ǵڶ������е��±�
	int k = 0;		//��ʱ��źϲ�������±�
	while (i <= mid && j <= right) {// �жϵ�һ�κ͵ڶ���ȡ�������ĸ���С���������ϲ����У�����������ɨ��
		if (inputList[i] <= inputList[j]) {// ����ĵȺ�������
			tem[k++] = inputList[i++];
		} else {
			tem[k++] = inputList[j++];
		}
	}
	while (i <= mid) {// ����һ�����л�ûɨ���꣬����ȫ�����Ƶ��ϲ�����
		tem[k++] = inputList[i++];
	}
	while (j <= right) {// ���ڶ������л�ûɨ���꣬����ȫ�����Ƶ��ϲ�����
		tem[k++] = inputList[j++];
	}
	k = 0;
	while (left <= right) {//����ʱ���鱣��õ���������鸳ֵ��ԭ�е����� 
		inputList[left++] = tem[k++];
	}
}

public static void mergeSort(int[] inputList, int left, int right, int[] tem) {
	if (left < right) {
		int mid = (right + left) / 2;
		mergeSort(inputList, left, mid, tem);		//����߽�һ����
		mergeSort(inputList, mid + 1, right, tem);	//���ұ߽�һ����
		merge(inputList, left, mid, right, tem);	//���������߽�������
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
		System.out.println("��������");
		kuaisu(aa,0,9);
		System.out.println("�鲢����");
		guibing(a,b);
		
	}

}
