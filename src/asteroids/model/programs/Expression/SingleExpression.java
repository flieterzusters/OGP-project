package asteroids.model.programs.Expression;
import asteroids.model.*;
import asteroids.model.programs.*;

public abstract class SingleExpression extends Expression {
	
	public SingleExpression(int line, int column, Program program, Type type, Expression e1) {
		super(line, column, program, type);
		setExpression(e1);
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	public void setExpression(Expression exp1) {
		this.expression = exp1;
	}
	
	protected Expression expression;

	

}
