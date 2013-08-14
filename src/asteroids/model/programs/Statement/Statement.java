package asteroids.model.programs.Statement;
import asteroids.model.programs.*;
import asteroids.model.*;

public abstract class Statement extends Code {
	
	public Statement(int line, int column, Program program) {
		super (line, column, program);
	}
	
	public abstract void execute();

}
