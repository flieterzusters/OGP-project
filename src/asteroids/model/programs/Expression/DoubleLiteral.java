package asteroids.model.programs.Expression;
import asteroids.model.*;
import asteroids.model.programs.*;

public class DoubleLiteral extends Expression{
	
	public DoubleLiteral(int line, int column, Program program, double value)
	{
		super(line, column, program, new DoubleT(value));
	}
	
	
	@Override
	public Type getValue() {
		return getType();
	}
	

}
