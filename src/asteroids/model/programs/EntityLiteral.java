package asteroids.model.programs;

import asteroids.model.*;

public class EntityLiteral extends Expression {
	
	public EntityLiteral (int line, int column, SpaceObject value)
	{
		super(line, column, new Entity());
		setValue(value);
	}
	
	public void setValue(SpaceObject value) {
		this.spaceobject = value;
	}
	
	private SpaceObject spaceobject;

	
	public SpaceObject getValue() {
		return this.spaceobject;
	}
	

}
