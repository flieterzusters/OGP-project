package asteroids.model.programs.Statement.standardStatement;

import asteroids.model.Program;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;

public class Print extends Statement {
	
	public Print (int line, int column, Program program, Expression e) {
		super(line, column, program);
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
