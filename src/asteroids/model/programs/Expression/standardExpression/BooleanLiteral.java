package asteroids.model.programs.Expression.standardExpression;
import asteroids.model.programs.Expression.*;

public class BooleanLiteral extends BooleanExpression {
	
	public BooleanLiteral (int line, int column, boolean b)
	{
		super (line, column);
		setValue(b);
	}
	
	public void setValue(boolean b) {
		this.value = b;
	}
	
	private boolean value;
	
	@Override
	public boolean getValue() {
		return this.value;
	}

}
