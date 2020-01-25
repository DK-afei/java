
import java.util.Arrays;
import java.util.Scanner;

public class T2 {

	public static void main(String[] args) {
		// P236 7.3
		int i = 0;
		int b = 0;
    	int m = 0;
		int[] c = new int[100];
		Scanner in = new Scanner(System.in);
		System.out.println("请输入数据，输入0结束：");
		while(true){
			b = in.nextInt();
			if(b==0)
				break;
				c[i++] = b;
		}
		int[] a = new int[i];
		for(int n=0; n<a.length;n++){
			a[n] = c[n];
		}
		Arrays.sort(a);
		for(int j=0; j<a.length; j++){
			    m=1;
			if(j>0&&a[j]==a[j-1])
				continue;
			for(int k=j+1; k<a.length; k++){
				if(a[k]==a[j])
					m++;
			}
			System.out.println(a[j]+"出现的次数为："+m);
		}

	}

}
