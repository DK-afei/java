package project;
class Triangle extends GeometricObject
	{
		private double a;
		private double b;
		private double c;
		
		Triangle(double a, double b, double c,String color,boolean filled) {
			super(color,filled);
			if(a+b>c&&a+c>b&&c+b>a)
			{
				this.a = a;
				this.b = b;
				this.c = c;
			}
		}

		@Override
		public double getArea() {
			double s;
			double p;
			p = (a+b+c)/2;
			s = Math.sqrt((p-a)*(p-b)*(p-c));
			return s;
		}

		@Override
		public double getPerimeter() {
			double l;
			l = a + b + c;
			return l;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString()+"\n三边为："+a+","+b+","+c+"\n三角形周长和面积分别为："+this.getPerimeter()+"和"+this.getArea();
		}
		
	}
