package test0;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeWork {
	public String homework_name;
	public int length;

	public HomeWork(String homework_name, int length) {
		this.homework_name = homework_name;
		this.length = length;
	}

	/**
	 * ������ҵ
	 * 
	 * @return
	 */
	public static List<HomeWork> create_homework() {
		List<HomeWork> homeWorklist = new ArrayList<HomeWork>();
		Scanner input = new Scanner(System.in);
		System.out.println("��������ҵ������");
		int number = input.nextInt();
		input.nextLine();
		for (int i = 0; i < number; i++) {
			System.out.println("��������ҵ����");
			String homeworkname = input.nextLine();
			System.out.println("��������ҵ�ĳ���:");
			int length = input.nextInt();
			input.nextLine();// ȥ�س�
			HomeWork h = new HomeWork(homeworkname, length);
			homeWorklist.add(h);
		}
		return homeWorklist;
	}

	/**
	 * ��ʾ��ҵ
	 */
	public static void display_homework(List<HomeWork> homeWorkList) {
		for (int i = 0; i < homeWorkList.size(); i++) {
			System.out.printf("��ҵ����%s  ��ҵ���ȣ�%d\n", homeWorkList.get(i).homework_name, homeWorkList.get(i).length);
		}
	}
}
