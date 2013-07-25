package asteroids.model.programs;
import asteroids.model.SpaceObject;

public class EntityT extends Type {

	public EntityT (SpaceObject value) {
		setValue(value);
	}
	
	public EntityT(){
		
	}
	
	public void setValue(SpaceObject value) {
		this.value = value;
	}
	
	
	public SpaceObject getValue() {
		return value;
	}
	
	private SpaceObject value = null;
}
