package a;

import java.util.Arrays;

public class a5 {
	static int n = 1, t;
	static int[] a = { 2, 3, 7, 6, 4, 1, 8, 9, 5 };// ��ñ�ߴ�����
	static int[] b = { 6, 5, 9, 8, 1, 7, 4, 2, 3 };// �ݶ��ߴ�����

	public static void main(String[] args) {
		Sort(a, b, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
	}

	// ���ÿ��������ԭ���������ݶ�����ñ���໥ƥ��
	public static void Sort(int[] a, int[] b, int left, int right) {
		if (left < right) {

			int t = a[left]; // �õ�left����ñ�������ݶ�
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
			System.out.println("���ݶ����ߴ�����b���ĵ�" + n + "��ƥ���� :  " + Arrays.toString(b));

			t = b[left + 1]; // Ȼ��ͬʱ�������õ�left+1���ݶ���������ñ
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
			System.out.println("����ñ���ߴ�����a���ĵ�" + n + "��ƥ���� :  " + Arrays.toString(a));

			n++;
			Sort(a, b, left + 2, right);// �ݹ���ÿ�������ֱ�������ݶ�����ñȫ��ƥ��ɹ�
		}
	}

	// ������ñ�����е�����ָ��˳�����ñ
	public static void change_a(int i, int j, int[] a) {
		t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// �����ݶ������е�����ָ��˳����ݶ�
	public static void change_b(int i, int j, int[] b) {
		t = b[i];
		b[i] = b[j];
		b[j] = t;
	}
}