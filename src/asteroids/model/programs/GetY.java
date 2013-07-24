package asteroids.model.programs;

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
