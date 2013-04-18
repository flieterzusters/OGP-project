package asteroids.model;

import asteroids.Util;
import asteroids.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Set;
import java.util.HashSet;

/**
 * A class of virtual game worlds with certain width and height.
 * A game world contains ships, bullets and asteroids.
 * 
 * 
 * 
 * @author Tom De Ferm
 * @version 0.1
 *
 */

public class World {
	
	/**
	 * Creates a new world with given width and height.
	 * 
	 * @param width 	The initial width of the world, expressed in km.
	 * 
	 * @param height 	The initial height of the world, expressed in km.
	 * 
	 * @post The new width of this new world is equal to the given width.
	 * 				| new this.getWorldWidth().equals(width)
	 * 
	 * @post The new height of this new world is equal to the given height.
	 * 				| new this.getWorldHeigt().equals(height)
	 */
	
	@Raw
	public World (double width, double height) throws IllegalArgumentException {
		
		setWidth(width);
		setHeight(height);
		this.Objects = new HashSet<SpaceObject>();
		
	}

	/**
	 * Returns the current width of the game world (in km).
	 * @return The current width of the game world (in km).
	 */
	@Basic @Immutable
	public double getWorldWidth() {
		return width;
	}
	
	/**
	 * Returns the current height of the game world (in km).
	 * @return The current height of the game world (in km).
	 */
	@Basic @Immutable
	public double getWorldHeight() {
		return height;
	}
	
	private void setWidth(double newWidth) throws IllegalArgumentException {
		if(!isValidCoordinate(newWidth)) {throw new IllegalArgumentException();}
		else {width = newWidth;}
	}
	
	private void setHeight(double newHeight) throws IllegalArgumentException {
		if(!isValidCoordinate(newHeight)) {throw new IllegalArgumentException();}
		else {height = newHeight;}
	}
	
	// TO DO: Testen of <0 geen probleem vormt.
	private boolean isValidCoordinate(double coordinate) {
		
		if(Double.isNaN(coordinate) || !Util.fuzzyLessThanOrEqualTo(coordinate, Double.MAX_VALUE)) return false;
		else return true;
	}
	
	/**
	 * The world's current width.
	 */
	private double width;
	
	/**
	 * The world's current height.
	 */
	private double height;
	
	/**
	 * Returns a set collecting all the ships in this world.
	 * 
	 * @return Each ship in this set is attached to this world, and vice versa.
	 */
	@Basic
	public Set<Ship> getShips() {
		
		Set<Ship> Ships = new HashSet<Ship>();
		for(SpaceObject spaceObject: Objects)
			if(Ship.class.isInstance(spaceObject))
				Ships.add((Ship)spaceObject);
		return Ships;
	}
	
	/**
	 * Returns a set collecting all the asteroids in this world.
	 * 
	 * @return Each asteroid in this set is attached to this world, and vice versa.
	 */
	@Basic
	public Set<Asteroid> getAsteroids() {
		
		Set<Asteroid> Asteroids = new HashSet<Asteroid>();
		for(SpaceObject spaceObject: Objects)
			if(Asteroid.class.isInstance(spaceObject))
				Asteroids.add((Asteroid)spaceObject);
		return Asteroids;
	}
	
	/**
	 * Returns a set collecting all the bullets in this world.
	 * 
	 * @return Each bullet in this set is attached to this world, and vice versa.
	 */
	@Basic
	public Set<Bullet> getBullets() {
		
		Set<Bullet> Bullets = new HashSet<Bullet>();
		for (SpaceObject spaceObject: Objects)
			if(Bullet.class.isInstance(spaceObject))
				Bullets.add((Bullet)spaceObject);
		return Bullets;
			
	}
	
	/**
	 * Returns a set collecting all the space objects in this world.
	 * 
	 * @return Each space object in this set is attached to this world, and vice versa.
	 */
	@Basic
	public Set<SpaceObject> getObjects() {
		return Objects;
	}
	
	/**
	 * Adds a space object to this world.
	 * 
	 * @param ship 		The space object that is added to this world.
	 */
	
	public void addObject (SpaceObject object) throws IllegalObjectException {
		
		if(object == null) {throw new NullPointerException();}
		
		if(isValidSpaceObject(object))
		{
		object.setWorld(this);
		Objects.add(object);
		}
		else {throw new IllegalObjectException ("This object can't be added to this world.");}
	}
	
	/**
	 * Removes a space object from this world.
	 * 
	 * @param object		The object that is removed from this world.
	 */
	public void removeObject (SpaceObject object) {
		
		if(object == null) {throw new NullPointerException();}
		Objects.remove(object);
	}
	
	private boolean isValidSpaceObject(SpaceObject object) {
		
		boolean validSpaceObject = true;
		for(SpaceObject spaceobject : Objects)
		{
			if(object instanceof Bullet)
			{
				if (object.overlap(spaceobject) && ((Bullet) object).getSource() != spaceobject)
				{
					spaceobject.Die();
					object.Die();
					validSpaceObject = false;
				}
			}
			
			else if(object.overlap(spaceobject))
			{
				validSpaceObject=false;
			}
		}
		
		return validSpaceObject;
	}
	
	
	/**0
	 * A collection of all the space objects in the world.
	 */
	private Set<SpaceObject> Objects;
	
	/**
	 * 
	 * @param dt
	 * @param collisionListener
	 */
	public void evolve(double dt) throws IllegalArgumentException {
		
		if(!isValidEvolveArgument(dt)) { throw new IllegalArgumentException("The given argument is either not a number or is negative.");}
		
		double timeToFirstCollision;
		SpaceObject collisionObject1;
		SpaceObject collisionObject2;
		
		while(true) {
			
			timeToFirstCollision = Double.POSITIVE_INFINITY;
			collisionObject1=null;
			collisionObject2=null;
			
			for (SpaceObject spaceobject1 : Objects)
			{
				for (SpaceObject spaceobject2 : Objects)
				{
					if(!spaceobject1.equals(spaceobject2))
					{
						double timeToCollision = spaceobject1.getTimeToCollision(spaceobject2);
			
						if(timeToCollision<timeToFirstCollision)
						{
							timeToFirstCollision = timeToCollision;
							collisionObject1 = spaceobject1;
							collisionObject2 = spaceobject2;
						}
					}
				}
		
				if(spaceobject1.getTimeToBoundaryCollision() < timeToFirstCollision)
				{
					timeToFirstCollision = spaceobject1.getTimeToBoundaryCollision();
					collisionObject1 = spaceobject1;
					collisionObject2 = null;
				}
			}
				
			if(timeToFirstCollision > dt)
				break;
			
				
				
			for(SpaceObject spaceobject : Objects)
			{
				spaceobject.move(timeToFirstCollision);
			}
				
			if(timeToFirstCollision == Double.POSITIVE_INFINITY)
				break;
	
			
			else if (collisionObject2 == null)
			{
				collisionObject1.resolveBoundaryCollision();
			}
				
			else
			{
				collisionObject1.resolve(collisionObject2);
			}
				
			dt = dt - timeToFirstCollision;
		}
			
		for(SpaceObject spaceobject : Objects)
		{
			spaceobject.move(timeToFirstCollision);
		}
		
		
	}
	
			
	private boolean isValidEvolveArgument(double argument) {	
		
		if (Double.isNaN(argument) || argument <= 0) {
			return false;}
	 	else {return true;}
	}
	
	
}
	
