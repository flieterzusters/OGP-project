package asteroids.model.programs.Expression;

import asteroids.model.*;

public class Variable extends DoubleExpression {
	
	public Variable (int line, int column, String string)
	{
		super(line, column);
		setString(string);
	}
	
	@Override
	public double getValue() {
		return ((Double) getProgram().getGlobal(getString())).getValue();
		
	}
	
	public String getString() {
		return this.value;
	}
	
	public void setString(String string) {
		this.value = string;
	}
	
	
	private String value;

}
