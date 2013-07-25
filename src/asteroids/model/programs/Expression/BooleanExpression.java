package asteroids.model.programs.Expression;
import asteroids.model.programs.*;

public abstract class BooleanExpression extends Expression{
	
	public BooleanExpression (int line, int column)
	{
		super(line, column, new BooleanT());
	}

	public abstract boolean getValue();
	
	@Override
	public String toString() {
		return Boolean.toString(getValue());
	}
	
}
