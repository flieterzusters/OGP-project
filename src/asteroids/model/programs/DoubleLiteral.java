package asteroids.model.programs;

public class DoubleLiteral extends DoubleExpression{
	
	public DoubleLiteral(int line, int column, double value)
	{
		super(line, column);
		setValue(value);
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}
	
	private double value = 0.0;
	
	@Override
	public double getValue() {
		return this.value;
	}
	

}
