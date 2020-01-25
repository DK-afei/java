package test0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Block {
	public int InitialAddress;
	public int length;
	public String status;

	public Block() {

	}

	public Block(int InitialAddress, int length, String status) {
		this.InitialAddress = InitialAddress;
		this.length = length;
		this.status = status;
	}

	/**
	 * ��������������ʾ
	 * 
	 * @param blockList                   ���з���
	 * @param finish_distribute_blocklist �Ѿ�����ķ���
	 */
	public static void displayBlock(List<Block> blockList, List<Block> finish_distribute_blocklist) {
		Block block = null;
		int i = 0;
		System.out.println("δ�������ѷ����������Ϊ��");
		System.out.println("*******************************************************************************");
		System.out.println("δ��������£� ��ַ������/kB  ");
		System.out.println("-----------------------------------------------------------------");
		for (Iterator it = blockList.iterator(); it.hasNext();) {
			block = (Block) it.next();
			System.out.printf("|    �������飺%d      ��ʼ��ַ��%d      ���ȣ�%d      ״̬��%s    \n", i, block.InitialAddress,
					block.length, block.status);
			System.out.println("-----------------------------------------------------------------");
			i++;
		}

		System.out.println("�ѷ�������£� ��ַ������/kB  ");
		for (Iterator it = finish_distribute_blocklist.iterator(); it.hasNext();) {
			block = (Block) it.next();
			System.out.printf("|    �������飺%d      ��ʼ��ַ��%d      ���ȣ�%d      ״̬��%s    \n", i, block.InitialAddress,
					block.length, block.status);
			System.out.println("-----------------------------------------------------------------");
			i++;
		}
		System.out.println("********************************************************************************");
	}

}
