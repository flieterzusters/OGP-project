
package main;

import asteroids.IShip;
import asteroids.Util;

import be.kuleuven.cs.som.annotate.*;



/**
 * A class of ships that have a position, velocity, radius, and direction.
 * Ships can move, apply thrust and turn. The class also provides functionality for collision detection.
 * 
 * @invar A ship's speed limit can never exceed the speed of light (c=300000 km/s).
 * 		  | getSpeedLimit =< 300000
 * 
 * @invar A ship's speed can never exceed it's speed limit. 
 * 	      | getVelocity =< getSpeedLimit
 * 
 * @invar The value of angle will always be between 0 and 2*Pi (inclusive)
 * 		  | 0 <= angle <= 2*Pi
 * @author Jasper, Tom
 * @version 0.1
 */

public class Ship implements IShip {
	
	/**
	 * Creates a new Ship with default parameters.
	 * The position will be initialised to the origin, the ship's speed will be zero, the radius will be 10km  and the angle will be zero.
	 * @effect Creates a new Ship with default parameters.
	 * 		  | ship(0, 0, 0, 0, 10, 0).
	 */
	
	public Ship() {
		this(0, 0, 0, 0, 10, 0);
		
	}
	
	/**
	 * Creates a new Ship with user defined parameters.
	 * @param x	The initial x-coordinate of the ship, expressed in km.
	 * @param y	The initial y-coordinate of the ship, expressed in km.
	 * @param xVelocity The initial velocity in the x-direction the ship will have, expressed in km/s.
	 * @param yVelocity The initial velocity in the y-direction the ship will have, expressed in km/s.
	 * @param radius The ship's radius, expressed in km.
	 * @param angle The direction the ship will face,expressed in radians (0 is to the right).
	 */
	
	public Ship(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) throws IllegalArgumentException {
		
		setXPos(x);
		setYPos(y);
		setXVelocity(xVelocity);				 
		setYVelocity(yVelocity);	
		this.speedLimit = 300000;							
		if (!Util.fuzzyLessThanOrEqualTo(this.getVelocity(), this.getSpeedLimit() )) this.makeVelocityValid(xVelocity, yVelocity);
		if (isValidRadius(radius)) this.radius = radius; 
		else {
				throw new IllegalArgumentException ("The provided radius is either not a number or is too small.");
		}
		setAngle(angle);				
		}
	
	/**
	 * Returns the current x-coordinate of the ship in km.
	 * @return The ship's current x-coordinate in km.
	 */
	@Basic
	public double getX() {
		
		return xPos;
	}
	
	/**
	 * Returns the current y-coordinate of the ship in km.
	 * @return The ship's current x-coordinate in km.
	 */
	@Basic
	public double getY() {
		
		return yPos;
	}
	
	
	/**
	 * Moves the ship for a duration dt according to its current state.
	 * The ship will be moved according to its current position and its current velocity during the specified duration <code>dt</code>.
	 * The specified duration <code>dt</code> can never be less than zero.
	 * @param dt Specifies the duration of the move command.
	 * @post The new xPos (yPos) is equal to the old xPos (yPos) changed by the xVelocity (yVelocity) multiplied by the given duration. 
	 * 		| new this.getX() = this.getX() + getXVelocity*dt 
	 * 		| new this.getY() = this.getY() + getYVelocity*dt
	 * @throws IllegalArgumentException		 
	 */
	
	public void move(double dt) throws IllegalArgumentException {	
		
		if (!isValidMoveArgument(dt)) throw new IllegalArgumentException("The given argument is either not a number or is negative.");
		xPos = xPos + this.getXVelocity()*dt;
		yPos = yPos + this.getYVelocity()*dt;
		
	}
	
	
	private boolean isValidMoveArgument(double argument) {	
		
		if (Double.isNaN(argument) || Util.fuzzyLessThanOrEqualTo(argument, 0)) {
			return false;
		} else return true;
		
	}	
	
	private void setXPos(double newXPos) throws IllegalArgumentException {
		if(!isValidCoordinate(newXPos)) throw new IllegalArgumentException();
		else xPos = newXPos;	
	}
	
	private void setYPos(double newYPos) throws IllegalArgumentException {
		if(!isValidCoordinate(newYPos)) throw new IllegalArgumentException();
		else yPos = newYPos;
	}
		
	private boolean isValidCoordinate(double coordinate) {
		
		if(Double.isNaN(coordinate)) return false;
		else return true;
		
	}
	
	/**
	 * The ship's current position along the x-axis.
	 */
	private double xPos;
	
	/**
	 * The ship's current position along the y-axis.
	 */
	private double yPos;
	
	
	
	/**
	 * Returns the ship's current velocity in the x-direction, expressed in km/s.
	 * @return The ship's current velocity in the x-direction(km/s).
	 */
	@Basic
	public double getXVelocity() {
		
		return xVelocity;
	}
	/**
	 * Returns the ship's current velocity in the y-direction, expressed in km/s.
	 * @return The ship's current velocity in the y-direction(km/s).
	 */
	@Basic
	public double getYVelocity() {
		
		return yVelocity;
	}
	
	/**
	 * Returns the ship's total velocity (in km/s).
	 * @return The ship's total velocity.
	 */
	public double getVelocity() {
		
		return Math.sqrt(Math.pow(getXVelocity(),2)+Math.pow(getYVelocity(),2));
	}
	
	/**
	 * Returns the ship's maximum speed.
	 * @return The ships maximum speed.
	 */
	@Basic
	public double getSpeedLimit() {
		
		return speedLimit;
		
	}
	
	/**
	 * Changes the velocity of the ship by an amount <code>da</code>.
	 * This method changes the velocity of the ship according to its current velocity and the direction it's facing.
	 * The change in total velocity will be equal to the specified amount <code>da</code>.
	 * @param da The total velocity to be added to the ship's current velocity.
	 * 
	 * @post If the given amount of change in velocity is equal to or less than zero, no change to the ship's velocity is made.
	 * 	     | if(da =< 0) then 
	 *       |       new getXVelocity = getXVelocity
	 *       |	     new getYVelocity = getYVelocity
	 * @post The new velocity vector is equal to the old velocity vector changed by the given amount da in the ship's current direction.
	 *       | new getXVelocity = getXVelocity + da*cos(angle)
	 *       | new getYVelocity = getYVelocity + da*sin(angle)
	 * @efect If the new total velocity would exceed the ship's upper speed limit, 
	 * 		   the new total velocity is changed to be equal to the speed of light without changing the direction of the velocity.
	 * 		 
	 */
	public void thrust(double da) {
		
		if (da <= 0) return;
		
		setXVelocity(getXVelocity() + da*Math.cos(angle));
		setYVelocity(getYVelocity() + da*Math.sin(angle));
		
		
		if (!Util.fuzzyLessThanOrEqualTo(this.getVelocity(), speedLimit)) {//TODO Dit stuk code werkt niet voor negatieve waarden van xVelocity en yVelocity, ik zal het morgen aanpassen (Jasper)
			
			this.makeVelocityValid(xVelocity, yVelocity);			
		}
		
	}
	
	/**
	 * @param xVelocity
	 */
	@Raw
	private void setXVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}
	
	/**
	 * @param yVelocity
	 */
	@Raw
	private void setYVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	/**
	 * This method reduces a ships velocity to make it comply with it's speed limit without changing the direction of movement.
	 * @param xVelocity The x-velocity the ship currently possesses.
	 * @param yVelocity The y-velocity the ship currently possesses.
	 * @post The new getXVelocity (getYVelocity) satisfies the upper speed limit of the ship and the direction of movement is the same as before.
	 * 		 | new getVelocity() <= this.speedLimit
	 * 		 | new atan2(getYVelocity(),getXVelocity()) = atan2(getYvelocity(), getXVelocity)	
	 */
	@Raw @Model
	private void makeVelocityValid(double xVelocity, double yVelocity) {
		
		double angleOfMovement = Math.atan2(yVelocity,xVelocity);
		
		setXVelocity(speedLimit*Math.cos(angleOfMovement));
		setYVelocity(speedLimit*Math.sin(angleOfMovement));
		
	}
	
	/**
	 * The ship's current velocity in the x-direction.
	 */
	private double xVelocity;
	
	/**
	 * The ship's current velocity in the x-direction.
	 */
	private double yVelocity; 
	
	/**
	 * The ship's maximum speed, cannot exceed the speed of light.
	 */
	private double speedLimit;
	
	
	
	/**
	 * Returns the ship's radius, expressed in km.
	 * @return The ship's radius (in km).
	 */
	@Basic @Immutable
	public double getRadius() {
		
		return radius;
	}
	
	
	private double getSumOfRadii(Ship ship2) {
		return this.getRadius() + ship2.getRadius();
	}

	
	
	
	private boolean isValidRadius(double radius) {
		if(Double.isNaN(radius) || !Util.fuzzyLessThanOrEqualTo(minimumRadius, radius)) return false;
		else return true;
	}
	
	/**
	 * The minimum radius a ship must have. This cannot be smaller then 10.
	 */
	private static double minimumRadius = 10;
	
	/**
	 * The ship's radius (in km)
	 */
	private final double radius;
	
	
	
	/**
	 * Returns the direction the ship is currently facing.
	 * @return The direction the ship is facing.
	 */
	//TODO spec+implement
	@Basic
	public double getDirection() {
		
		return angle;
	}
	
	/**
	 * Turns the ship over a given angle in radians. <code>angle</code> can be negative 
	 * @param angle The amount of radians to be added to the ships direction. 
	 * @pre the given angle must be a double, Double.isNaN must return false. Double.POSITIVE_INFINITY and Double.NEGATIVE_INFINITY are not valid arguments.
	 * @post The given angle is added to the ship's direction
	 * 		| new this.angle = this.angle + angle
	 */
	//TODO spec+implement
	public void turn(double angle) {
		
		setAngle(this.angle + angle);
		
	}
	
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
	 * Returns the distance between this ship and the given ship. This distance is the shortest distance the ships would have to move
	 * to be adjacent to each other. When the given ship is the same as this ship, the distance is zero, when the ships overlap, the distance is negative.
	 * @param Ship2 The ship to which the distance should be calculated.
	 * @return The distance between the 2 ships. The distance is negative if and only if the ship's overlap.
	 */
	//TODO Defensieve specificatie + implementatie
	public double getDistanceBetween(Ship ship2) {
		
		double centerDistanceSquared = Math.pow(ship2.getX()-this.getX() , 2) + Math.pow(ship2.getY()-this.getY(), 2);
		return Math.sqrt(centerDistanceSquared) - getSumOfRadii(ship2);
		
	}

	
	
	/**
	 * Returns true if and only if this ship overlaps with the given ship. A ship always overlaps with itself. Two adjacent ships are considered to overlap.
	 * @param Ship2 The ship to check for overlap with.
	 * @return True if this ship overlaps with the given ship, False if the ships do not overlap.
	 */
	//TODO defensieve specificatie + implementatie
	public boolean overlap(Ship ship2) {
		
		double distance = this.getDistanceBetween(ship2);
		double sumOfRadius = getSumOfRadii(ship2);
		
		if(Util.fuzzyLessThanOrEqualTo(distance, sumOfRadius))	{
			return true;
		}
		
		else return false;
		
	}
	
	
	/**
	 * Returns the time left (in seconds) before this ship collides for the first time with the given ship or Double.POSITIVE_INTINITY if they never collide.
	 * A ship can never collide with itself.
	 * @param Ship2 The ship to which the collision time should be calculated.
	 * @return The time, in seconds, to collision with the given ship.
	 */
	public double getTimeToCollision(Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("this method requires a non-null object.");
		else {
			double sigmaSquared = Math.pow(getSumOfRadii(ship2), 2);
			double deltaVSquared = Math.pow(ship2.getXVelocity() - this.getXVelocity(), 2) 
								 + Math.pow(ship2.getYVelocity() - this.getYVelocity(), 2);	
			double deltaRSquared = Math.pow(ship2.getX() - this.getX(), 2) + Math.pow(ship2.getY() - this.getY(), 2);
			double deltaVDeltaR = (ship2.getXVelocity() - this.getXVelocity())*(ship2.getX() - this.getX()) 
								+ (ship2.getYVelocity() - this.getYVelocity())*(ship2.getY() - this.getY());
			double d  = Math.pow(deltaVDeltaR, 2) - deltaVSquared*(deltaRSquared - sigmaSquared);
			double deltaT;
			if(Util.fuzzyEquals(deltaVDeltaR, 0) || deltaVDeltaR > 0 ) deltaT = Double.POSITIVE_INFINITY;
			else if (Util.fuzzyLessThanOrEqualTo(d, 0)) deltaT = Double.POSITIVE_INFINITY;
			else deltaT = -(deltaVDeltaR + Math.sqrt(d))/(deltaVSquared);
			return deltaT;
		}
	}
	
	/**
	 * 
	 * @param ship2
	 * @return
	 */
	public double[] getCollisionPosition(Ship ship2) {
		if (ship2 == null) throw new IllegalArgumentException("this method requires a non-null object.");
		else {
			double deltaT = getTimeToCollision(ship2);
			if(deltaT == Double.POSITIVE_INFINITY) return null;
			else {
				double[] collisionPos = new double[2];
				collisionPos[0] = this.getX() + deltaT*this.getXVelocity();
				collisionPos [1] = this.getY() + deltaT*this.getYVelocity();
				return collisionPos;
		}
		
		}
	}
	
	
	
	
	
	
	
	
}
