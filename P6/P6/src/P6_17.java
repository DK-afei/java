
public class P6_17 {
	static void cCos(double a,double b,double[] e,double[] f)	//������
	{
		double p,q;
	    p=Math.exp(b); 
		q=1/p;	
	    e[0]=Math.cos(a)*(p+q)/2.0; 						//ʵ��
		f[0]=-1.0*Math.sin(a)*(p-q)/2.0;					//�鲿
	}
	public static void main(String[] args) {
		double a,b;
		double[] e={0},f={0};
		a=1.0;b=4.0;							//��ʼ��

		cCos(a,b,e,f);						//����������
		System.out.printf("Cos(%f+%fi)= %f+%fi\n",a,b,e[0],f[0]);

	}

}
