package asteroids.model.programs;

public class Boolean extends Type{
	
	public Boolean(){
		
	}
	
	public Boolean (boolean value) {
		setValue(value);
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}
	
	
	public boolean evaluate() {
		return value;
	}
	
	private boolean value;

}
