package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import java.util.Random;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class of asteroids that have a position, velocity, radius and mass.
 * 
 * @invar An asteroid's speed limit can never exceed the speed of light (c=300000 km/s).
 * 		  | getAsteroidSpeedLimit =< 300000
 * 
 * @invar An asteroids's speed can never exceed it's speed limit. 
 * 	      | getAsteroidVelocity =< getAsteroidSpeedLimit
 * 
 * @author Tom De Ferm
 * @version 0.1
 */

public class Asteroid extends SpaceObject {
	
	/**
	 * Creates a new Asteroid with user defined parameters.
	 * @param x	The initial x-coordinate of the asteroid, expressed in km.
	 * @param y	The initial y-coordinate of the asteroid, expressed in km.
	 * @param xAsteroidVelocity The initial velocity in the x-direction the asteroid will have, expressed in km/s.
	 * @param yAsteroidVelocity The initial velocity in the y-direction the asteroid will have, expressed in km/s.
	 * @param radius The asteroid's radius, expressed in km.
	 * @param asteroidMass The asteroid's mass, expressed in kg.
	 */
	
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) 
	{
		super(x, y, xVelocity, yVelocity, radius);
		
	}
	
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius, Random random)
	{
		super(x, y, xVelocity, yVelocity, radius);
		setRandom(random);
		
	}
	
	
	@Override
	public double getMass() {
		
		double Mass = (4/3)*Math.PI*massDensity*Math.pow(getRadius(), 3);
		return Mass;
	}
	
	
	/**
	 * The mass density of each Asteroid is fixed (in kg/km³).
	 */
	private static final double massDensity = 2.65*Math.pow(10, 12);
	
	public void Die() {
		
		if(this.getWorld() != null) {
			this.getWorld().removeObject(this);
			this.removeWorld();
		}
		
		if (getRandom() != null && this.getRadius() >= 30)
		{
			double randomDouble = getRandom().nextDouble();
			double childradius = (this.getRadius())/(2);
			double childVelocity = (1.5)*(this.getVelocity());
			double childXVelocity = childVelocity*Math.cos(randomDouble);
			double childYVelocity = childVelocity*Math.sin(randomDouble);
			double xPosChild1 = this.getX()+childradius*Math.cos(randomDouble);
			double yPosChild1 = this.getY()+childradius*Math.sin(randomDouble);
			double xPosChild2 = this.getX()-childradius*Math.cos(randomDouble);
			double yPosChild2 = this.getY()-childradius*Math.sin(randomDouble);
			
			SpaceObject Child1 = new Asteroid(xPosChild1, yPosChild1, childXVelocity, childYVelocity, childradius, getRandom());
			SpaceObject Child2 = new Asteroid(xPosChild2, yPosChild2, -childXVelocity, -childYVelocity, childradius, getRandom());
			
			Child1.setWorld(this.getWorld());
			Child2.setWorld(this.getWorld());
			this.getWorld().addObject(Child1);
			this.getWorld().addObject(Child2);
		}
		
		else {
			super.Die();
		}
			
	}
	
	public Random getRandom()
	{
		return random;
	}
	
	public void setRandom(Random random) {
		
		this.random = random;
	}
	
	/**
	 * A random object, which will send a child asteroid in a random starting direction.
	 */
	private Random random;
	

}
