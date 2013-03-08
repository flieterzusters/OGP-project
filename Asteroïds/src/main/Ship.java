package main;


import asteroids.IShip;

/**
 * @author Jasper
 * @version 0.1
 */
public class Ship implements IShip {
	
	
	public static double getRadius() {
		return 0;
	}

	private static double radius;
	
	
	/**
	 * Creates a new ship with default parameters.
	 */
	public Ship(){
	
		
	}
	/**
	 * Creates a new ship with user defined parameters.
	 * @param x  The x-coördinate the ship should get.
	 * @param y  The y-coördinate the ship should get.
	 * @param xVelocity  The velocity in the x-direction to be given to the ship.
	 * @param yVelocity  The velocity in the y-direction the ship should get.
	 * @param radius  The radius of the ship.
	 * @param angle  The direction the ship should face, in radians.
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double angle ) {
		
	}
	
	public double getX() {
		return 0;
	}
	public double getY() {
		return 0;
	}
	public void move(double dt) {
		
	}
	
	private double xPos;
	private double yPos;
	
	
	public double getXVelocity() {
		return 0;
	}
	public double getYVelocity() {
		return 0;
	}
	public double getVelocity() {
		return 0;
	}
	public void thrust(double amount) {
					
	}
	
	private double xVelocity;
	private double yVelocity;
	
	
	public double getDirection() {
		return 0;
	}
	public void turn(double angle) {

	}
	
	private double angle;
	
	
	

	
}
