package asteroids.model.programs.Statement.standardStatement;
import asteroids.model.programs.Statement.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;
import asteroids.model.programs.*;

public class For extends ComplexStatement {
	
	public For(int line, int column, Program program, Expression condition, Statement body) {
		super(line, column, program, body, condition);
	}
	
	@Override
	public Sequence getBody() {
		return getSequence();
	}

	@Override
	public void execute() {
		double condition = ((DoubleT) getCondition().getValue()).getValue();
		int times = (int) Math.round(condition);
		int index = 0;
		while(index<times) {
			getBody().execute();
			index++;
		}
		
	}
}
