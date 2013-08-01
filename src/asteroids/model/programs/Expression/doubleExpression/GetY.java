package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.Expression.*;


public class GetY extends DoubleExpression {
	
	public GetY (int line, int column, Expression e) {
		super(line, column);
		this.spaceobject = e;
	}
	
	@Override
	public double getValue() {
		EntityLiteral ship = (EntityLiteral) spaceobject ;
		return ship.getValue().getPosition().getY();
	}
	
	private Expression spaceobject;
	

}
