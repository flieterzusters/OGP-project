package asteroids.model;

import asteroids.Util;

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
	 * @param x	
	 * 		  The initial x-coordinate of the asteroid, expressed in km.
	 * @param y	
	 * 		  The initial y-coordinate of the asteroid, expressed in km.
	 * @param xVelocity 
	 * 		  The initial velocity in the x-direction the asteroid will have, expressed in km/s.
	 * @param yVelocity 
	 * 		  The initial velocity in the y-direction the asteroid will have, expressed in km/s.
	 * @param radius 
	 * 		  The asteroid's radius, expressed in km.
	 * 
	 * @effect This new asteroid is initialized as a SpaceObject with a given position, speed and radius.
	 *        | super(x, y, xVelocity, yVelocity, radius)
	 *        
	 * @post The given random is now set as a random of this asteroid.
	 * 		  | new.getRandom() == random
	 * 
	 */
	
	public Asteroid(Vector position, Vector velocity, double radius, Random random)
	{
		super(position, velocity, radius);
		setRandom(random);
	
	}
	
	/**
	 * Creates a new Asteroid with a given position, velocity and radius.
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * @effect |this(x, y, xVelocity, yVelocity, radius, null)
	 */
	public Asteroid(Vector position, Vector velocity, double radius)
	{
		this(position, velocity, radius, null);
	}
	
	
	/**
	 * @return The current mass of this asteroid.
	 * 			| (4/3)*Math.PI*Math.pow(getRadius(), 3)*getMassDensity()
	 */
	@Override @Immutable
	public double getMass() {
		
		double Mass = (4/3)*Math.PI*Math.pow(getRadius(), 3)*getMassDensity();
		return Mass;
	}
	
	/**
	 * @return The mass density of this asteroid.
	 */
	@Basic @Immutable
	public double getMassDensity() {
		return massDensity;
	}
	
	/**
	 * The mass density of each Asteroid is fixed (in kg/km³).
	 */
	private static final double massDensity = 2.65*Math.pow(10,12);
	
	/**
	 * Terminates this object. The object will be removed from its current world.
	 * 		| getWorld().removeObject(this)
	 * 		| removeWorld()
	 * If the radius of this object is greater than or equal to 30, two smaller child asteroids will spawn.
	 * 		| if (getRadius() >= 30)
	 * 		| let
	 * 		| 		childradius = (this.getRadius())/(2)
	 * 		|		xPosChild1 = this.getX()+childradius*Math.cos(randomDouble)
	 * 		|		yPosChild1 = this.getY()+childradius*Math.sin(randomDouble)
	 * 		|		xPosChild2 = this.getX()-childradius*Math.cos(randomDouble)
	 * 		|		yPosChild2 = this.getY()-childradius*Math.sin(randomDouble)
	 * 		|		childXVelocity = childVelocity*Math.cos(randomDouble)
	 * 		|		childYVelocity = childVelocity*Math.sin(randomDouble)
	 * 		| in
	 * 		|		getWorld().addObject(new Asteroid(xPosChild1, yPosChild1, childXVelocity, childYVelocity, childradius, getRandom()))
	 * 		|		getWorld().addObject(new Asteroid(xPosChild2, yPosChild2, -childXVelocity, -childYVelocity, childradius, getRandom()))
	 */
	@Override
	public void terminate() {
		
		World world = getWorld();
		
		if(world == null || getRadius() < 30)
		{
			super.terminate();
			return;
		}
		
		double childradius = (getRadius())/(2);
		
		// Create Child 1
		
		double direction = Math.PI*(random.nextDouble());
		
		double xPosChild1 = this.getPosition().getX()+(childradius+Util.EPSILON)*Math.cos(direction);
		double yPosChild1 = this.getPosition().getY()+(childradius+Util.EPSILON)*Math.sin(direction);
		Vector position = new Vector(xPosChild1, yPosChild1);
		double childVelocity = (1.5)*(getVelocity().getNorm());
		double childXVelocity = childVelocity*Math.cos(direction);
		double childYVelocity = childVelocity*Math.sin(direction);
		Vector velocity = new Vector(childXVelocity, childYVelocity);
		SpaceObject Child1 = new Asteroid(position, velocity, childradius);
		
		// Create Child 2
		
		double xPosChild2 = this.getPosition().getX()-(childradius)*Math.cos(direction);
		double yPosChild2 = this.getPosition().getY()-(childradius)*Math.sin(direction);
		Vector position2 = new Vector(xPosChild2, yPosChild2);
		Vector velocity2 = new Vector(-1*childXVelocity, -1*childYVelocity);
		SpaceObject Child2 = new Asteroid(position2, velocity2, childradius);
			
		world.removeObject(this);
		world.addObject(Child1);
		world.addObject(Child2);
		
	}
	
	/**
	 * Returns a random object.
	 * @return A random object.
	 */
	@Basic		
	public Random getRandom()
	{
		return random;
	}
	
	/**
	 * Sets a random object as the random object of the asteroid.
	 * @param random
	 * 			A random object.
	 */
	public void setRandom(Random random) {
		
		if(random == null) {this.random = new Random();}
		else{
		this.random = random;}
	}
	
	/**
	 * A random object, which will send a child asteroid in a random starting direction.
	 */
	private Random random;
	
	/**
	 * Resolves the collision between this asteroid and another space object.
	 * @param spaceobject
	 * 		The space object in collision with this asteroid.
	 * 
	 * @effect If the other space object is an asteroid, the asteroids will bounce off each other.
	 * 			|if(spaceobject instanceof Asteroid)
	 * 			|	then resolveCollision(spaceobject)
	 * @effect If the other space object is a bullet, both objects die.
	 *  		|if(spaceobject instanceof Bullet)
	 *  		|then
	 *  		|	spaceobject.Die()
	 *  		|	Die()
	 * @effect If the other spaceobject is a ship, the ship will die and the asteroid remains unaffected.
	 *  		| if (spaceobject instanceof Ship)
	 *  		|then spaceobject.Die()
	 */
	@Override
	public void resolve(SpaceObject spaceobject)
	{
		if(spaceobject instanceof Asteroid)
		{
			resolveCollision(spaceobject);
		}
		
		if(spaceobject instanceof Bullet)
		{
			spaceobject.resolve(this);	
		}
		
		if(spaceobject instanceof Ship)
		{
			spaceobject.terminate();
		}
		
		
	}
	

}
