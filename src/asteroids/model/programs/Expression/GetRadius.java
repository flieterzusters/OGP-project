package asteroids.model.programs.Expression;

public class GetRadius extends DoubleExpression {
	
	public GetRadius(int line, int column, Expression e) {
		super(line, column);
		this.object = (EntityLiteral) e;
	}
	
	public double getValue() {
		return object.getValue().getRadius();
	}
	
	private EntityLiteral object;

}
