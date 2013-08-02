package asteroids.model.programs.Statement.standardStatement;

import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;
import asteroids.model.*;

public class While extends StandardStatement {
	
	public While(int line, int column, Expression condition, Statement body) {
		super(line, column);
		setCondition(condition);
		setBody(body);
	}
	
	public Expression getCondition() {
		return condition;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	private Expression condition;
	
	public Statement getBody() {
		return body;
	}
	
	public void setBody(Statement body) {
		this.body = body;
	}
	
	private Statement body;
	
	@Override
	public void execute() {
		while(((BooleanExpression) getCondition()).getValue()) {
			body.execute();
		}
	}
	

}
