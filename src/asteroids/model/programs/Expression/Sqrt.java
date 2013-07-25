package asteroids.model.programs.Expression;

public class Sqrt extends ComposedDouble {
	
	public Sqrt (int line, int column, Expression e1) {
		super(line, column, e1);
	}
	
	@Override
	public double getValue() {
		double sqrt = Math.sqrt(((DoubleExpression) e1).getValue());
		return sqrt;
	}

}
