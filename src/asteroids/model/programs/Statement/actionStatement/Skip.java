package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.Program;
import asteroids.model.programs.Statement.*;

public class Skip extends ActionStatement {
	
	public Skip(int line, int column, Program program) {
		super (line, column, program);
	}
	
	@Override
	public void execute() {}
	
}
