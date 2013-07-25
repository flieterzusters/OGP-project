package asteroids.model.programs.Expression.booleanExpression;
import asteroids.model.programs.Expression.*;

public class Equality extends ComposedBoolean {
	
	public Equality(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}

	@Override
	public boolean getValue() {
		boolean value = (((DoubleExpression) e1).getValue() == ((DoubleExpression) e2).getValue());
		return value;
	}

}
