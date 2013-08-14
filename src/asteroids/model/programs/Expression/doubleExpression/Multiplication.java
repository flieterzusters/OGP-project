package asteroids.model.programs.Expression.doubleExpression;
import asteroids.model.programs.DoubleT;
import asteroids.model.programs.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;

public class Multiplication extends ComposedDouble {
	
	public Multiplication(int line, int column, Program program, Expression e1, Expression e2) {
		super(line, column, program, new DoubleT(), e1, e2);
	}
	
	@Override
	public Type getValue() {
		double product = (((DoubleT)(e1.getValue())).getValue() * ((DoubleT)(e2.getValue())).getValue());
		return new DoubleT(product);
	}

}
