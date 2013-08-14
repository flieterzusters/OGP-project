package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.*;
import asteroids.model.*;

public class GetDir extends Expression {
	
	public GetDir(int line, int column, Program program) {
		super(line, column, program, new DoubleT());
	}
	
	@Override
	public Type getValue() {
		Ship currentship = getProgram().getShip();
		double value = currentship.getDirection();
		return new DoubleT(value);
	}

}
