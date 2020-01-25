
import java.util.Scanner;

public class T4 {

	public static void main(String[] args) {
		// P260 8.7
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of points:");
		int numberofPoints = in.nextInt();
		double[][] points = new double[numberofPoints][3];
		System.out.println("Enter "+numberofPoints + " points:");
		for(int i=0; i<points.length; i++)
		{
			points[i][0] = in.nextDouble();
			points[i][1] = in.nextDouble();
			points[i][2] = in.nextDouble();
		}
		int p1 = 0,p2 = 1;
		double shortDistance = distance(points[p1][0],points[p1][1],points[p1][2],points[p2][0],points[p2][1],points[p2][2]);
		for(int i=0; i<points.length; i++)
		{
			for(int j=i+1; j<points.length; j++)
			{
				double distance = distance(points[i][0],points[i][1],points[i][2],points[j][0],points[j][1],points[j][2]);
				if(shortDistance > distance)
				{
					p1 = i;
					p2 = j;
					shortDistance = distance;
				}
			}
		}
		System.out.println("The closest two points are "+"("+points[p1][0]+", "+points[p1][1]+", "+points[p1][2]+")and("+
		points[p2][0]+", "+points[p2][1]+", "+points[p2][2]+")");
	}

	public static double distance(double x1,double y1,double z1,double x2,double y2,double z2)
	{
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)+(z1-z2)*(z1-z2));
	}

}
