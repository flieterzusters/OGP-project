package asteroids.model.programs.Statement;
import asteroids.model.*;

public abstract class ActionStatement extends Statement {
	
	public ActionStatement (int line, int column, Program program) {
		super(line, column, program);
	}
	
	public abstract void execute();

}
