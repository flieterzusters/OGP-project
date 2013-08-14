package asteroids.model.programs.Statement;
import asteroids.model.programs.Statement.standardStatement.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ComplexStatement extends Statement {
	
	public ComplexStatement (int line, int column, Program program, Statement body, Expression condition) {
		super(line, column, program);
		setBody(body);
		setCondition(condition);
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	private Expression condition;
	
	public Sequence getSequence() {
		return this.sequence;
	}
	
	public void setBody(Statement body) {
		if(body instanceof Sequence) { this.sequence = (Sequence) body; }
		else {
			List<Statement> statementsList = new ArrayList<Statement>();
			this.sequence = new Sequence(line, column, program, statementsList);
		}
	}
	
	private Sequence sequence;
	
	public abstract Sequence getBody();
	

}
