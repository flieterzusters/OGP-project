package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.*;
import asteroids.model.*;



public class GetX extends SingleExpression {
	
	public GetX (int line, int column, Program program, Expression e) {
		super(line, column, program, new DoubleT(), e);
	
		
	}
	
	@Override
	public Type getValue() {
		SpaceObject spaceobject = ((EntityT) expression.getValue()).getValue();
		return new DoubleT(spaceobject.getPosition().getX());
	}

	

	
	
	
	
	

}
