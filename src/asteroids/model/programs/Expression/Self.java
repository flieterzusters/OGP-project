package asteroids.model.programs.Expression;
import asteroids.model.*;
import asteroids.model.programs.*;

public class Self extends Expression {
	
	public Self(int line, int column, Program program) {
		super(line, column, program, new EntityT());
	}
	
	public Type getValue() {
		return new EntityT(getProgram().getShip());
	}
	
	@Override
	public String toString() {
		return "SpaceObject";
	}

}
