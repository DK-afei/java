package project;

import java.util.Date;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account a = new Account();
		System.out.println(a.toString());
		CheckingAccount ca = new CheckingAccount();
		System.out.println(ca.toString());
		SavingAccount sa = new SavingAccount();
		System.out.println(sa.toString());
	}

}
class Account
{
	private int id;
	private double balance;
	private double annualInterestRate;
	private Date dateCreated = null;
	@Override
	public String toString() {
		return "id:"+id+",balance:"+balance+",annualInterestRate:"+annualInterestRate+",dateCreated:"+dateCreated;
	}
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
class CheckingAccount extends Account
{
	private double limits;
	@Override
	public String toString() {
		return super.toString()+",limits:"+limits;
	}
	
}
class SavingAccount extends Account
{
	public String warning()
	{
		return "²»ÄÜÍ¸Ö§";
	}
	@Override
	public String toString() {
		return super.toString()+"("+warning()+")";
	}
	
}