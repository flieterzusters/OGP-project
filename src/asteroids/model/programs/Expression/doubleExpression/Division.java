package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.DoubleT;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;
import asteroids.model.programs.*;

public class Division extends ComposedDouble {
	
	public Division(int line, int column, Program program, Expression e1, Expression e2) {
		super(line, column, program, new DoubleT(), e1, e2);
	}
	
	@Override
	public Type getValue() {
		double quotient = (((DoubleT)(e1.getValue())).getValue() / ((DoubleT)(e2.getValue())).getValue());
		return new DoubleT(quotient);
	}

}
