package project0;

public class Test0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<2; i++)
		{
			System.out.print(i + " ");
			try
			{
				System.out.println(1/0);
			}
			catch(Exception ex)
			{
				
			}
		}
	}

}
