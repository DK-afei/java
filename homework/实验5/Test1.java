package project;

public class Test1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person("张三","某地","110","1332@jf");
		System.out.println(p.toString());
		Student s = new Student("张三","某地","110","1332@jf","大一");
		System.out.println(s.toString());
		Test2 t2 = new Test2();
		t2.setDate(561555550000L);
		Employee e = new Employee("张三","某地","110","1332@jf","1-1",6000);
		e.setYear(t2.getYear());
		e.setMonth(t2.getMonth());
		e.setDay(t2.getDay());
		System.out.println(e.toString());
		Faculty f = new Faculty("张三","某地","110","1332@jf","1-1",6000,"20:00","高级");
		f.setYear(t2.getYear());
		f.setMonth(t2.getMonth());
		f.setDay(t2.getDay());
		System.out.println(f.toString());
		Staff st = new Staff("张三","某地","110","1332@jf","1-1",6000,"程序员");
		st.setYear(t2.getYear());
		st.setMonth(t2.getMonth());
		st.setDay(t2.getDay());
		System.out.println(st.toString());
	}
}
class Person
{
	private String name;
	private String address;
	private String pnum;
	private String email;
	
	public Person(String name, String address, String pnum, String email) {
		this.name = name;
		this.address = address;
		this.pnum = pnum;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "name:"+name+",address:"+address+",pnum:"+pnum+",email:"+email;
	}
	
}
class Student extends Person
{
	public Student(String name, String address, String pnum, String email,String state) {
		super(name, address, pnum, email);
		this.state = state;
	}

	private String state;

	@Override
	public String toString() {
		return super.toString()+",state:"+state;
	}
}
class Employee extends Person
{
	public Employee(String name, String address, String pnum, String email,String office, double salary) {
		super(name, address, pnum, email);
		this.office = office;
		this.salary = salary;
	}
	private String office;
	private double salary;
	private int year;
	private int month;
	private int day;
	public void setYear(int year) {
		this.year = year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return super.toString()+",office:"+office+",salary:"+salary+",year:"+year+",month:"+month+",day:"+day;
	}
	
	
}
class Faculty extends Employee
{
	public Faculty(String name, String address, String pnum, String email,String office, double salary,String time,String level) {
		super(name, address, pnum, email, email, salary);
		this.time = time;
		this.level = level;
	}
	private String time;
	private String level;
	@Override
	public String toString() {
		return super.toString()+",time:"+time+",level:"+level;
	}
	
}
class Staff extends Employee
{
	public Staff(String name, String address, String pnum, String email,String office, double salary,String title) {
		super(name, address, pnum, email, email, salary);
		this.title = title;
	}

	private String title;

	@Override
	public String toString() {
		return super.toString()+",title:"+title;
	}
	
}