package asteroids.model.programs.Expression;
import asteroids.model.programs.*;

public abstract class Expression extends Code {
	

		protected Expression(int line,int column, Type type) {
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
		
		public abstract String toString();
		
		
		

}


