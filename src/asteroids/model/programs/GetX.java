package asteroids.model.programs;

public class GetX extends DoubleExpression {
	
	public GetX (int line, int column, Expression e) {
		super(line, column);
		this.object = (EntityLiteral) e;
	}
	
	@Override
	public double getValue() {
		return object.getValue().getPosition().getX();
	}
	
	private EntityLiteral object;
	

}
