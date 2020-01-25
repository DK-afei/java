package util;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;

public class MyDisk implements Serializable{
	/**
	 * serialVersionUID
	 * ������java���л����ơ�����˵��JAVA���л��Ļ�����ͨ���ж����serialVersionUID����֤�İ汾һ�µġ�
	 * �ڽ��з����л�ʱ��JVM��Ѵ������ֽ����е�serialVersionUID�ڱ�����Ӧʵ�����serialVersionUID���бȽϡ�
	 * �����ͬ˵����һ�µģ����Խ��з����л����������ַ����л��汾һ�µ��쳣������InvalidCastException
	 */
	private static final long serialVersionUID = 1L;
	private static MyDisk a = new MyDisk();
	private Hashtable<Integer,MyDiskBlock> usedlist;
	private int Num = 1024;//ָ���ռ�
	private int restNum = Num;//ʣ��ռ�
	/**  ��ǰ���̿�ָ�룬ֻ���������Է����̿��ͻ     */
	private int nowpoint=0;        
	private MyDisk() {
		this.usedlist = new Hashtable<Integer,MyDiskBlock>();
	}

	/**
	 * ���õ���ģʽ�õ���ǰ����ʵ��
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
	 * ��ĳһ�����̿��ϵ����ݱ��浽������
	 * @param a
	 *   ��Ҫ���浽�����ϵ�ĳһ���ſ��
	 */
	public void addUsed(MyDiskBlock a){
			a.setId(nowpoint);
			this.usedlist.put(nowpoint,a);
			this.restNum--;
			this.nowpoint++;
			
	}
	/**
	 * ���ƶ��̿��ϵ����ݴӴſ���ɾ��
	 * @param a
	 * �̿��
	 */
	public void deleteUsed(int a){
		this.usedlist.remove(a);
		this.restNum++;
	}
	
	/**
	 * ��ʽ������
	 * @param a
	 * �̿��
	 */
	public void format_MyDisk(){
		this.usedlist.clear();
		this.setRestNum(this.Num);
	}
	
  /**
   * ��ӡ�Ѿ�ʹ�ù��Ĵ��̿��
   */
	public void show() {
		System.out.println("--------------����Ϊ�Ѿ�ʹ�õĴ��̿��--------------");
		Iterator<Integer> it=usedlist.keySet().iterator();
		while(it.hasNext()) {
			System.out.print(usedlist.get(it.next()).getId() + "     ");
		}
		if (usedlist.size() > 0)
			System.out.println();
		System.out.println("ʣ��ռ䣺"+this.getRestNum());
	}
}