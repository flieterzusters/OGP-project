package asteroids.model.programs.Expression;

public abstract class ComposedDouble extends DoubleExpression {
	
	public ComposedDouble(int line, int column, Expression e1, Expression e2) {
		
		super(line, column);
		setExpression1(e1);
		setExpression2(e2);
	}
	
	public ComposedDouble(int line, int column, Expression e1) {
		super(line, column);
		setExpression1(e1);
	}
	
	public Expression getExpression1(){
		return e1;
	}
	
	public void setExpression1(Expression e1) {
		this.e1 = e1;
	}
	
	protected Expression e1;
	
	public Expression getExpression2() {
		return e2;
	}
	
	public void setExpression2(Expression e2) {
		this.e2 = e2;
	}
	
	protected Expression e2;

}
