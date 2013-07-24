package asteroids.model.programs;

public class Double extends Type {
	
	public Double(){
		
	}
	
	public Double(double value) {
		setValue(value);
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	
	public double evaluate() {
		return value;
	}
	
	private double value;

}



