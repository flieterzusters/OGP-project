package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.programs.Statement.*;

public class Skip extends ActionStatement {
	
	public Skip(int line, int column) {
		super (line, column);
	}
	
	@Override
	public void execute() {}
	
}
