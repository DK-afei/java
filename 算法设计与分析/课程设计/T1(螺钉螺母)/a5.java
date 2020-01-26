package a;

import java.util.Arrays;

public class a5 {
	static int n = 1, t;
	static int[] a = { 2, 3, 7, 6, 4, 1, 8, 9, 5 };// 螺帽尺寸数组
	static int[] b = { 6, 5, 9, 8, 1, 7, 4, 2, 3 };// 螺钉尺寸数组

	public static void main(String[] args) {
		Sort(a, b, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
	}

	// 利用快速排序的原理来进行螺钉、螺帽的相互匹配
	public static void Sort(int[] a, int[] b, int left, int right) {
		if (left < right) {

			int t = a[left]; // 用第left个螺帽来区分螺钉
			int i = left, j = right;
			while (i < j) {
				while (i < j && b[i] < t)
					i++;
				while (i < j && b[j] > t)
					j--;
				if (i < j)
					change_b(i, j, b);//
			}
			change_b(i, left, b);
			System.out.println("对螺钉（尺寸数组b）的第" + n + "次匹配结果 :  " + Arrays.toString(b));

			t = b[left + 1]; // 然后同时反过来用第left+1个螺钉来区分螺帽
			i = left + 1;
			j = right;
			while (i < j) {
				while (i < j && a[i] < t)
					i++;
				while (i < j && a[j] > t)
					j--;
				if (i < j)
					change_a(i, j, a);
			}
			change_a(left + 1, i, a);
			System.out.println("对螺帽（尺寸数组a）的第" + n + "次匹配结果 :  " + Arrays.toString(a));

			n++;
			Sort(a, b, left + 2, right);// 递归调用快速排序，直到所有螺钉、螺帽全部匹配成功
		}
	}

	// 互换螺帽数组中的两个指定顺序的螺帽
	public static void change_a(int i, int j, int[] a) {
		t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// 互换螺钉数组中的两个指定顺序的螺钉
	public static void change_b(int i, int j, int[] b) {
		t = b[i];
		b[i] = b[j];
		b[j] = t;
	}
}