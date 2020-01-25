package project3;

public class Test3 {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Course c1 = new Course("Math");
		Course c2 = (Course)c1.clone();
		System.out.println(c1==c2);
		System.out.println(c1.getCourseName()==c2.getCourseName());
		System.out.println(c1.getNumberOfStudents()==c2.getNumberOfStudents());
		System.out.println(c1.getStudents()==c2.getStudents());
	}

}
