package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.DoubleT;
import asteroids.model.programs.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;

public class Sqrt extends ComposedDouble {
	
	public Sqrt (int line, int column, Program program, Expression e1) {
		super(line, column, program, new DoubleT(), e1);
	}
	
	@Override
	public Type getValue() {
		double sqrt = Math.sqrt(((DoubleT)(e1.getValue())).getValue());
		return new DoubleT(sqrt);
	}

}
