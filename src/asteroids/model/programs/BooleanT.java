package asteroids.model.programs;

public class BooleanT extends Type {
	
		
		public BooleanT(){
			
		}
		
		public BooleanT (boolean value) {
			setValue(value);
		}
		
		public void setValue(boolean value) {
			this.value = value;
		}
		
		
		public boolean getValue() {
			return value;
		}
		
		private boolean value = false;

}


