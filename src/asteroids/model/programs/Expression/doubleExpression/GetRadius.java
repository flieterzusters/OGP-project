package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;


public class GetRadius extends DoubleExpression {
	
	public GetRadius(int line, int column, Expression e) {
		super(line, column);
		this.spaceobject = e;
	}
	
	public double getValue() {
		EntityLiteral ship = (EntityLiteral) spaceobject;
		return ship.getValue().getRadius();
	}
	
	private Expression spaceobject;

}
