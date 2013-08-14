package asteroids.model.programs.Statement.standardStatement;

import asteroids.model.programs.Statement.*;



import asteroids.model.*;

import java.util.List;
import java.util.ArrayList;


public class Sequence extends Statement {
	
	public Sequence(int line, int column, Program program, List<Statement> statements) {
		super(line, column, program);
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
	public void execute() {
		setAction(false);
		
		for(int i = getExecutePosition();i<=getStatements().size();i++) {
			Statement currentstatement = statements.get(i-1);
			
			if(currentstatement != null) {
				currentstatement.execute();
				
				if(currentstatement instanceof ActionStatement) {
					setAction(true);
					setExecutePosition(i+1);
					return;
				}
				
				if(currentstatement instanceof ComplexStatement) {
					
					ComplexStatement loop = (ComplexStatement) currentstatement;
					Sequence sequence = loop.getBody();
					
					if(sequence.actionHasOccured()) {
						if (sequence.getExecutePosition() == (sequence.getStatements().size()+1)) {
							setExecutePosition(i+1);
							sequence.setExecutePosition(1);
						}
						else {
							setExecutePosition(1);
						}
						
						setAction(true);
						return;
					}
					
					if (sequence.getExecutePosition() == (sequence.getStatements().size()+1)) {
						setExecutePosition(1);
					}
					
	
					
					
				}
			}
			
		}
		setExecutePosition(getStatements().size()+1);
		setAction(false);
		
		
	}
	
	public boolean actionHasOccured() {
		return actionoccured;
	}
	
	public void setAction(boolean value) {
		this.actionoccured = value;
	}
	
	private boolean actionoccured=false;
	
	public int getExecutePosition() {
		return this.executeposition;
	}
	
	public void setExecutePosition(int value) {
		this.executeposition = value;
	}
	
	private int executeposition=1;
}
		

