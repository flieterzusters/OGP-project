package asteroids.model;


import asteroids.Util.*;

import be.kuleuven.cs.som.annotate.*;



/**
 * A class of ships that have a position, velocity, radius, direction and mass.
 * Ships can move, apply thrust, turn and fire bullets. The functionality for collision detection is located in the class SpaceObject.
 *
 * @invar The value of angle will always be between 0 and 2*Pi (inclusive).
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
	 * 
	 * @param position
	 * 		  The initial position of the ship.
	 * @param velocity
	 * 		  The initial velocity of the ship.
	 * @param radius
	 * 		  The initial radius of the ship.
	 * @param angle 
	 * 		  The direction the ship will face, expressed in radians (0 is to the right, Pi is to the left).
	 * @param mass 
	 * 		  The total mass of the ship (in kg).
	 * 
	 * @effect This new ship is initialized as a SpaceObject with a given position, speed and radius.
	 *        | super(position, velocity, radius)
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
	
	public Ship(Vector position, Vector velocity, double radius, double angle, double mass) {
		
		super(position, velocity, radius);							
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
	 * Moves the ship for a duration dt according to its current state.
	 * The ship will be moved according to its current position and its current velocity during the specified duration <code>dt</code>.
	 * The specified duration <code>dt</code> can never be less than zero.
	 * @param dt Specifies the duration of the move command.
	 * @post The new xPos (yPos) is equal to the old xPos (yPos) changed by the xVelocity (yVelocity) multiplied by the given duration. 
	 * 		| new Position.getX() = Position.getX() + Velocity.getXVelocity*dt 
	 * 		| new Position.getY() = Position.getY() + Velocity.getYVelocity*dt	 
	 */
	@Override
	public void move(double dt){	
		
		if (dt>0){
			
		double xpos = (getPosition().getX() + getVelocity().getX()*dt);
		double ypos = (getPosition().getY() + getVelocity().getY()*dt);
		setPosition(new Vector(xpos,ypos));
		thrust(dt);
		}
	}
	
	
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
	 *       |       new getVelocity().getX() = getVelocity().getX()
	 *       |	     new getVelocity().getY() = getVelocity().getY()
	 * @post The new velocity vector is equal to the old velocity vector changed by the acceleration in the ship's current direction.
	 *       | new getVelocity().getX() = getVelocity().getX() + getAcceleration*cos(angle)
	 *       | new getVelocity().getY() = getVelocity().getY() + getAcceleration*sin(angle)
	 * 		 
	 */
	public void thrust(double dt) {
		
		if (thrusterActive) {
		
		double xVelocity = (this.getVelocity().getX() + (dt*this.getAcceleration()*Math.cos(this.getDirection())));
		double yVelocity = (this.getVelocity().getY() + (dt*this.getAcceleration()*Math.cos(this.getDirection())));
		setVelocity(new Vector(xVelocity, yVelocity));
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
	public void setAngle(double angle) {	
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
		
		if(this.getWorld()==null || this.getWorld().getNrOfBulletsFrom(this) >= 3) {return;}
		
		double bulletRadius = 3;
		double bulletSpeed = 250;
		Vector addPosition = new Vector(Math.cos(getDirection())*(getRadius()+ bulletRadius),Math.sin(getDirection())*(getRadius()+ bulletRadius));
		Vector position = getPosition().add(addPosition);
		Vector addVelocity = new Vector(Math.cos(getDirection())*bulletSpeed,Math.sin(getDirection())*bulletSpeed);
		Vector velocity = getVelocity().add(addVelocity);
		Bullet bullet = new Bullet(position, velocity, bulletRadius,this);
		this.getWorld().addObject(bullet);
		for (SpaceObject secondObject : getWorld().getObjects())
		{
			if(bullet.overlap(secondObject) && bullet != secondObject) {
				bullet.resolve(secondObject);
				return;
			}
			
		}
		
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
	 * 			| then terminate()
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
			this.terminate();}
		
		if(spaceobject instanceof Bullet) {
			spaceobject.resolve(this);}
		
	}
	
	/**
	 * @return Returns the program with which this ship is associated.
	 */
	@Basic
	public Program getProgram(){
		return this.program;
	}
	
	/**
	 * Sets a certain program for this ship.
	 * @param program
	 * 		  The program this ship will be associated with.
	 */
	@Basic
	public void setProgram(Program program) {
		this.program = program;
		program.setShip(this);
	}
	
	/**
	 * The program associated with this ship.
	 */
	private Program program = null;
	
	/**
	 * Executes a program one or multiple times depending on the parameter.
	 * Does nothing if this ship has no program available.
	 * @param value
	 */
	public void execute(int value) {
		
		Program program = getProgram();
		if(program != null) {
			program.execute(value);}
	}
	
}
