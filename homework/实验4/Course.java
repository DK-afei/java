package zuoye;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Course {
	private String courseName;
	private String[] students = new String[100];
	private int numberOfStudents;
	
	public Course(String courseName)
	{
		this.courseName = courseName;
	}
	
	public void addStudent(String student)
	{
		students[numberOfStudents] = student;
		numberOfStudents++;
	}
	
	public String[] getStudents()
	{
		return students;
	}
	
	public int getNumberOfStudents()
	{
		return numberOfStudents;
	}
	
	public String getCourseName()
	{
		return courseName;
	}
	
	public void dropStudent(String student)
	{
		List<String> student2 = new ArrayList<String>();
		Collections.addAll(student2, students);
		for(int i=0,len=student2.size();i<len;i++)
		{
			if(student2.get(i)==student)
			{
				student2.remove(i);
				--len;
			}
		}
		students = (String[])student2.toArray(new String[student2.size()]);
	}
	public void clear()
	{
		students = null;
	}
	public static void main(String[] args) {
		// P342 10.9
		Course c = new Course("English");
		c.addStudent("张三");
		c.addStudent("李四");
		c.addStudent("王二");
		c.dropStudent("王二");
		System.out.println("课程"+c.getCourseName()+"剩余学生：");
		for(int i=0;i<c.getNumberOfStudents()-1;i++)
		{
			System.out.println(c.getStudents()[i]);
		}
	}

}