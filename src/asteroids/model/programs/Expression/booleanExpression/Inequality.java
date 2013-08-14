package asteroids.model.programs.Expression.booleanExpression;
import asteroids.model.programs.BooleanT;
import asteroids.model.programs.DoubleT;
import asteroids.model.programs.EntityT;
import asteroids.model.programs.Type;
import asteroids.model.programs.Expression.*;
import asteroids.model.*;

public class Inequality extends ComposedBoolean {
	
	public Inequality(int line, int column, Program program, Expression e1, Expression e2) {
		super(line, column, program, new BooleanT(), e1, e2);
	}

	@Override
	public Type getValue() {
		
		Type type = checkBool();
		Type exp1 = getExpression1().getValue();
		Type exp2 = getExpression2().getValue();
		boolean result = false;
		try {
		if(type instanceof DoubleT) {
			result = (((DoubleT) exp1).getValue() != ((DoubleT) exp2).getValue());
		}
		else if(type instanceof EntityT) {
			result = (((EntityT) exp1).getValue() != ((EntityT) exp2).getValue());
		}
		else {
			result = (((BooleanT) exp1).getValue() != ((BooleanT) exp2).getValue());
		}}
		catch(IllegalArgumentException illegalargument) {
			throw new IllegalArgumentException(illegalargument);
		}
		return new BooleanT(result);
		}
}
