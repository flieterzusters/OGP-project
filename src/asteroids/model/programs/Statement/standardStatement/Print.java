package asteroids.model.programs.Statement.standardStatement;

import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;

public class Print extends StandardStatement {
	
	public Print (int line, int column, Expression e) {
		super(line, column);
		setExpression(e);
	}
	
	public Expression getExpression() {
		return e;
	}
	
	public void setExpression(Expression e) {
		this.e = e;
	}
	
	private Expression e;
	
	@Override
	public void execute() {
		System.out.println(getExpression().toString());
	}

}
