package asteroids.model;

import asteroids.model.programs.*;
import java.util.*;

public class Program {
	
	public Program(){
		setGlobals(null);
		setStatement(null);
		
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
	
	private Map<String,Type> globals;
	
	public Statement getStatement() {
		return statement;
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	private Statement statement;
	
	public Ship getShip() {
		return ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	private Ship ship;
	

}
