package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.EntityT;
import asteroids.model.programs.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;


public class GetRadius extends SingleExpression {
	
	public GetRadius(int line, int column, Program program, Expression e) {

		super(line, column, program, new DoubleT(), e);

	}
	
	@Override
	public Type getValue() {
		SpaceObject spaceobject = ((EntityT) expression.getValue()).getValue();
		return new DoubleT(spaceobject.getRadius());
	}


}
