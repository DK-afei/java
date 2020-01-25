package util;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;

public class MyDisk implements Serializable{
	/**
	 * serialVersionUID
	 * 适用于java序列化机制。简单来说，JAVA序列化的机制是通过判断类的serialVersionUID来验证的版本一致的。
	 * 在进行反序列化时，JVM会把传来的字节流中的serialVersionUID于本地相应实体类的serialVersionUID进行比较。
	 * 如果相同说明是一致的，可以进行反序列化，否则会出现反序列化版本一致的异常，即是InvalidCastException
	 */
	private static final long serialVersionUID = 1L;
	private static MyDisk a = new MyDisk();
	private Hashtable<Integer,MyDiskBlock> usedlist;
	private int Num = 1024;//指定空间
	private int restNum = Num;//剩余空间
	/**  当前磁盘块指针，只增不减，以防磁盘块冲突     */
	private int nowpoint=0;        
	private MyDisk() {
		this.usedlist = new Hashtable<Integer,MyDiskBlock>();
	}

	/**
	 * 运用单例模式得到当前磁盘实例
	 * 
	 * @return
	 */
	public static synchronized MyDisk getInstance() {
		return a;
	}

	public Hashtable<Integer, MyDiskBlock> getUsedlist() {
		return usedlist;
	}
	
	public void setUsedlist(Hashtable<Integer, MyDiskBlock> usedlist) {
		this.usedlist = usedlist;
	}

	public int getRestNum() {
		return restNum;
	}

	/**
	 * 
	 * @param restNum
	 */
	public void setRestNum(int restNum) {
		this.restNum = restNum;
	}

	
	/**
	 * 将某一个磁盘块上的内容保存到磁盘上
	 * @param a
	 *   需要保存到磁盘上的某一个磁块号
	 */
	public void addUsed(MyDiskBlock a){
			a.setId(nowpoint);
			this.usedlist.put(nowpoint,a);
			this.restNum--;
			this.nowpoint++;
			
	}
	/**
	 * 将制定盘块上的内容从磁块上删除
	 * @param a
	 * 盘块号
	 */
	public void deleteUsed(int a){
		this.usedlist.remove(a);
		this.restNum++;
	}
	
	/**
	 * 格式化磁盘
	 * @param a
	 * 盘块号
	 */
	public void format_MyDisk(){
		this.usedlist.clear();
		this.setRestNum(this.Num);
	}
	
  /**
   * 打印已经使用过的磁盘块号
   */
	public void show() {
		System.out.println("--------------以下为已经使用的磁盘块号--------------");
		Iterator<Integer> it=usedlist.keySet().iterator();
		while(it.hasNext()) {
			System.out.print(usedlist.get(it.next()).getId() + "     ");
		}
		if (usedlist.size() > 0)
			System.out.println();
		System.out.println("剩余空间："+this.getRestNum());
	}
}