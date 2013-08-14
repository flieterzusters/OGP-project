package asteroids.model.programs.Expression;
import asteroids.model.*;
import asteroids.model.programs.*;

public abstract class ComposedBoolean extends Expression {
	
	public ComposedBoolean(int line, int column, Program program, Type type, Expression e1, Expression e2)
	{
		super(line, column, program, type);
		setExpression1(e1);
		setExpression2(e2);
	}
		
	public Expression getExpression1() {
		return this.e1;
	}
	
	public void setExpression1(Expression e1) {
		this.e1 = e1;
	}
	
	protected Expression e1;
	
	public Expression getExpression2() {
		return this.e2;
	}
	
	public void setExpression2(Expression e2) {
		this.e2 = e2;
	}
	
	protected Expression e2;
	
	public Type checkBool() {
		Type type1=getExpression1().getValue();
		Type type2=getExpression2().getValue();
		
		if(type1 instanceof BooleanT && type2 instanceof BooleanT) {
			return (BooleanT) type1;
		}
		
		else if(type1 instanceof DoubleT && type2 instanceof DoubleT) {
			return (DoubleT) type1;
		}
		
		else if(type1 instanceof EntityT && type2 instanceof EntityT) {
			return (EntityT) type1;
		}
		
		else {throw new IllegalArgumentException();}
		
	}
	
	
}



