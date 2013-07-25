package asteroids.model.programs.Statement.actionStatement;
import asteroids.model.Ship;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;

public class Turn extends ActionStatement {
	
	public Turn(int line, int column, Expression angle) {
		super(line, column);
		setAngle(angle);
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public void setAngle(Expression angle) {
		this.angle = ((DoubleExpression) angle).getValue();
	}
	
	private double angle;
	
	@Override
	public void execute() {
		Ship currentship = getProgram().getShip();
		if(currentship == null) {throw new NullPointerException();}
		currentship.turn(angle);
	}

}
