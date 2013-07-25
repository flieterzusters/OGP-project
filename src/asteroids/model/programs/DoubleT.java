package asteroids.model.programs;

public class DoubleT extends Type {
	
	public DoubleT(){
		
	}
	
	public DoubleT(double value) {
		setValue(value);
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	
	public double getValue() {
		return value;
	}
	
	private double value = 0.0;

}
