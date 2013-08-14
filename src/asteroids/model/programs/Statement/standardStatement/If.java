package asteroids.model.programs.Statement.standardStatement;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.model.programs.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;


public class If extends ComplexStatement {
	
	public If(int line, int column, Program program, Expression condition, Statement then, Statement otherwise) {
		super(line, column, program, then, condition);
		setOtherwise(otherwise);
	}

	
	public Sequence getOtherwise() {
		return otherwise;
	}
	
	public void setOtherwise(Statement otherwise) {
		if(otherwise instanceof Sequence) { this.otherwise = (Sequence) otherwise; }
		else {
			List<Statement> statementsList = new ArrayList<Statement>();
			this.otherwise = new Sequence(line, column, program, statementsList);
		}
	}
	
	private Sequence otherwise;
	
	@Override
	public void execute() {
		boolean condition = ((BooleanT) getCondition().getValue()).getValue();
		Sequence todo = null;
		if(condition) {todo = getBody();}
		else {todo = getOtherwise();}
		
		todo.execute();
	}
	
	@Override
	public Sequence getBody() {
		return getSequence();
	}
}


