package asteroids.model.programs.Expression.booleanExpression;
import asteroids.model.programs.DoubleT;
import asteroids.model.programs.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;

public class LessThanOrEqualTo extends ComposedBoolean {
	
	public LessThanOrEqualTo (int line, int column, Program program, Expression e1, Expression e2) {
		super(line, column, program, new BooleanT(), e1, e2);
	}

	@Override
	public Type getValue() {
		boolean result = (((DoubleT)(e1.getValue())).getValue() <= ((DoubleT)(e2.getValue())).getValue());
		return new BooleanT(result);
	}

}
