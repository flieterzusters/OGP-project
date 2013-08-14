package asteroids.model.programs.Expression;

import asteroids.model.programs.*;
import asteroids.model.*;

public class Variable extends Expression {
	
	public Variable (int line, int column, Program program, String string)
	{
		super(line, column, program);
		setString(string);
	}
	
	@Override
	public Type getValue() {
		Type type = getProgram().getGlobal(value);
		return type;
	}
	
	public String getString() {
		return this.value;
	}
	
	public void setString(String string) {
		this.value = string;
	}
	
	
	private String value;

}
