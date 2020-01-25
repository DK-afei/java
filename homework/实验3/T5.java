import java.util.Scanner;

public class T5 {

	public static void main(String[] args) {
		//P308 9.13
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of rows and columns in the array:");
		int row = in.nextInt();
		int column = in.nextInt();
		double[][] a = new double[row][column];
		System.out.println("Enter the array:");
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<a[i].length; j++)
			{
				a[i][j] = in.nextDouble();
			}
		}
		Location l = new Location();
		System.out.println("The location of the largest element is "+l.locatelargest(a).maxValue+" at"+"("+l.locatelargest(a).row+","+l.locatelargest(a).column+")");
	}

}
class Location
{
	public static int row;
	public static int column;
	public static double maxValue;
	public static Location locatelargest(double[][] a)
	{
		maxValue = a[0][0];
		Location l = new Location();
		for(l.row=0; l.row<a.length; l.row++)
		{
			for(l.column=0;l.column<a[l.row].length; l.column++)
			{
				if(maxValue<a[l.row][l.column])
				{
					maxValue = a[l.row][l.column];
					break;
				}
			}
		}
		return l;
	}
	public int getRow()
	{
		return row;
	}
	public int getColumn()
	{
		return column;
	}
}