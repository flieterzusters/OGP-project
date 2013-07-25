package asteroids.model.programs.Statement;

public abstract class StandardStatement extends Statement{
	
	public StandardStatement(int line, int column) {
		super(line, column);
	}
	
	public abstract void execute();

}
