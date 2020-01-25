package project4;

public class Complex implements Cloneable{
	private double a;
	private double b;
	public Complex()
	{
		this.a = 0;
		this.b = 0;
	}
	public Complex(double a)
	{
		this.a = a;
		this.b = 0;
	}
	public Complex(double a,double b)
	{
		this.a = a;
		this.b = b;
	}
	public double getRealPart()
	{
		return a;
	}
	public double getImaginaryPart()
	{
		return b;
	}
	public String add(Complex c)
	{
		double a;
		double b;
		a = this.a + c.a;
		b = this.b + c.b;
		return "("+this.a+" + "+this.b+"i)"+" + "+"("+c.a+" + "+c.b+"i)"+" = "+a+" + "+b+"i";
	}
	public String substract(Complex c)
	{
		double a;
		double b;
		a = this.a - c.a;
		b = this.b - c.b;
		return "("+this.a+" + "+this.b+"i)"+" - "+"("+c.a+" + "+c.b+"i)"+" = "+a+" + "+b+"i";
	}
	public String multiply(Complex c)
	{
		double a;
		double b;
		a = this.a*c.a - this.b*c.b;
		b = this.b*c.a + this.a*c.b;
		return "("+this.a+" + "+this.b+"i)"+" * "+"("+c.a+" + "+c.b+"i)"+" = "+a+" + "+b+"i";
	}
	public String divide(Complex c)
	{
		double a;
		double b;
		a = (this.a*c.a + this.b*c.b)/(c.a*c.a+c.b*c.b);
		b = (this.b*c.a - this.a*c.b)/(c.a*c.a+c.b*c.b);
		return "("+this.a+" + "+this.b+"i)"+" / "+"("+c.a+" + "+c.b+"i)"+" = "+a+" + "+b+"i";
	}
	public String abs()
	{
		double absValue;
		absValue = Math.sqrt(this.a*this.a + this.b*this.b);
		return "|("+this.a+" + "+this.b+"i)|"+" = "+absValue;
	}
	public String toString()
	{
		if(b!=0)
			return a+"+"+b+"i"; 
		else
			return a+"";
				
				
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}