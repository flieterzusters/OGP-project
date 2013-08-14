package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.*;
import asteroids.model.programs.Statement.*;


public class DisableThruster extends ActionStatement {
	
	public DisableThruster(int line, int column, Program program) {
		super (line, column, program);
	}
	
	@Override
	public void execute() {
		Ship currentship = getProgram().getShip();
		currentship.setThrusterActive(false);
		
	}

}
