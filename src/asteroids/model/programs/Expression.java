package asteroids.model.programs;

public class Expression extends Code {
	

		public Expression(int line,int column, Type type) {
			super(line,column);
			setType(type);
		}
		
		public Type getType() {
			return type;
		}
		
		public void setType(Type type) {
			this.type = type;
		}
		
		private Type type;
		

}


