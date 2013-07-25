package asteroids.model.programs.Expression;

public class Multiplication extends ComposedDouble {
	
	public Multiplication(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	@Override
	public double getValue() {
		double product = (((DoubleExpression) e1).getValue() * ((DoubleExpression) e2).getValue());
		return product;
	}

}
