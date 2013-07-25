package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;

public class Substraction extends ComposedDouble {
	
	public Substraction (int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	@Override
	public double getValue() {
		double difference = (((DoubleExpression) e1).getValue() - ((DoubleExpression) e2).getValue());
		return difference;
	}

}
