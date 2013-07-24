package asteroids.model.programs;

public abstract class BooleanExpression extends Expression{
	
	public BooleanExpression (int line, int column)
	{
		super(line, column, new Boolean());
	}

	public abstract boolean getValue();
	
}
