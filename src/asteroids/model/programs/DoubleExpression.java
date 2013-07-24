package asteroids.model.programs;

public abstract class DoubleExpression extends Expression{
	
	public DoubleExpression(int line, int column)
	{
		super(line, column, new Double());
	}
	
	public abstract double getValue();

}
