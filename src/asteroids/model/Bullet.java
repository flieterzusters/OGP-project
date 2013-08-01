package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Util;

/**
 * A class of bullets that have a position, velocity, radius and mass.
 * 
 * @invar The source of this bullet must be valid at all times.
 * 		  | isValidSource(getSource())
 * 
 * @author Tom De Ferm
 * @version 0.1
 */

public class Bullet extends SpaceObject {
	
	/**
	 * Creates a new Bullet with user defined parameters.
	 * 
	 * @param position
	 * 		  The initial position of the space object.
	 * @param velocity
	 * 		  The initial velocity of the space object.
	 * @param radius
	 * 		  The initial radius of the space object.
	 * @param mass
	 * 		  The mass of this bullet.
	 * @param source
	 * 		  The source of this bullet.
	 * 
	 *  @effect This new bullet is initialized as a SpaceObject with a given position, speed, radius and mass.
	 *        | super(position, velocity, radius, mass)
	 *        
	 * @post The given source is set as the source of this bullet.
	 * 		  | new.getSource() == source
	 */
	
	public Bullet(Vector position, Vector velocity, double radius, World world, Ship source) {
		
		super(position, velocity, radius);
		if(isValidSource(source)){
			this.source = source;}
		}
	
	/**
	 * The bullet's radius is currently fixed at 3 km.
	 */
	private static final double minimumRadius = 3;
	
	/**
	 * Returns the bullet's mass, expressed in kg.
	 * @return The bullet's mass (in kg).
	 */
	@Override
	public double getMass() {
		
		double Mass = (4/3)*Math.PI*massDensity*Math.pow(getRadius(), 3);
		return Mass;
	}
	
	/**
	 * The mass density of each Bullet is fixed (in kg/km³).
	 */
	private static final double massDensity = 7.8*Math.pow(10, 12);
	
	
	/**
	 * Returns the ship from which this bullet is fired.
	 * @return The ship from which this bullet is fired.
	 */
	@Basic @Immutable
	public Ship getSource() {
		
		return source;
	}
	
	/**
	 * Checks whether the source is a valid source.
	 * @param source
	 * 			The source to check.
	 * @return True if the given source is valid, false if it isn't.
	 * 			| result = (source != null)
	 * 
	 */
	private boolean isValidSource(Ship source)
	{
		return (source != null);
	}
	
	/**
	 * The ship this bullet is fired from.
	 */
	private Ship source;
	
	
	/**
	 * False if the bullet hasn't bounced on a wall, true if it has bounced.
	 */
	private boolean bounced=false;
	
	/**
	 * Makes the bounced field true. If a bullet collides with a boundary now, it will die.
	 */
	public void setBounced() {
		this.bounced = true;
	}
	
	/**
	 * Returns the initial speed of the bullet, expressed in km/s.
	 * @return The initial speed of the bullet, expressed in km/s.
	 */
	public double getBulletSpeed() {
		
		return bulletSpeed;
	}
	
	/**
	 * The initial speed of the bullet, expressed in km/s.
	 */
	private static final double bulletSpeed = 250;
	
	/**
	 * Resolves the collision between this bullet and a boundary.
	 * If the bullet hits a boundary for the second time, it dies.
	 * Otherwise it will bounce off the wall like any other space object.
	 */
	@Override
	public void resolveBoundaryCollision()
	{
		if (bounced) {this.terminate();}
		
		else {setBounced();
			super.resolveBoundaryCollision();}
		
			
	}
	
	/**
	 * Resolves the collision between this bullet and another space object.
	 * @param spaceobject
	 * 			he space object in collision with this bullet.
	 * 
	 * @effect If spaceobject is the source of this bullet, only this bullet will die.
	 * 		   Otherwise both the bullet and the spaceobject will die.
	 * 			|if(spaceobject == source)	then this.terminate()
	 * 			|else
	 * 			|	terminate()
	 * 			|	spaceobject.terminate()
	 */
	@Override
	public void resolve(SpaceObject spaceobject)
	{
		if(spaceobject == source) 
		{
			this.terminate();
		}
		
		else
		{
				terminate();
				spaceobject.terminate();
		}
			
			
		
			
	}

}
