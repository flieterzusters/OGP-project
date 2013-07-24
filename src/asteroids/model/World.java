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
 * @invar 		|isValidCoordinate(getWorldWidth())
 * @invar		|isValidCoordinate(getWorldHeight())
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
	 * Checks whether this world contains a certain space object.
	 */
	
	public boolean containsSpaceObject(SpaceObject spaceobject)
	{
		return Objects.contains(spaceobject);
	}
	
	/**
	 * Checks whether the space object is valid, to be added to this game world.
	 * If valid, it adds the space object to this world.
	 * 
	 * @param object 		The space object to be checked and added to this world.
	 */
	
	public void addObject (SpaceObject object) {
		
		if(object == null || !object.fitsInWorld(this)) {throw new IllegalArgumentException();}
		
		object.setWorld(this);
		boolean validSpaceObject = true;
		
		for(SpaceObject spaceobject : getObjects())
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
		if(validSpaceObject)
		{
			Objects.add(object);
		}
		
		}
	}
	
	/**
	 * Removes a space object from this world.
	 * 
	 * @param object		The object that is removed from this world.
	 */
	public void removeObject (SpaceObject object) {
		
		if(object == null) {throw new NullPointerException();}
		Objects.remove(object);
		object.removeWorld();
	}
	
	
	/**
	 * A collection of all the space objects in the world.
	 */
	private Set<SpaceObject> Objects;
	
	/**
	 * A simple method to move all the spaceobjects in this world during a given time dt.
	 * @param dt
	 * 		  The time the objects are moved.	  
	 */
	public void moveAllSpaceObjects(double dt)
	{
		for (SpaceObject object : getObjects())
		{object.move(dt);}
	}
	
	/**
	 * Evolves the world for an amount of time dt.
	 * @param dt
	 * 			The amount of the time the world has to evolve.
	 * 
	 * @effect 	|while(true)
	 * 			|	timeToFirstCollision = Double.POSITIVE_INFINITY
	 * 			|	collisionObject1=null
	 * 			|	collisionObject2=null
	 * 			|	
	 * 			|	for each (SpaceObject spaceobject1 : Objects)
	 * 			|		for each (SpaceObject spaceobject2 : Objects)
	 * 			|			
	 * 			|			if(!spaceobject1.equals(spaceobject2))
	 * 			|				then double timeToCollision = spaceobject1.getTimeToCollision(spaceobject2)
	 * 			|				
	 * 			|				if(timeToCollision<timeToFirstCollision)
	 * 			|						then timeToFirstCollision = timeToCollision
	 * 			|							 collisionObject1 = spaceobject1
	 * 			|						     collisionObject2 = spaceobject2
	 * 			|		
	 * 			|		if(spaceobject1.getTimeToBoundaryCollision() < timeToFirstCollision)
	 * 			|			then timeToFirstCollision = spaceobject1.getTimeToBoundaryCollision()
	 * 			|				 collisionObject1 = spaceobject1
	 * 			|				 collisionObject2 = null
	 * 			|
	 * 			|	if(timeToFirstCollision > dt) then break;
	 * 			|
	 * 			| 	for each (SpaceObject spaceobject : Objects)
	 * 			|		spaceobject.move(timeToFirstCollision)
	 * 			|
	 * 			| 	if(timeToFirstCollision == Double.POSITIVE_INFINITY) then break;
	 * 			|
	 * 			|	else if (collisionObject2 == null)
	 * 			|		then collisionObject1.resolveBoundaryCollision()
	 * 			|
	 * 			|	else
	 * 			|		collisionObject1.resolve(collisionObject2)
	 * 			|
	 * 			|	dt = dt - timeToFirstCollision
	 * 			|
	 * 			| for each (SpaceObject spaceobject : Objects)
	 * 			|	spaceobject.move(timeToFirstCollision)
	 * 
	 * @throws IllegalArgumentException
	 * 				Only if dt isn't a valid evolve argument. (see method: isValidEvolveArgument)
	 * 
	 * 
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
				
			if(timeToFirstCollision > dt || timeToFirstCollision == Double.POSITIVE_INFINITY)
				break;
			
			moveAllSpaceObjects(timeToFirstCollision);
	
			if (collisionObject2 == null)
			{
				collisionObject1.resolveBoundaryCollision();
			}
				
			else
			{
				collisionObject1.resolve(collisionObject2);
			}
				
			dt = dt - timeToFirstCollision;
		}
			
		moveAllSpaceObjects(timeToFirstCollision);
		
	}
	
	/**
	 * Checks whether the argument is a valid argument for the method "evolve".		
	 * @param argument
	 * 			The argument to be checked.
	 * @return True if the argument is a number and greater than zero, else false will be returned.
	 */
	private boolean isValidEvolveArgument(double argument) {	
		
		if (Double.isNaN(argument) || argument <= 0) {
			return false;}
	 	else {return true;}
	}
	
	/**
	 * @return The boolean field 'isTerminated()'.
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	/**
	 * Terminates a world.
	 * 
	 * @post    This world is terminated.
	 *        | new.isTerminated()
	 * @post    Each of the space objects of this world no longer has
	 *          a world.
	 *        | for each space object in getObjects():
	 *        |	  (! (new space object).hasSpaceObject())
	 */
	public void terminate(){
		if (!isTerminated()) {
			for (SpaceObject spaceObject : this.getObjects())
				spaceObject.removeWorld();
			this.isTerminated = true;
		}
	}
	
	/**
	 * Indicates whether the world is terminated or not.
	 */
	private boolean isTerminated;

	
}
	
