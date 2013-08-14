package asteroids.model;
import asteroids.model.programs.Statement.*;
import asteroids.model.programs.*;
import java.util.*;

public class Program {
	
	public Program(){
		this.globals = new HashMap<String,Type>();
		this.statement = null;
	}
	
	public Program(Map<String, Type> globals, Statement statement) {
		setGlobals(globals);
		setStatement(statement);
	}
	
	public Map<String,Type> getGlobals() {
		return globals;
	}
	
	public void setGlobals(Map<String, Type> globals) {
		this.globals = new HashMap<String,Type>(globals);
	}
	
	public Type getGlobal (String name) {
		return globals.get(name);
	}
	
	public void setGlobal(String name, Type type) {
		globals.put(name, type);
	}
	
	private Map<String,Type> globals;
	
	public Statement getStatement() {
		return statement;
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;
		this.statement.setProgram(this);
	}
	
	private Statement statement;
	
	public Ship getShip() {
		return ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	private Ship ship;
	
	private void executeNext() {
		statement.execute();
		
	}
	
	public void execute(int value) {
		
		for (int i=0; i<value; i++){
			executeNext();
		}
	}
	

}
