package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Expression.standardExpression.*;

public class GetY extends DoubleExpression {
	
	public GetY (int line, int column, Expression e) {
		super(line, column);
		this.object = (EntityLiteral) e;
	}
	
	@Override
	public double getValue() {
		return object.getValue().getPosition().getY();
	}
	
	private EntityLiteral object;
	

}
