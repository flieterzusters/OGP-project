package asteroids.model;

import asteroids.Util;
import asteroids.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import java.util.*;

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
	 * Returns the number of bullets this ship has fired in this world.
	 * @param ship
	 * 		  The ship firing the bullets.
	 * @return amount of bullets fired by this ship.
	 */

	public int getNrOfBulletsFrom(Ship ship) {
		
		int bullets = 0;
		for (SpaceObject spaceobject: Objects) {
			if (spaceobject instanceof Bullet && ((Bullet) spaceobject).getSource() == ship) 
			{
				bullets++;
			}		
			}

		return bullets;
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
		
		assert (object.getWorld() == this) && (object.fitsInWorld(this)) && (object !=null) && (containsSpaceObject(object) == false);
		object.setWorld(this);
		Objects.add(object);
		addModifiedSpaceObject(object);
		
	}
	
	/**
	 * Removes a space object from this world.
	 * 
	 * @param object		The object that is removed from this world.
	 */
	public void removeObject (SpaceObject object) {
		
		if(object == null) {throw new NullPointerException();}
		Objects.remove(object);
		modifiedObjects.remove(object);
		object.removeWorld();
		removeCollisions(object);
	}
	
	/**
	 * A collection of all the space objects in the world.
	 */
	private Set<SpaceObject> Objects = new HashSet<SpaceObject>();
	
	/**
	 * A simple method to move all the space objects in this world during a given time dt.
	 * If the space object is a ship, it will also start executing programs if it has any.
	 * @param dt
	 * 		  The time the objects are moved.	  
	 */
	public void moveAllSpaceObjects(double dt)
	{
		Set<SpaceObject> asteroidAndBullet = new HashSet<SpaceObject>();
		asteroidAndBullet.addAll(getAsteroids());
		asteroidAndBullet.addAll(getBullets());
		
		for(SpaceObject object : asteroidAndBullet) {
			object.move(dt);
		}
		
		this.programtime = this.programtime + dt;
		if (this.programtime >= 0.2) {
			nrOfRuns = (int) Math.floor(this.programtime / 0.2);
			this.programtime = this.programtime - nrOfRuns*0.2; 
		}
		
		for (Ship ship : getShips()) {
			
			ship.move(dt);
			addModifiedSpaceObject(ship);
			ship.execute(nrOfRuns);
			}
	}
		
	
	/**
	 * A sort of remainder of time from the previous round of executions.
	 */
	private double programtime = 0;
	
	/**
	 * Number of times a ship has to run a certain program.
	 */
	private int nrOfRuns = 0;
	
	
	
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
	private boolean isTerminated = false;
	
	
	
	/**
	 * An arraylist which holds collision objects.
	 */
	private final ArrayList<Collision> Collisions = new ArrayList<Collision>();
	
	/**
	 * A set which holds all the modified objects.
	 */
	private final Set<SpaceObject> modifiedObjects = new HashSet<SpaceObject>();
	
	/**
	 * Evolves this world with a given time interval dt.
	 * @param dt The time over which this world has to evolve.
	 * 
	 * 
	 * @throws IllegalArgumentException
	 * 		   Throws an illegalargumentexception if dt isn't a valid evolve argument.
	 */
	public void evolve (double dt) {
		
		if(!isValidEvolveArgument(dt)) {throw new IllegalArgumentException();}
		
		double timeToFirstCollision;
		
		while(true)
		{
			timeToFirstCollision=Double.POSITIVE_INFINITY;
			
			prepareCollisions();
			if(!Collisions.isEmpty()) {timeToFirstCollision = Collisions.get(0).getCollisionTime();}
			if(timeToFirstCollision > dt) {break;}
			moveAllSpaceObjects(timeToFirstCollision);
			changeCollisionsTime(timeToFirstCollision);
			if(timeToFirstCollision == Double.POSITIVE_INFINITY) {break;}
			else {
				this.Collisions.get(0).resolve();
			}
			dt = dt - timeToFirstCollision;
		}
		moveAllSpaceObjects(dt);
		changeCollisionsTime(dt);
	}
			
	/**
	 * Prepares the collisions before evolving.		
	 */
	private void prepareCollisions()
	{
		for (SpaceObject spaceobject : modifiedObjects) {
			removeCollisions(spaceobject);
		}
		for (SpaceObject spaceobject1 : modifiedObjects) {
			for (SpaceObject spaceobject2 : Objects) {
				if (! spaceobject1.equals(spaceobject2) ) {
					double collisiontime = spaceobject1.getTimeToCollision(spaceobject2);
					if (collisiontime != Double.POSITIVE_INFINITY) {
						addCollision(new Collision(collisiontime, spaceobject1, spaceobject2));}
				}
			}
			double timeToBoundaryCollision = spaceobject1.getTimeToBoundaryCollision();
			if (timeToBoundaryCollision != Double.POSITIVE_INFINITY) 
				addCollision(new Collision(timeToBoundaryCollision, spaceobject1));
			}

		
		modifiedObjects.clear();
	}
	
	/**
	 * Removes collisions from the Collisions arraylist.
	 * @param spaceobject
	 */
	private void removeCollisions(SpaceObject spaceobject)
	{
		for(int i = 0; i<Collisions.size();i++) {
				if (Collisions.get(i).attachedSpaceObject(spaceobject)) {
					this.Collisions.remove(i);
					i--;}
					
		}
	}
	
	/**
	 * Adds a space object to the set of modified space objects.
	 * @param spaceobject
	 */
	public void addModifiedSpaceObject(SpaceObject spaceobject)
	{
		if(!modifiedObjects.contains(spaceobject) && spaceobject.getWorld() == this)
			this.modifiedObjects.add(spaceobject);
	}
	
	/**
	 * Adds a collision to the arraylist of collisions.
	 * @param collision
	 */
	private void addCollision(Collision collision) 
	{
		if(Collisions.isEmpty()) {Collisions.add(0, collision);}
		else
		{
			for(int i=0; i<Collisions.size(); i++)
			{
				if(Collisions.get(i).getCollisionTime() > collision.getCollisionTime())
				{
					Collisions.add(i, collision);
					i = Collisions.size();
				}
				if(i== Collisions.size()-1)
					{
					Collisions.add(collision);
					i = Collisions.size();
					}


			}
		}
	}
		
	/**
	 * Changes the collision time by using the method evolve in the class Collision.
	 * @param dt
	 */
		private void changeCollisionsTime(double dt) 
		{
			for(Collision collision: Collisions)
			{
				collision.evolve(dt);
			}
		}
	
			
			
			
			
			
			
		
		
		
				

		
	}
