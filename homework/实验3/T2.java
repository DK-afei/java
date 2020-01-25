public class T2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stock s = new Stock("ORCL","Oracle Corporation");
		s.setCurrentPrice(34.35);
		System.out.println(s.getChangePercent());
	}

}
class Stock
{
	private String symbol;
	private String name;
	private double previousClosingPrice = 34.5;
	private double  currentPrice;
	Stock(String symbol, String name)
	{
		this.symbol = symbol;
		this.name = name;
	}
	public double getChangePercent()
	{
		double rate;
		rate  = (currentPrice-previousClosingPrice)/previousClosingPrice;
		return rate;
	}
	public void setCurrentPrice(double currentPrice)
	{
		this.currentPrice = currentPrice; 
	}
}