package asteroids.model;


import asteroids.Util;

import be.kuleuven.cs.som.annotate.*;



/**
 * A class of ships that have a position, velocity, radius, direction and mass.
 * Ships can move, apply thrust, turn and fire bullets. The functionality for collision detection is located in the class SpaceObject.
 * 
 * @invar A ship's speed limit can never exceed the speed of light (c=300000 km/s).
 * 		  | getSpeedLimit() =< 300000
 * 
 * @invar A ship's speed can never exceed it's speed limit. 
 * 	      | getVelocity() =< getSpeedLimit()
 * 
 * @invar The value of angle will always be between 0 and 2*Pi (inclusive)
 * 		  | 0 <= angle <= 2*Pi
 * 
 * @invar The mass of the ship must be valid at all times.
 * 		  | isValidMass(getMass())
 * 
 * @author Tom De Ferm
 * @version 0.1
 */

public class Ship extends SpaceObject {
	
	/**
	 * Creates a new Ship with user defined parameters.
	 * @param x	
	 * 		  The initial x-coordinate of the ship, expressed in km.
	 * @param y	
	 * 		  The initial y-coordinate of the ship, expressed in km.
	 * @param xVelocity 
	 * 		  The initial velocity in the x-direction the ship will have, expressed in km/s.
	 * @param yVelocity 
	 * 		  The initial velocity in the y-direction the ship will have, expressed in km/s.
	 * @param radius 
	 * 		  The ship's radius, expressed in km.
	 * @param angle 
	 * 		  The direction the ship will face, expressed in radians (0 is to the right, Pi is to the left).
	 * @param mass 
	 * 		  The total mass of the ship (in kg).
	 * 
	 * @effect This new ship is initialized as a SpaceObject with a given position, speed and radius.
	 *        | super(x, y, xVelocity, yVelocity, radius)
	 *        
	 * @post The given angle is now set as the new direction of the ship.
	 *       | new.getAngle() == angle
	 * @post The given mass is now set as the new mass of the ship.
	 *       | new.getMass() == mass
	 * @post The acceleration of the ship is now set, based on the given mass.
	 * 		 | new.getAcceleration == acceleration
	 * @throws IllegalArgumentException
	 *         If the mass is not a valid mass.
	 *       | (! isValidMass(mass))     
	 */
	
	public Ship(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle, double mass) {
		
		super( x, y, xVelocity, yVelocity, radius);							
		setAngle(angle);
		setMass(mass);
		setAcceleration(mass);
		}
	
	
	
	/**
	 * @return The ship's current acceleration in space, expressed in km/s.
	 */
	
	@Basic
	public double getAcceleration() {
		
		return acceleration;
	}
	
	/**
	 * Sets the acceleration of this ship, based on the mass of the ship and the thrusterForce.
	 * @param mass
	 * 		  The total mass of the ship.
	 */
	private void setAcceleration(double mass) {
		
		acceleration = (thrusterForce/mass);
	}
	
	/**
	 * The ship's current acceleration in space.
	 */
	private double acceleration;
	
	
	
	/**
	 * Returns the "condition" of the ship's thruster. The thruster is either enabled or disabled.
	 * @return True if the thruster is enabled, false if the thruster is disabled.
	 */
	@Basic
	public boolean isThrusterActive() 
	{		
		return thrusterActive;
	}

	/**
	 * Enables the thruster from this ship.
	 * @param condition 
	 * 		  The condition of the ship's thruster. If condition is true, the thruster is active.
	 */
	public void setThrusterActive (boolean condition) 
	{
		thrusterActive = condition;
	}
	
	/**
	 * An active thruster exerts 1,1 * 10^18 Newton per second on its ship. 	
	 */
	private final double thrusterForce = 1.1*Math.pow(10,18);
	
	/**
	 * thrusterActive shows whether the thruster is enabled or not.
	 * Initially a thruster is disabled.
	 */
	private boolean thrusterActive = false;
		
	/**
	 * Changes the velocity of the ship by an acceleration.
	 * This method changes the velocity of the ship according to its current velocity and the direction it's facing
	 * 
	 * @post If the acceleration of the ship is equal to or less than zero, no change to the ship's velocity is made.
	 * 	     | if(getAcceleration =< 0) then 
	 *       |       new getXVelocity = getXVelocity
	 *       |	     new getYVelocity = getYVelocity
	 * @post The new velocity vector is equal to the old velocity vector changed by the acceleration in the ship's current direction.
	 *       | new getXVelocity = getXVelocity + getAcceleration*cos(angle)
	 *       | new getYVelocity = getYVelocity + getAcceleration*sin(angle)
	 * @effect If the new total velocity would exceed the ship's upper speed limit, 
	 * 		   the new total velocity is changed to be equal to the speed of light without changing the direction of the velocity.
	 * 		 
	 */
	public void thrust(double dt) {
		
		if (thrusterActive) {
		
		xVelocity = (this.getXVelocity() + (dt*this.getAcceleration()*Math.cos(this.getDirection())));
		yVelocity = (this.getYVelocity() + (dt*this.getAcceleration()*Math.cos(this.getDirection())));
		}
		
		if (!Util.fuzzyLessThanOrEqualTo(this.getVelocity(), speedLimit)) {
			
			this.makeVelocityValid(xVelocity, yVelocity);			
		}
	}
	
	
	/**
	 * @return The direction the ship is facing.
	 */
	
	@Basic
	public double getDirection() {
		
		return angle;
	}
	
	/**
	 * Turns the ship over a given angle in radians. <code>angle</code> can be negative 
	 * @param angle The amount of radians to be added to the ships direction. 
	 * @pre the given angle must be a number and finite.
	 * 		| (! angle.isNaN() && angle < Double.POSITIVE_INFINITY)
	 * @post The given angle is added to the ship's direction
	 * 		| new this.angle = this.angle + angle
	 */
	public void turn(double angle) {
		
		setAngle(this.angle + angle);
		
	}
	
	/**
	 * Sets the given angle as the new angle for the ship.
	 * @param angle
	 * @pre The given angle must be a number and must be finite.
	 *  	| (! angle.isNaN() && angle < Double.POSITIVE_INFINITY)
	 */
	private void setAngle(double angle) {	
		this.angle = angle;
		while (this.angle < 0) {
			this.angle += 2*Math.PI;
		}
		while (this.angle > 2*Math.PI) {
			this.angle -= 2*Math.PI;
		}
	}
		
	/**
	 * The direction the ship is currently facing
	 */
	private double angle;
	
	/**
	 * @return The total mass of the ship.
	 */
	@Basic @Override
	public double getMass () {
		
		return mass;
	}

	/**
	 * Sets the given mass as the ships new mass.
	 * @param Mass
	 * @throws RuntimeException(), when the mass isn't valid.
	 * 		   | (! isValidMass(Mass))
	 */
	private void setMass (double Mass) throws IllegalArgumentException
	
	{
		if (isValidMass(Mass)) {
		mass = Mass;}
		else {throw new IllegalArgumentException();}
	}
	
	/**
	 * Checks whether the given mass is a valid mass.
	 * @param mass
	 * @return True if the mass is greater than 0, false if the mass isn't.
	 * 		   | (mass > 0)
	 */
	private boolean isValidMass(double mass)
	{
		return (mass>0);
	}
	
	/**
	 * The mass of the ship (in kg).
	 */
	private double mass;
	
	/**
	 * The ship fires a bullet.
	 * 
	 * @effect A new bullet is added to the world of this ship with a new position, new velocity and this ship as its source.
	 * 			| getWorld().addObject(bullet)
	 */
	
	public void fireBullet() {
		
		if(this.getWorld()==null) {return;}
		
		double bulletRadius = 3;
		double bulletSpeed = 250;
		double bulletXPos = this.getX() + Math.cos(this.getDirection())*(this.getRadius()+ bulletRadius);
		double bulletYPos = this.getY() + Math.sin(this.getDirection())*(this.getRadius()+ bulletRadius);
		double bulletXVelocity = Math.cos(this.getDirection())* bulletSpeed;
		double bulletYVelocity = Math.sin(this.getDirection())* bulletSpeed;
		Bullet bullet = new Bullet(bulletXPos, bulletYPos, bulletXVelocity, bulletYVelocity, this);
		this.getWorld().addObject(bullet);
	}
	
	/**
	 * Resolves the collision between this ship and another space object.
	 * @param spaceobject
	 * 				The space object in collision with this ship.
	 * 
	 * @effect If the other space object is a ship, the ships will bounce off each other.
	 * 			| if (spaceobject instanceof Ship)
	 * 			| then resolveCollision(spaceobject)
	 * @effect If the other space object is an Asteroid, this ship dies and the asteroid remains unaffected.
	 * 			| if (spaceobject instanceof Asteroid)
	 * 			| then Die()
	 * @effect If the other space object is a Bullet, the resolve method will be called on this bullet.
	 * 			| if (spaceobject instanceof Bullet)
	 * 			| then spaceobject.resolve(this)
	 */
	@Override
	public void resolve(SpaceObject spaceobject)
	{
		if(spaceobject instanceof Ship) {
			resolveCollision(spaceobject);}
		
		if(spaceobject instanceof Asteroid) {
			this.Die();}
		
		if(spaceobject instanceof Bullet) {
			spaceobject.resolve(this);}
		
	}
	
	
}
