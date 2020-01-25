package project;


import java.util.GregorianCalendar;

public class Test2 {
	private int year;
	private int month;
	private int day;
	public Test2() {
		GregorianCalendar g = new GregorianCalendar();
		this.year = g.get(GregorianCalendar.YEAR);
		this.month =g.get(GregorianCalendar.MONTH);
		this.day =g.get(GregorianCalendar.DAY_OF_MONTH);
	}
	public Test2(long l) {
		GregorianCalendar g = new GregorianCalendar();
		g.setTimeInMillis(l);
		this.year = g.get(GregorianCalendar.YEAR);
		this.month =g.get(GregorianCalendar.MONTH);
		this.day =g.get(GregorianCalendar.DAY_OF_MONTH);
	}
	public Test2(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public void setDate(long elapsedTime)
	{
		GregorianCalendar g = new GregorianCalendar();
		g.setTimeInMillis(elapsedTime);
		this.year = g.get(GregorianCalendar.YEAR);
		this.month =g.get(GregorianCalendar.MONTH);
		this.day =g.get(GregorianCalendar.DAY_OF_MONTH);
	}
}
