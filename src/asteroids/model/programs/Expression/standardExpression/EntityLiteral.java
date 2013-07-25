package asteroids.model.programs.Expression.standardExpression;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.*;
import asteroids.model.*;

public class EntityLiteral extends Expression {
	
	public EntityLiteral (int line, int column, SpaceObject value)
	{
		super(line, column, new EntityT());
		setValue(value);
	}
	
	public void setValue(SpaceObject value) {
		this.spaceobject = value;
	}
	
	private SpaceObject spaceobject;

	
	public SpaceObject getValue() {
		return this.spaceobject;
	}
	
	public String toString() {
		return "SpaceObject";
	}
	

}
