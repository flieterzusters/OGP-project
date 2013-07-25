package asteroids.model.programs.Statement.standardStatement;

import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;


public class If extends StandardStatement {
	
	public If(int line, int column, Expression condition, Statement then, Statement otherwise) {
		super(line, column);
		setCondition(condition);
		setThen(then);
		setOtherwise(otherwise);
	}
	
	public Expression getCondition() {
		return condition;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	private Expression condition;
	
	public Statement getThen() {
		return then;
	}
	
	public void setThen(Statement then) {
		this.then = then;
	}
	
	private Statement then;
	
	public Statement getOtherwise() {
		return otherwise;
	}
	
	public void setOtherwise(Statement otherwise) {
		this.otherwise = otherwise;
	}
	
	private Statement otherwise;
	
	@Override
	public void execute() {
		if(((BooleanExpression) getCondition()).getValue()) {
			then.execute();}
		else{otherwise.execute();}
			
		}
}


