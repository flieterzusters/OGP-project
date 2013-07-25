package asteroids.model.programs.Expression;
import asteroids.model.programs.*;

public class Expression extends Code {
	

		protected Expression(int line,int column, Type type) {
			super(line,column);
			setType(type);
		}
		
		protected Expression(int line, int column) {
			super(line, column);
		}
		
		public Type getType() {
			return type;
		}
		
		public void setType(Type type) {
			this.type = type;
		}
		
		private Type type;
		

}


