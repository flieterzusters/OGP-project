package asteroids.model.programs;
import asteroids.model.Program;

public class Code {
	
	protected Code(int line,int column){
		
		setLine(line);
		setColumn(column);
	}

	public void setProgram(Program program){
		this.program = program;
	}

	public Program getProgram(){
		return program;
	}

	public int getLine() {
		return line;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn (int column) {
		this.column = column;
	}

	protected Program program;
	private int line;
	private int column;

}



