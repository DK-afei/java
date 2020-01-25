package zuoye;

import java.util.Date;
import java.util.Scanner;

public class T5 {

	public static void menuPrint()
	{
		System.out.println("Main menu");
		System.out.println("1: check balance");
		System.out.println("2: withdraw");
		System.out.println("3: deposit");
		System.out.println("4: exit");
	}
	public static void main(String[] args) {
		//P340 10.7
		Scanner in = new Scanner(System.in);
		ATM atm = new ATM();
		while(true)
		{
			System.out.println("Enter an id:");
			int id = in.nextInt();
			if(id<0||id>9) {continue;}
			else
				while(true)
				{
					menuPrint();
					System.out.println("Enter a choice:");
					int n = in.nextInt();
					switch(n)
					{
						case 1:System.out.println("The balance is:"+atm.getA(id).getBalance());continue;
						case 2:
								{
									System.out.println("Enter an amount to withdraw:");
									double m = in.nextDouble();
									atm.getA(id).withDraw(m);
									continue;
								}
						case 3:
								{
									System.out.println("Enter an amount to deposit:");
									double m = in.nextDouble();
									atm.getA(id).deposit(m);
									continue;
								}
						case 4:	break;
						}
					}
			}
	}
	}
class ATM
{
	private Account[] a = new Account[10];
	public ATM()
	{
		for(int i=0; i<a.length; i++)
		{
			a[i] = new Account();
			a[i].setId(i);
			a[i].setBalance(100);
		}
	}
	public Account getA(int i)
	{
		return a[i];
	}
}
class Account
{
	private int id;
	private double balance;
	private double annualInterestRate;
	private Date dateCreated = null;
	public Account(){}
	public Account(int id,double balance)
	{
		this.id = id;
		this.balance = balance;
	}
	public int getId()
	{
		return id;
	}
	public double getBalance()
	{
		return balance;
	}
	public double getAnnualInterestRate()
	{
		return annualInterestRate;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	public void setAnnualInterestRate(double annualInterestRate)
	{
		 this.annualInterestRate = annualInterestRate;
	}
	public Date getdateCreated()
	{
		return dateCreated;
	}
	public double getMonthlyInteresrRate()
	{
		double rate = annualInterestRate/12;
		return rate;
	}
	public void withDraw(double money)
	{
		balance -= money;
	}
	public void deposit(double money)
	{
		balance += money;
	}
}