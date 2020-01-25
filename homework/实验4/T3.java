package zuoye;

public class T3 {

	public static void main(String[] args) {
		// P339 10.1
		Time t1 = new Time();
		t1.showTime();
		Time t2 = new Time(555550000);
		t2.setTime(555550000);
		t2.showTime();
	}

}
class Time
{
	private int hour;
	private int minute;
	private int second;
	private long t;
	public Time()
	{
		long t = System.currentTimeMillis();
		t /= 1000;
		second = (int)t%60;
		minute = (int)t/60%60;
		hour = (int)t/60/60%24+8;
	}
	public void showTime()
	{
		System.out.println(hour+":"+minute+":"+second);
	}
	public Time(long t)
	{
		this.t = t/1000;
	}
	public Time(int hour,int minute,int second)
	{
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getHour()
	{
		return hour;
	}
	public int getMinute()
	{
		return minute;
	}
	public int getSecond()
	{
		return second;
	}
	public void setTime(long elapseTime)
	{
		long t = elapseTime;
		t /= 1000;
		second = (int)t%60;
		minute = (int)t/60%60;
		hour = (int)t/60/60%24;
	}
}