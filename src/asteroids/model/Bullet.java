package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Util;

/**
 * A class of bullets that have a position, velocity, radius and mass.
 * 
 * @invar A bullet's speed limit can never exceed the speed of light (c=300000 km/s).
 * 		  | getSpeedLimit =< 300000
 * 
 * @invar A bullet's speed can never exceed it's speed limit. 
 * 	      | getBulletVelocity =< getBulletSpeedLimit
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
	 * @param x	
	 * 		  The initial x-coordinate of the bullet, expressed in km.
	 * @param y	
	 * 		  The initial y-coordinate of the bullet, expressed in km.
	 * @param xVelocity 
	 * 		  The initial velocity in the x-direction the bullet will have, expressed in km/s.
	 * @param yVelocity 
	 * 		  The initial velocity in the y-direction the bullet will have, expressed in km/s.
	 * @param radius 
	 * 		  The bullet's radius, expressed in km.
	 * @param mass
	 * 		  The mass of this bullet.
	 * @param source
	 * 		  The source of this bullet.
	 * 
	 *  @effect This new bullet is initialized as a SpaceObject with a given position, speed, radius and mass.
	 *        | super(x, y, xVelocity, yVelocity, radius, mass)
	 *        
	 * @post The given source is set as the source of this bullet.
	 * 		  | new.getSource() == source
	 */
	
	public Bullet(double x, double y, double xVelocity,
			double yVelocity, Ship source) {
		
		super(x, y, xVelocity, yVelocity, minimumRadius);
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
	 * Returns the number of bounces the bullet has made.
	 * @return The number of bounces the bullet has made.
	 */
	public int getNumberOfBounces() {
		return numberOfBounces;
	}
	
	/**
	 * Sets the number of bounces the bullet has made.
	 * @param bounce Number of bounces the bullet makes.
	 */
	public void setNumberOfBounces(int bounce) {
		this.numberOfBounces = bounce;
	}
	
	/**
	 * Number of bounces the bullet has made.
	 */
	private int numberOfBounces=0;
	
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
		this.numberOfBounces++;
		if (numberOfBounces == 2) {this.Die();}
		
		else {super.resolveBoundaryCollision();}
		
			
	}
	
	/**
	 * Resolves the collision between this bullet and another space object.
	 * @param spaceobject
	 * 			he space object in collision with this bullet.
	 * 
	 * @effect If spaceobject is the source of this bullet, only this bullet will die.
	 * 		   Otherwise both the bullet and the spaceobject will die.
	 * 			|if(spaceobject == source)	then this.Die()
	 * 			|else
	 * 			|	Die()
	 * 			|	spaceobject.Die()
	 */
	@Override
	public void resolve(SpaceObject spaceobject)
	{
		if(spaceobject == source) 
		{
			this.Die();
		}
		
		else
		{
				Die();
				spaceobject.Die();
		}
			
			
		
			
	}

}
