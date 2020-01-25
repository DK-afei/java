package project2;

public class ComparableRectangle  extends Rectangle implements Comparable<ComparableRectangle>{

	public ComparableRectangle(double width, double height) {
		super(width, height);
	}

	@Override
	public int compareTo(ComparableRectangle o) {
		if(this.getArea()>o.getArea())
			return 1;
		else if(getArea()<o.getArea())
			return -1;
		else 
			return 0;
	}
	
}
