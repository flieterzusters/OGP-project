package asteroids.model.programs.Statement.standardStatement;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;
import asteroids.model.programs.*;

public class Assignment extends StandardStatement {
	
	public Assignment (int line, int column, String variable, Expression rhs) {
		super(line, column);
		setString(variable);
		setExpression(rhs);
	}
	public String getString() {
		return this.string;
	}
	
	public void setString (String variable) {
		this.string = variable;
	}
	
	private String string;
	
	public Expression getExpression() {
		return this.rhs;
	}
	
	public void setExpression(Expression rhs) {
		this.rhs = rhs;
	}
	
	private Expression rhs;
	
	@Override
	public void execute() {
		Type type = null;
		if(rhs instanceof DoubleExpression) {
			type = new DoubleT(((DoubleExpression) rhs).getValue());
		}
		else if(rhs instanceof BooleanExpression) {
			type = new BooleanT(((BooleanExpression) rhs).getValue());
		}
	
		getProgram().setGlobal(this.string, type);
	}

}
