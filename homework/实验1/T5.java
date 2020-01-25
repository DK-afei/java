package dengxiaoping_1_5;

import java.util.Scanner;

public class T5 {
	final static int r = 40;
	public static void main(String[] args) {
		//P128 4.6
		Scanner in = new Scanner(System.in);
		double x1,y1,x2,y2,x3,y3;
		x1 = Math.random()*80-39;
		y1 = Math.sqrt(40*40-x1*x1);
		x2 = Math.random()*80-39;
		y2 = Math.sqrt(40*40-x2*x2);
		x3 = Math.random()*80-39;
		y3 = Math.sqrt(40*40-x3*x3);
		double a = Math.sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3));
		double b = Math.sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));
		double c = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		double A = Math.toDegrees(Math.acos((a*a-b*b-c*c)/(-2*b*c)));
		double B = Math.toDegrees(Math.acos((b*b-a*a-c*c)/(-2*a*c)));
		double C = Math.toDegrees(Math.acos((c*c-b*b-a*a)/(-2*a*b)));
		System.out.printf("三个顶点分别为（%.2f，%.2f），（%.2f，%.2f），（%.2f，%.2f）\n",x1,y1,x2,y2,x3,y3);
		System.out.println("the three angles are "+
		Math.round(A*100)/100.0+" "+
		Math.round(B*100)/100.0+" "+
		Math.round(C*100)/100.0);
	}

}
