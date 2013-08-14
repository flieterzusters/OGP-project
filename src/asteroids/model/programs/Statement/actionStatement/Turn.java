package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.Program;
import asteroids.model.programs.*;
import asteroids.model.Ship;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;

public class Turn extends ActionStatement {
	
	public Turn(int line, int column, Program program, Expression angle) {
		super(line, column, program);
		this.angle = angle;
	
	}

	@Override
	public void execute() {
		double angle = ((DoubleT) getAngle().getValue()).getValue();
		Ship currentship = getProgram().getShip();
		currentship.turn(angle);
	}
	
	public Expression getAngle() {
		return this.angle;
	}
	
	private Expression angle;

}
