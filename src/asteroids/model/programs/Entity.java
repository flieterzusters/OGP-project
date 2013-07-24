package asteroids.model.programs;
import asteroids.model.*;

public class Entity extends Type{
	
	public Entity(){
		
	}
	
	public Entity (SpaceObject value) {
		setValue(value);
	}
	
	public void setValue(SpaceObject value) {
		this.value = value;
	}
	
	
	public SpaceObject evaluate() {
		return value;
	}
	
	private SpaceObject value;

}


