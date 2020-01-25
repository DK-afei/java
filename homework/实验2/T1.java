
import java.util.Scanner;

public class T1 {
	//P198 6.3
	public static int reverse(int number){
		int b = 0;
		int i = 0;
		int flag = number;
		do
		{
			if(number==0)
				break;
			number/=10;
				i++;
		}while(number!=0);
		number = flag;
		int j = i - 1;
		String s = number+"";
		for(int k=(i-1); k>=0; k--,j--)
		{
			b += ((s.charAt(k)-'0')*Math.pow(10, j));
		}
		return b;
	}
	public static boolean isPalindrome(int number){
		if(number==0)
			return true;
		else
			return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个整数：");
		int a = in.nextInt();
		if(isPalindrome(reverse(a)-a))
		{
			System.out.println(a+"是回文整数");
		}
		else
		{
			System.out.println(a+"不是回文整数");
		}
	}

}
