package util;

import java.io.Serializable;

public class MyDiskBlock implements Serializable {
	/**
	 * serialVersionUID
	 * ������java���л����ơ�����˵��JAVA���л��Ļ�����ͨ���ж����serialVersionUID����֤�İ汾һ�µġ�
	 * �ڽ��з����л�ʱ��JVM��Ѵ������ֽ����е�serialVersionUID�ڱ�����Ӧʵ�����serialVersionUID���бȽϡ�
	 * �����ͬ˵����һ�µģ����Խ��з����л����������ַ����л��汾һ�µ��쳣������InvalidCastException
	 */
	private static final long serialVersionUID = 1L;
	private static int size = 5; // ���̿�̶���СΪ5���ֽ�
	private int id; // ��¼���̿�����ڴ����ϵ�λ��
	private StringBuffer content = new StringBuffer();
	public MyDiskBlock() {

	}

	public MyDiskBlock(StringBuffer s) {
		this.content = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getSize() {
		return size;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public boolean canAdd() {
		if (content.length() > 5)
			return false;
		else
			return true;
	}
}