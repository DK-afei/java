package project;

import java.util.ArrayList;
import java.util.EmptyStackException;


import project.Maze.Point;

/** 
 * ���ܣ�������������һ��ջ��
 */
class MazeStack<T>{

	private ArrayList<T> array;	 // ʹ��һ��List����������ջ�е�Ԫ�ء�
	private int now_count;		 // ��¼��ǰջ�������е�Ԫ�صĸ�����

	public MazeStack() {
		now_count = 0;
		array = new ArrayList<T>();
	}

	public Point pop() {
		if (now_count == 0) {
			throw new EmptyStackException();
		} else {
			now_count--;
			return (Point) array.remove(now_count);
		}
	}

	public void push(T point) {
		array.add(point);
		now_count++;
	}

	public Point peek() {
		if (now_count == 0)
			throw new EmptyStackException();
		else
			return (Point) array.get(now_count - 1);
	}

	public boolean isEmpty() {
		return now_count == 0;
	}

	public int getCount() {
		return now_count;
	}

}