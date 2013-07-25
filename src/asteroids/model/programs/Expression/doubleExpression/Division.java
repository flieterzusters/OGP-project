package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;

public class Division extends ComposedDouble {
	
	public Division(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	@Override
	public double getValue() {
		double quotient = (((DoubleExpression) e1).getValue() / ((DoubleExpression) e2).getValue());
		return quotient;
	}

}
