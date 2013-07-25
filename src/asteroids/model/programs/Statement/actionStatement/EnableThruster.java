package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.*;
import asteroids.model.programs.Statement.*;

public class EnableThruster extends ActionStatement {
	
	public EnableThruster(int line, int column) {
		super (line, column);
	}
	
	@Override
	public void execute() {
		Ship currentship = getProgram().getShip();
		if(currentship == null) {throw new NullPointerException();}
		currentship.setThrusterActive(true);
		
	}

}
