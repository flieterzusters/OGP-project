package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;

public class GetDir extends DoubleExpression {
	
	public GetDir(int line, int column) {
		super(line, column);
	}
	
	@Override
	public double getValue() {
		Ship currentship = getProgram().getShip();
		double value = currentship.getDirection();
		return value;
	}

}
