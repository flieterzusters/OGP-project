package asteroids.model.programs.Statement.standardStatement;


import asteroids.model.programs.Statement.*;

import asteroids.model.*;

import java.util.List;
import java.util.ArrayList;

import asteroids.model.programs.Statement.Statement;

public class Sequence extends StandardStatement {
	
	public Sequence(int line, int column, List<Statement> statements) {
		super(line, column);
		setStatements(statements);
	}
	
	public List<Statement> getStatements() {
		return this.statements;
	}
	
	public void setStatements(List<Statement> statements) {
		
		List<Statement> statsToAdd = new ArrayList<Statement>();
		
		for (Statement statement: statements) {
			if (statement != null) {
				if (statement instanceof Sequence) 
				{
					statsToAdd.addAll(((Sequence)statement).getStatements());
				} 
				else {
					statsToAdd.add(statement);
				}
			}
		}
		this.statements = statsToAdd;
	}
	
	private List<Statement> statements;

	
	@Override
	public void setProgram(Program program){
		
		if (!statements.isEmpty()) {
			for (Statement statement: statements) {
				if (statement != null){
					statement.setProgram(program);
				}
			}
		}
		this.program = program;
	}
	
	
	@Override
	public void execute() {
		
		if (statements.isEmpty()) {return;}
		
		for (Statement statement: statements) 
		{
			if (statement != null) 
			{
				statement.execute();
				this.getProgram().setExecutePosition(statement.getLine());
				
				if (statement instanceof ActionStatement) {return;}
			}
		}
	}
	
	public void executeNextCommand(int line) {
		
		line = line-1;
		if(statements.isEmpty()){return;}
		
		while (true) 
		{
			Statement statement = statements.get(line);
			if (statement != null) 
			{
				statement.execute();
				line++;
				line = line%statements.size();
				
				if (statement instanceof ActionStatement) 
				{
					this.getProgram().setExecutePosition(line+1);
					return;
				}
			}
		}
	}
}
		

