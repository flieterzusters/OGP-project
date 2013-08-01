package asteroids.model;


/**
 * A class of space stations that have a position, velocity, radius, direction and mass.
 * Space stations can move, apply thrust, turn and load ships or other space stations. 
 * 
 * @author Tom De Ferm
 * @version 0.1
 */

public class SpaceStation extends Ship {
	
	public SpaceStation(Vector position, Vector velocity, double radius, double angle, double mass, double capacity) {
		
		super(position, velocity, radius, angle, mass);
		this.capacity = capacity;
	}
	
	/**
	 * @return The capacity of this particular space station.
	 */
	public double getCapacity() {
		return this.capacity;
	}
	
	
	/**
	 * The capacity of a space station is the maximum weight it can handle.
	 */
	private final double capacity;
	
	/**
	 * The load this space station is currently carrying.
	 * This load should not exceed the capacity of this space station.
	 */
	private double currentload = 0.0;
	
	public void land(Ship ship) {
		
	}
	
	

}
