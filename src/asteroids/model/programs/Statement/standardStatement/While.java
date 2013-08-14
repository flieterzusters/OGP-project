package asteroids.model.programs.Statement.standardStatement;

import asteroids.model.programs.Expression.*;
import asteroids.model.programs.*;
import asteroids.model.programs.Statement.*;
import asteroids.model.*;

public class While extends ComplexStatement {
	
	public While(int line, int column, Program program, Expression condition, Statement body) {
		super(line, column, program, body, condition);
	}
	
	
	@Override
	public void execute() {
		boolean condition = ((BooleanT) getCondition().getValue()).getValue();
		while(condition) {
			getBody().execute();
			
			if(getSequence().getExecutePosition() > getSequence().getStatements().size()) {
				getSequence().setExecutePosition(1);
			}
			if(getSequence().actionHasOccured()) {return;}
		}
	}
	
	@Override
	public Sequence getBody() {
		return getSequence();
	}
	

}
