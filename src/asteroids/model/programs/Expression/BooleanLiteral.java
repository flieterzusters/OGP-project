package asteroids.model.programs.Expression;
import asteroids.model.*;
import asteroids.model.programs.*;


public class BooleanLiteral extends Expression {
	
	public BooleanLiteral (int line, int column, Program program, boolean b)
	{
		super (line, column, program, new BooleanT(b));
	}

	@Override
	public Type getValue() {
		return getType();
	}

}
