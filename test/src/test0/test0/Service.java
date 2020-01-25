package test0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Service {

	public int menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("--------- 1 : �״���Ӧ   2 : ѭ���״���Ӧ   3 : �����ڴ�   0 ���˳� ---------");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("������������еĲ�����");
		int number = input.nextInt();
		return number;
	}

	/**
	 * �ͷ��ڴ�
	 * 
	 * @param blockList
	 */
	public void deleteBlock(List<Block> blockList, List<Block> finish_distribute_blocklist) {
		/**
		 * ���ݷ�����
		 */
		List<Block> copy_blockList = new ArrayList<Block>();
		copy_blockList.addAll(blockList);
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ���յ���ҵ��:");
		String homework_name = input.nextLine();
		int i = 0;
		Block f = null;
		boolean flag = false;
		for (Iterator fini_block = finish_distribute_blocklist.iterator(); fini_block.hasNext();) {
			f = (Block) fini_block.next();
			if (f.status.equals(homework_name)) {
				flag = true;
				finish_distribute_blocklist.remove(f);
				break;
			}
		}
		if (f != null && flag) {
			for (i = 0; i < blockList.size(); i++) {
				Block b1 = copy_blockList.get(i);
				Block b2 = null;
				if (i + 1 <= blockList.size())
					b2 = copy_blockList.get(i + 1);
				if (f.InitialAddress + f.length == b1.InitialAddress) {
					b1.InitialAddress = f.InitialAddress;
					b1.length = f.length + b1.length;
					break;
				}
				// ��������кϲ�
				else if ((b1.InitialAddress + b1.length == f.InitialAddress)
						&& (f.InitialAddress + f.length != b2.InitialAddress)) {
					b1.length += f.length;
					break;
				}
				// ��������кϲ�
				else if ((b1.InitialAddress + b1.length < f.InitialAddress)
						&& (f.InitialAddress + f.length == b2.InitialAddress)) {
					b2.InitialAddress = f.InitialAddress;
					b2.length += f.length;
					break;
				}
				// ���¶��ϲ�
				else if ((b1.InitialAddress + b1.length == f.InitialAddress)
						&& (f.InitialAddress + f.length == b2.InitialAddress)) {
					b1.length += (f.length + b2.length);
					copy_blockList.remove(b2);
					break;
				}

			}
			// ���¶�û�����ڿ��У���Ӵ˿���
			if (i >= blockList.size())
				copy_blockList.add(new Block(f.InitialAddress, f.length, "free"));
		} else {
			System.out.println("����ʧ�ܣ�������������ҵ��!");
		}

		Block.displayBlock(copy_blockList, finish_distribute_blocklist);
	}

	/**
	 * �״���Ӧ�㷨
	 * 
	 * @param blockList                   ���з���
	 * @param finish_distribute_blocklist �ѷ����
	 * @param homeWorkList                ��ҵ��
	 * @return
	 */
	public void insert(List<Block> blockList, List<Block> finish_distribute_blocklist, List<HomeWork> homeWorkList) {
		HomeWork h = null;
		Block b = null;
		for (Iterator homework = homeWorkList.iterator(); homework.hasNext();) {
			h = (HomeWork) homework.next();
			boolean flag = true;
			for (int i = 0; i < blockList.size(); i++) {
				b = blockList.get(i);
				if (h.length <= b.length) {
					Block block1 = new Block(b.InitialAddress, h.length, h.homework_name);
					finish_distribute_blocklist.add(block1);
					System.out.printf("������ҵ %s �ɹ���\n", h.homework_name);
					System.out.printf("������ʼλ��Ϊ��%d   ���䳤��Ϊ��%d\n", b.InitialAddress, h.length);
					b.InitialAddress = b.InitialAddress + h.length;
					b.length = b.length - h.length;
					Block.displayBlock(blockList, finish_distribute_blocklist);
					flag = false;
					break;
				}

			}

			if (flag) {
				System.out.println("-----------------------------------------------------------------");
				System.out.printf("������ҵ %s ʧ�ܣ�\n", h.homework_name);
			}
		}

	}

	/**
	 * ѭ���״���Ӧ
	 * 
	 * @param blockList    δ�������
	 * @param homeWorkList �ѷ������
	 * @return
	 */
	public void insert_xunhuan(List<Block> blockList, List<Block> finish_distribute_blocklist,
			List<HomeWork> homeWorkList) {
		HomeWork h = null;
		Block b = null;
		int temp = 0;
		for (Iterator homework = homeWorkList.iterator(); homework.hasNext();) {
			h = (HomeWork) homework.next();
			int j = 0, k = 0;
			while (j <= blockList.size()) {
				if (j == 0)
					k = temp;
				if (temp >= blockList.size())
					temp = 0;
				b = blockList.get(temp);
				if (h.length <= b.length) {
					Block block1 = new Block(b.InitialAddress, h.length, h.homework_name);
					finish_distribute_blocklist.add(block1);
					System.out.println("-----------------------------------------------------------------");
					System.out.printf("������ҵ %s �ɹ���\n", h.homework_name);
					System.out.printf("������ʼλ��Ϊ��%d   ���䳤��Ϊ��%d\n", b.InitialAddress, h.length);
					b.InitialAddress = b.InitialAddress + h.length;
					b.length = b.length - h.length;
					Block.displayBlock(blockList, finish_distribute_blocklist);
					temp++;
					break;
				}
				j++;
				temp++;
			}
			if (j > blockList.size()) {
				System.out.println("-----------------------------------------------------------------");
				System.out.printf("������ҵ %s ʧ�ܣ�\n", h.homework_name);
				temp = k;
			}
		}
	}

}
