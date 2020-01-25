import java.util.Arrays;

public class T3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StopWatch t = new StopWatch();
		int[] a = new int[100000];
		int keyvalue;
		int index;
		int temp;
		for(int i=0; i<a.length; i++)
		{
			a[i] = (int)Math.random()*100000;
		}
		t.start();
		for(int i=0; i<a.length; i++)
		{
			index = i;
			keyvalue = a[i];
			for(int j=i; j<a.length; j++)
			{
				if(a[j]<keyvalue)
				{
					index = j;
					keyvalue = a[j];
				}
			}
			temp = a[index];
			a[index] = a[i];
			a[i] = temp;
		}
		t.stop();
		System.out.println(t.getElapsedTime());	
	}

}
class StopWatch
{
	private long startTime;
	private long endTime;
	StopWatch()
	{
		startTime = System.currentTimeMillis();
	}
	public long getStartTime()
	{
		return startTime;
	}
	public long getEndTime()
	{
		return endTime;
	}
	public void start()
	{
		startTime = System.currentTimeMillis();
	}
	public void stop()
	{
		endTime = System.currentTimeMillis();
	}
	public long getElapsedTime()
	{
		return endTime - startTime;
	}
}