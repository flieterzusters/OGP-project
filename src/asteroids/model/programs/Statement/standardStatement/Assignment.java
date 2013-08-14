package asteroids.model.programs.Statement.standardStatement;
import asteroids.model.Program;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;
import asteroids.model.programs.*;

public class Assignment extends Statement {
	
	public Assignment (int line, int column, Program program, String variable, Expression rhs) {
		super(line, column, program);
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
		
		Type type = getExpression().getValue();
	
		getProgram().setGlobal(this.string, type);
	}

}
