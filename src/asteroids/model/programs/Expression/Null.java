package asteroids.model.programs.Expression;
import asteroids.model.programs.*;
import asteroids.model.*;

public class Null extends Expression {
	
	public Null(int line, int column, Program program) {
		super(line, column, program, new EntityT());
		
	}
	
	@Override
	public Type getValue() {
		return new EntityT(null);
	}
	

}
