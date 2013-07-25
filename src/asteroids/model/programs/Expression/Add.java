package asteroids.model.programs.Expression;

public class Add extends ComposedDouble {
	
	public Add(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	@Override
	public double getValue() {
		double sum = (((DoubleExpression) e1).getValue() + ((DoubleExpression) e2).getValue());
		return sum;
	}

}
