package util;

import java.io.Serializable;

public class MyDiskBlock implements Serializable {
	/**
	 * serialVersionUID
	 * 适用于java序列化机制。简单来说，JAVA序列化的机制是通过判断类的serialVersionUID来验证的版本一致的。
	 * 在进行反序列化时，JVM会把传来的字节流中的serialVersionUID于本地相应实体类的serialVersionUID进行比较。
	 * 如果相同说明是一致的，可以进行反序列化，否则会出现反序列化版本一致的异常，即是InvalidCastException
	 */
	private static final long serialVersionUID = 1L;
	private static int size = 5; // 磁盘块固定大小为5个字节
	private int id; // 记录磁盘块放置在磁盘上的位置
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