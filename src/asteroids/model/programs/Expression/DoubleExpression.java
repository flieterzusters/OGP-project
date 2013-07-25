package asteroids.model.programs.Expression;
import asteroids.model.programs.*;

public abstract class DoubleExpression extends Expression{
	
	public DoubleExpression(int line, int column)
	{
		super(line, column, new DoubleT());
	}
	
	public abstract double getValue();
	
	@Override
	public String toString() {
		return Double.toString(getValue());
	}

}
