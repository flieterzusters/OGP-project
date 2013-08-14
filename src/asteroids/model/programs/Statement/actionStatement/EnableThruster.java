package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.*;
import asteroids.model.programs.Statement.*;

public class EnableThruster extends ActionStatement {
	
	public EnableThruster(int line, int column, Program program) {
		super (line, column, program);
	}
	
	@Override
	public void execute() {
		Ship currentship = getProgram().getShip();
		currentship.setThrusterActive(true);
		
	}

}
