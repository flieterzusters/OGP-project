package asteroids.model;

import java.util.HashSet;
import java.util.Set;


/**
 * A class of space stations that have a position, velocity, radius, direction and mass.
 * Space stations can move, apply thrust, turn and load ships or other space stations. 
 * 
 * @author Tom De Ferm
 * @version 0.1
 */

public class SpaceStation extends Ship {
	
	/**
	 * Creates a new space station with user defined parameters.
	 * 
	 * @param position
	 * 		  The initial position of the space station.
	 * @param velocity
	 * 		  The initial velocity of the space station.
	 * @param radius
	 * 		  The initial radius of the space station.
	 * @param angle 
	 * 		  The direction the space station will face, expressed in radians (0 is to the right, Pi is to the left).
	 * @param mass 
	 * 		  The total mass of the space station (in kg).
	 * @param capacity
	 * 		  The total capacity of the space station.
	 */ 
	
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
	
	/**
	 * Lands a ship (space station) in this space station. If there is no space left
	 * for the ship (space station), it will be terminated. 
	 * @param ship
	 * 		  The ship (space station) to be landed on the space station.
	 */
	public void land(Ship ship) {
		if(!permissionToLand(ship)) {ship.terminate();}
		this.currentload = currentload + ship.getMass();
		landedObjects.add(ship);
	}
	
	/**
	 * Launches a ship (space station) back in space, with a higher speed than the station and the same direction.
	 * @param ship
	 * 		  The ship to be launched in space.
	 * 
	 * @throws IllegalArgumentException
	 * 		   A station can't launch a ship (space station) that never landed on this station in the first place.
	 */
	public void launch(Ship ship) {
		if(!landedObjects.contains(ship)) {throw new IllegalArgumentException();}
		Vector shipspeed = getVelocity().multiply(1.1);
		ship.setVelocity(shipspeed);
		ship.setAngle(this.getDirection());
		double shipxpos = this.getPosition().getX()+this.getRadius();
		double shipypos = this.getPosition().getY();
		ship.setPosition(new Vector(shipxpos, shipypos));
		this.currentload = currentload - ship.getMass();
		
	}
	
	/**
	 * Terminates this space station and removes it from its current world.
	 * Before the space station is terminated, it launches all the ships and other
	 * space stations it contains.
	 */
	@Override
	public void terminate() {
		for(Ship ship : landedObjects) 
		{
			launch(ship);
		}
		super.terminate();
	}
	
	/**
	 * Resolves the collision between this space station and another space object.
	 * @param spaceobject
	 * 		  The space object in collision with this space station.
	 * 
	 * @effect If the other space object is an asteroid or a bullet, the space station will die.
	 * 		   |if(spaceobject instanceof Asteroid || spaceobject instanceof Bullet)
	 * 		   |then terminate()
	 * @effect If the other space object is a ship or a lighter space station, 
	 * 		   it will try to land on this space station.
	 * 		   |if(spaceobject instanceof Ship)
	 * 		   |then land((Ship) spaceobject)
	 */
	@Override
	public void resolve(SpaceObject spaceobject) {
		if(spaceobject instanceof Asteroid || spaceobject instanceof Bullet) {
			terminate();}
		if(spaceobject instanceof Ship) {
			
			land((Ship) spaceobject);}
	}
	
	/**
	 * Checks whether a certain ship will be able to land without exceeding the station's
	 * capacity.
	 * @param ship
	 * 		  The ship that wants to land on this station.
	 * @return True if the capacity won't be exceeded, false if the capacity will be
	 * 		   exceeded.
	 */
	public boolean permissionToLand(Ship ship) {
		return (this.currentload + ship.getMass() < this.capacity);
	}
	
	/**
	 * A set containing objects which landed successfully on this space station.
	 */
	private Set<Ship> landedObjects = new HashSet<Ship>();
	

}
