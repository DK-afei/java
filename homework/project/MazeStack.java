package project;

import java.util.ArrayList;
import java.util.EmptyStackException;


import project.Maze.Point;

/** 
 * 功能：本类用来描述一个栈。
 */
class MazeStack<T>{

	private ArrayList<T> array;	 // 使用一个List对象来保存栈中的元素。
	private int now_count;		 // 记录当前栈中所具有的元素的个数。

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