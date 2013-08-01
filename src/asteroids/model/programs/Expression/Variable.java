package asteroids.model.programs.Expression;

import asteroids.model.programs.*;

public class Variable extends DoubleExpression {
	
	public Variable (int line, int column, String string)
	{
		super(line, column);
		setString(string);
	}
	
	@Override
	public double getValue() {
		Type type = getProgram().getGlobal(value);
		double value = ((DoubleT) type).getValue();
		return value;
		
		
	}
	
	public String getString() {
		return this.value;
	}
	
	public void setString(String string) {
		this.value = string;
	}
	
	
	private String value;

}
