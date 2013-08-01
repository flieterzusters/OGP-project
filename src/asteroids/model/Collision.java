package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of all collisions, between two space objects or between a space object and a boundary.
 * @author Tom De Ferm
 * @version 0.1
 *
 */
public class Collision {
	
	/**
	 * Constructor for the collision between a space object and a boundary.
	 * @param collisionTime
	 * 		  The time remaining until this collision.
	 * @param spaceobject1
	 * 		  The space object involved in the collision.
	 * @post This given time is set as the new collisionTime of the collision.
	 * 		 | new.getCollisionTime()==collisionTime
	 * @post This given SpaceObject is set as the space object of the collision.
	 * 		 | new.getSpaceObject1()==spaceobject1
	 */
	public Collision(double collisionTime, SpaceObject spaceobject1)
	{
		setCollisionTime(collisionTime);
		setSpaceObject1(spaceobject1);
	}
	

	/**
	 * Constructor for the collision between two space objects.
	 * @param collisionTime
	 * 		  The time remaining until this collision.
	 * @param spaceobject1
	 * 		  The space object involved in the collision.
	 * @param spaceobject2
	 * 		  The second space object involved in the collision.
	 * @post This given time is set as the new collisionTime of the collision.
	 * 		 | new.getCollisionTime()==collisionTime
	 * @post This given SpaceObject is set as the space object of the collision.
	 * 		 | new.getSpaceObject1()==spaceobject1
	 * @post This given SpaceObject is set as the second space object of the collision.
	 * 		 | new.getSpaceObject2()==spaceobject2
	 */
	
	public Collision(double collisionTime, SpaceObject spaceobject1, SpaceObject spaceobject2)
	{
		setCollisionTime(collisionTime);
		setSpaceObject1(spaceobject1);
		setSpaceObject2(spaceobject2);
	}
	
	/**
	 * @return the time to collision.
	 */
	public double getCollisionTime() {
		return this.collisionTime;
	}
	
	/**
	 * Sets the time until collision.
	 * @param collisionTime
	 * 		  The time until this collision.
	 */
	public void setCollisionTime(double collisionTime) {
		if(collisionTime < 0) {throw new IllegalArgumentException();}
		
		this.collisionTime = collisionTime;
	}
	
	/**
	 * Time until the collision.
	 */
	private double collisionTime;
	
	/**
	 * @return The first (and maybe only) SpaceObject of the collision.
	 */
	@Basic @Immutable
	public SpaceObject getSpaceObject1() {
		return this.spaceobject1;
	}
	
	/**
	 * Sets the the first spaceobject of the collision.
	 * @param spaceobject1
	 */
	public void setSpaceObject1(SpaceObject spaceobject1) {
		this.spaceobject1 = spaceobject1;
	}
	
	/**
	 * The first (and maybe only) spaceobject involved in the collision.
	 */
	private SpaceObject spaceobject1;
	
	/**
	 * @return The second SpaceObject of the collision.
	 */
	@Basic @Immutable
	public SpaceObject getSpaceObject2() {
		return this.spaceobject2;
	}
	
	/**
	 * Sets the the second spaceobject of the collision.
	 * @param spaceobject2
	 */
	public void setSpaceObject2(SpaceObject spaceobject2) {
		this.spaceobject2 = spaceobject2;
	}
	
	/**
	 * The second spaceobject involved in the collision.
	 */
	private SpaceObject spaceobject2;
	
	/**
	 * @param spaceObject
	 * @return True if this spaceobject equals spaceboject1 (or spaceobject2).
	 */
	public boolean attachedSpaceObject(SpaceObject spaceobject) {
		if(spaceobject2==null) {
		return spaceobject.equals(spaceobject1);}
		else {
			return (spaceobject.equals(spaceobject1) || spaceobject.equals(spaceobject2));
		}
	}
	
	/**
	 * A little method to subtract the time interval from the time to collision.
	 * @param dt
	 */
	public void evolve(double dt) {
		if(dt > collisionTime) {throw new RuntimeException();}
		else {
			setCollisionTime(collisionTime - dt);
		}
	}
	
	/**
	 * Resolves this collision, depending on whether it is a collision between
	 * a spaceobject and a boundary or two spaceobjects.
	 */
	public void resolve() {
		if(spaceobject2 == null) {
		spaceobject1.resolveBoundaryCollision();}
		else {
			spaceobject1.resolve(spaceobject2);
		}
	}
	
	
}
