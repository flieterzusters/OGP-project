package asteroids.model.programs.Expression.booleanExpression;
import asteroids.model.Program;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.*;

public class TrueOrFalse extends ComposedDouble {
	
	public TrueOrFalse(int line, int column, Program program, Type type, Expression e1, Expression e2, Expression condition) {
		super(line, column, program, type, e1, e2);
		setCondition(condition);
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	protected Expression condition;
	
	@Override
	public Type getValue() {
		boolean condition = ((BooleanT) getCondition().getValue()).getValue();
		double result;
		if(condition) {
			result= ((DoubleT) e1.getValue()).getValue();
		}
		else {
			result = ((DoubleT) e2.getValue()).getValue();
		}
		
		return new DoubleT(result);
		
	}

}
