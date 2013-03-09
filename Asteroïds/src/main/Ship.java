/**
 * 
 */
package main;

import asteroids.IShip;
import be.kuleuven.cs.som.annotate.*;

/**
 * @author Jasper
 * @version 0.1
 */
/**
 * @author Jasper
 *
 */
public class Ship implements IShip {
	
	/**
	 * Creates a new Ship with default parameters.
	 */
	//TODO Specificatie + Implementatie
	public Ship(){
		
	}
	
	/**
	 * Creates a new Ship with user defined parameters.
	 * @param x	The initial x-coordinate of the ship, expressed in km.
	 * @param y	The initial y-coordinate of the ship, expressed in km.
	 * @param xVelocity The initial velocity in the x-direction the ship will have, expressed in km/s.
	 * @param yVelocity The initial velocity in the y-direction the ship will have, expressed in km/s.
	 * @param radius The ship's radius, expressed in m.
	 * @param angle The direction the ship will face,expressed in radians (0 is to the right).
	 */
	//TODO Afwerken Specificatie + Implementatie
	public Ship(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) {
		
	}
	
	/**
	 * Returns the current x-coordinate of the ship in km.
	 * @return The ship's current x-coordinate in km.
	 */
	@Basic
	//TODO Afwerken Specificatie + Implementatie
	public double getX() {
		return 0;
	}
	
	/**
	 * Returns the current y-coordinate of the ship in km.
	 * @return The ship's current x-coordinate in km.
	 */
	@Basic
	//TODO Afwerken Specificatie + Implementatie
	public double getY() {
		return 0;
	}
	
	
	/**
	 * Moves the ship for a duration dt according to its current state.
	 * The ship will be moved according to its current position and its current velocity during the specified duration <code>dt</code>.
	 * The specified duration <code>dt</code> can never be less than zero
	 * @param dt Specifies the duration of the move command.
	 */
	//TODO spec + implement
	public void move(double dt) {
		
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
	//TODO Afwerken Specificatie + Implementatie
	public double getXVelocity() {
		return 0;
	}
	/**
	 * Returns the ship's current velocity in the y-direction, expressed in km/s.
	 * @return The ship's current velocity in the y-direction(km/s).
	 */
	@Basic
	//TODO Afwerken Specificatie + Implementatie
	public double getYVelocity() {
		return 0;
	}
	
	/**
	 * Changes the velocity of the ship by an amount <code>da</code>.
	 * This method changes the velocity of the ship according to its current velocity and the direction it's facing.
	 * The change in total velocity will be equal to the specified amount <code>da</code>
	 * @param da The total velocity to be added to the ship's current velocity
	 */
	public void thrust(double da) {
		
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
	 * Returns the ship's radius, expressed in km.
	 * @return The ship's radius (in km).
	 */
	@Basic @Immutable
	//TODO afwerken spec + implement
	public double getRadius() {
		return 0;
	}
	
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
		return 0;
	}
	
	/**
	 * Turns the ship over a given angle in radians. <code>angle</code> can be negative 
	 * @param angle The amount of radians to be added to the ships direction. 
	 */
	//TODO spec+implement
	public void turn(double angle) {
		
	}
		
	/**
	 * The direction the ship is currently facing
	 */
	public double angle;
	
	
	
	
	
	
	
}
