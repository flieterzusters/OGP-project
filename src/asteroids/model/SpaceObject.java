package asteroids.model;
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of space objects: ships, asteroids and bullets.
 * 
 * @invar The radius of each space object must be valid.
 * 		  | isValidRadius(getRadius())
 * 
 * @author Tom
 * @version 0.1
 */
public abstract class SpaceObject {
	
	/**
	 * Initializes a space object with a given position, velocity and radius.
	 * @param x
	 * 		  The initial x-coordinate of the space object, expressed in km.
	 * @param y
	 * 		  The initial y-coordinate of the space object, expressed in km.
	 * @param xVelocity
	 * 		  The initial velocity in the x-direction the space object will have, expressed in km/s.
	 * @param yVelocity
	 * 		  The initial velocity in the y-direction the space object will have, expressed in km/s.
	 * @param radius
	 * 		  The space object's radius, expressed in km.
	 * 
	 * @post The given x-coordinate is set as the new x-coordinate of the space object.
	 * 		 |new.getX().equals(x)
	 * @post The given y-coordinate is set as the new y-coordinate of the space object.
	 * 		 |new.getY().equals(y)
	 * @post The given xVelocity is set as the new xVelocity of the space object.
	 * 		 |new.getXVelocity().equals(xVelocity)
	 * @post The given yVelocity is set as the new yVelocity of the space object.
	 * 		 |new.getYVelocity().equals(yVelocity)
	 * @post The given radius is set as the new radius of the space object.
	 * 		 |new.getRadius().equals(radius)
	 * 
	 * @throws IllegalArgumentException
	 * 		   If the given radius isn't valid.
	 * 		   |isValidRadius(getRadius())
	 *
	 */
	public SpaceObject(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		
		this.Position = new Position(x,y);
		this.Velocity = new Velocity(xVelocity, yVelocity);							
		if (isValidRadius(radius)) this.radius = radius; 
		else {
				throw new IllegalArgumentException ("The provided radius is either not a number or is too small.");
		
			 }
		}
	
	/**
	 * @return The space object's current position in space.
	 */
	@Basic
	public Position getPosition() {
		
		return this.Position;
	}
	
	/**
	 * Sets a new position in space for this space object.
	 * @param newPos
	 * 		  The new position for this space object.
	 */
	public void setPosition(Position newPos) {
		this.Position = newPos;
		
	}
	
	/**
	 * The current position of the space object.
	 */
	protected Position Position;
	
	
	
	/**
	 * Moves the space object for a duration dt according to its current state.
	 * The space object will be moved according to its current position and its current velocity during the specified duration <code>dt</code>.
	 * The specified duration <code>dt</code> can never be less than zero.
	 * If the space object is a ship, the ship will thrust.
	 * @param dt Specifies the duration of the move command.
	 * @post The new xPos (yPos) is equal to the old xPos (yPos) changed by the xVelocity (yVelocity) multiplied by the given duration. 
	 * 		| new Position.getX() = Position.getX() + Velocity.getXVelocity*dt 
	 * 		| new Position.getY() = Position.getY() + Velocity.getYVelocity*dt	 
	 */
	
	public void move(double dt){	
		
		if (dt>0){
			
		double xpos = (getPosition().getX() + getVelocity().getXVelocity()*dt);
		double ypos = (getPosition().getY() + getVelocity().getYVelocity()*dt);
		Position.setPosition(new Position(xpos,ypos));
		
		if (this instanceof Ship) {
			Ship ship = (Ship) this;
			ship.thrust(dt);
		}}
		
		
		
	}
	
	
	/**
	 * @return The space object's current velocity in space(km/s).
	 */
	@Basic
	public Velocity getVelocity() {
		
		return this.Velocity;
	}
	
	
	
	/**
	 * Sets the given velocity as the new Velocity of the space object.
	 * @param newVelocity
	 * 		  The new velocity of the ship.
	 */
	@Raw
	public void setVelocity(Velocity newVelocity) {
		this.Velocity = newVelocity;
	}
	
	
	
	/**
	 * The space object's current velocity in space.
	 */
	protected Velocity Velocity;
	
	/**
	 * @return The world containing this object, null if this space object has no world.
	 */
	@Basic @Raw
	public World getWorld() {
		return world;
	}
	
	/**
	 * Puts this object in a particular world if this world is suitable.
	 * @param world 
	 * 		  The world in which this object will be placed.
	 */
	@Basic @Raw
	public void setWorld(World world) {
		if (this.canHaveAsWorld(world))
		{this.world = world;}
		
	}
	
	/**
	 * Checks whether this space object has a world.
	 */
	public boolean hasWorld()
	{
		return (this.getWorld() != null);
	}
	
	/**
	 * Check whether this space object can have the given world as its world.
	 * 
	 * @param  world
	 *         The world to check.
	 * @return  If this space object is not yet terminated, return true if the given world is not null and not yet
	 *          terminated.
	 *        | if (! isTerminated())
	 *        |   then result == (world != null) && (! world.isTerminated())
	 * @return  If this space object is terminated, return true if the given world is null.
	 *        | if (! this.isTerminated())
	 *        |   then result == (world == null)
	 */
	@Raw
	public boolean canHaveAsWorld(World world){
		if (this.isTerminated()) {return (world == null);}
		return (world != null) && (!world.isTerminated());
	}
	
	/**
	 * Removes this object from his world.
	 * @post This object has no world anymore.
	 * @post His former world doesn't have this particular space object anymore.
	 */
	@Basic @Raw
	public void removeWorld () {
		if(this.hasWorld())
		{
			World world = this.getWorld();
			world.removeObject(this);
			this.setWorld(null);
			
		}
	}
	
	/**
	 * @param world
	 * 		  The world we check to see either this spaceobject fits in it or not.
	 */
	public boolean fitsInWorld (World world) {
		if (world == null) {return false;}
		double xpos = getPosition().getX();
		double ypos = getPosition().getY();
		double radius = getRadius();
		
		if (xpos-radius < 0 || xpos+radius > world.getWorldWidth() || ypos-radius < 0 || ypos+radius > world.getWorldHeight())
		{
			return false;
		}
		else {return true;}
	}
	
	/**
	 * The world in which this object is placed. Null if this object doesn't have a world.
	 */
	protected World world;
	
	/**
	 * @return The space object's radius (in km).
	 */
	@Basic @Immutable
	public double getRadius() {
		
		return radius;
	}
	
	/**
	 * @param secondObject
	 * @return The sum of the radii of this space object and secondObject. 
	 */
	private double getSumOfRadii(SpaceObject secondObject) {
		return this.getRadius() + secondObject.getRadius();
	}

	/**
	 * Checks whether the radius of this space object is valid.
	 * @param radius
	 * @return True if the radius is a number and greater than the minimumRadius.
	 */
	private boolean isValidRadius(double radius) {
		if(radius > 0 && radius < Double.POSITIVE_INFINITY) return true;
		else return false;
	}
	
	
	/**
	 * The space object's radius (in km).
	 */
	protected double radius;
	
	/**
	 * @return Total mass of the space object.
	 */
	@Basic @Immutable
	public abstract double getMass();
	
	/**
	 * The current mass of the space object.
	 */
	protected double mass;
	
	/**
	 * Enumeration of all possible states of a space object.
	 */
	protected static enum State{
		ACTIVE,TERMINATED;
	}
	
	/**
	 * @return	Returns the state of the Space Object. The state is either Active or Terminated.
	 */
	@Raw @Basic
	protected State getState()
	{
		return this.state;
	}
	
	/**
	 * Sets the state of the space object in a given state.
	 */
	@Basic
	protected void setState(State state)
	{
		if(state == null) {throw new NullPointerException();}
		this.state = state;
	}
	
	/**
	 *  the state of a space object.
	 */
	protected State state = State.ACTIVE;
	
	/**
	 * Returns the distance between this space object and the given space object. This distance is the shortest distance the space objects would have to move
	 * to be adjacent to each other. When the given space object is the same as this space object, the distance is zero, when the space objects overlap, the distance is negative.
	 * @param secondObject 
	 * 				The space object to which the distance should be calculated.
	 * 
	 * @return The distance between the 2 space objects. The distance is negative if and only if the space objects overlap.
	 * 			|double centerDistanceSquared = Math.pow(secondObject.getX()-this.getX() , 2) + Math.pow(secondObject.getY()-this.getY(), 2)
	 * 			|return Math.sqrt(centerDistanceSquared)
	 */
	public double getDistanceBetween(SpaceObject secondObject) {
		
		double centerDistanceSquared = Math.pow(secondObject.getPosition().getX()-this.getPosition().getX() , 2) + Math.pow(secondObject.getPosition().getY()-this.getPosition().getY(), 2);
		return Math.sqrt(centerDistanceSquared);
		
	}

	
	
	/**
	 * Returns true if and only if this space object overlaps with the given space object. A space object always overlaps with itself. Two adjacent space objects are considered to overlap.
	 * @param secondObject 
	 * 			The space object to check for overlap with.
	 * 
	 * @return True if this space object overlaps with the given space object, False if the space objects do not overlap.
	 * 			|double distance = this.getDistanceBetween(secondObject)
	 * 			|double sumOfRadius = getSumOfRadii(secondObject)
	 * 			|
	 * 			|if(distance < sumOfRadius) then return true
	 * 			|else 	return false
	 */
	public boolean overlap(SpaceObject secondObject) {
		
		double distance = this.getDistanceBetween(secondObject);
		double sumOfRadius = getSumOfRadii(secondObject);
		
		if(distance < sumOfRadius)	{
			return true;
		}
		
		else return false;
		
	}
	
	
	/**
	 * Returns the time left (in seconds) before this space object collides for the first time with the given space object or Double.POSITIVE_INTINITY if they never collide.
	 * A space object can never collide with itself.
	 * @param secondObject 
	 * 				The space object to which the collision time should be calculated.
	 * 
	 * @return The time, in seconds, to collision with the given space object.
	 * 		   Double.POSITIVE_INFINITY if they don't collide.
	 * 			|double sigmaSquared = Math.pow(getSumOfRadii(secondObject), 2)
	 * 			|double deltaVSquared = Math.pow(secondObject.getXVelocity() - this.getXVelocity(), 2) + Math.pow(secondObject.getYVelocity() - this.getYVelocity(), 2)
	 * 			|double deltaRSquared = Math.pow(secondObject.getX() - this.getX(), 2) + Math.pow(secondObject.getY() - this.getY(), 2)
	 * 			|double deltaVDeltaR = (secondObject.getXVelocity() - this.getXVelocity())*(secondObject.getX() - this.getX()) + (secondObject.getYVelocity() - this.getYVelocity())*(secondObject.getY() - this.getY())
	 * 			|double d  = Math.pow(deltaVDeltaR, 2) - deltaVSquared*(deltaRSquared - sigmaSquared)
	 * 			|double deltaT
	 * 			|
	 * 			|if(Util.fuzzyEquals(deltaVDeltaR, 0) || deltaVDeltaR > 0 ) then deltaT = Double.POSITIVE_INFINITY
	 * 			|else if(Util.fuzzyLessThanOrEqualTo(d, 0)) then deltaT = Double.POSITIVE_INFINITY
	 * 			|else 	deltaT = -(deltaVDeltaR + Math.sqrt(d))/(deltaVSquared)
	 * 			|return deltaT
	 */
	
	public double getTimeToCollision(SpaceObject secondObject) {
		if (secondObject == null) throw new NullPointerException();
		
		else {
			double sigmaSquared = Math.pow(getSumOfRadii(secondObject), 2);
			double deltaVSquared = Math.pow(secondObject.getVelocity().getXVelocity() - this.getVelocity().getXVelocity(), 2) 
								 + Math.pow(secondObject.getVelocity().getYVelocity() - this.getVelocity().getYVelocity(), 2);	
			double deltaRSquared = Math.pow(secondObject.getPosition().getX() - this.getPosition().getX(), 2) + Math.pow(secondObject.getPosition().getY() - this.getPosition().getY(), 2);
			double deltaVDeltaR = (secondObject.getVelocity().getXVelocity() - this.getVelocity().getXVelocity())*(secondObject.getPosition().getX() - this.getPosition().getX()) 
								+ (secondObject.getVelocity().getYVelocity() - this.getVelocity().getYVelocity())*(secondObject.getPosition().getY() - this.getPosition().getY());
			double d  = Math.pow(deltaVDeltaR, 2) - deltaVSquared*(deltaRSquared - sigmaSquared);
			double deltaT;
			if(Util.fuzzyEquals(deltaVDeltaR, 0) || deltaVDeltaR > 0 ) deltaT = Double.POSITIVE_INFINITY;
			else if (Util.fuzzyLessThanOrEqualTo(d, 0)) deltaT = Double.POSITIVE_INFINITY;
			else deltaT = -(deltaVDeltaR + Math.sqrt(d))/(deltaVSquared);
			return deltaT;
		}
	}
	
	/**
	 * Returns the position of the collision with the given space object or Double.POSITIVE_INFINITY if they never collide.
	 * A space object can never collide with itself.
	 * @param secondObject 
	 * 			The space object to which the collision position should be calculated.
	 * 
	 * @return The position of the collision with the given space object or Double.POSITIVE_INFINITY if they never collide.
	 * 			|if (deltaT == Double.POSITIVE_INFINITY)
	 * 			|	then return null
	 * 			|
	 * 			|double[] collisionPos = new double[2]
	 * 			|double theta = Math.atan2(secondObject.getY()-this.getY(), secondObject.getX()-this.getX())
	 * 			|if((secondObject.getX()-this.getX())<0)
	 * 			|	theta+=Math.PI*2
	 * 			|
	 * 			|collisionPos[0] = this.getX() + this.radius * Math.cos(theta)
	 * 			|collisionPos [1] = this.getY() + this.radius * Math.sin(theta)
	 * 			|return collisionPos
	 * 
	 * @throws NullPointerException
	 * 				If the second object is null.
	 */
	
	public double[] getCollisionPosition(SpaceObject secondObject) {
		if (secondObject == null) throw new NullPointerException();
			
		double deltaT = getTimeToCollision(secondObject);
		if(deltaT == Double.POSITIVE_INFINITY) return null;
			
		double[] collisionPos = new double[2];
		double theta = Math.atan2(secondObject.getPosition().getY()-this.getPosition().getY(), secondObject.getPosition().getX()-this.getPosition().getX());
		if ((secondObject.getPosition().getX()-this.getPosition().getX())<0) {
			theta+=Math.PI*2;
		}
		
		collisionPos[0] = this.getPosition().getX() + this.radius * Math.cos(theta);
		collisionPos [1] = this.getPosition().getY() + this.radius * Math.sin(theta);
		return collisionPos;
	}
		

		
	
	
	/**
	 * Resolves the collision between two ships or two asteroids by bouncing them off each other.
	 * @param secondObject
	 * 				The second object with which this object collides.
	 * 
	 * @effect The speed of this object will be updated to resolve the collision.
	 * 			|double sigma = getSumOfRadii(secondObject)
	 * 			|double deltaRx = secondObject.getX()-this.getX()
	 * 			|double deltaRy = secondObject.getY()-this.getY()
	 * 			|double deltaVx = secondObject.getXVelocity()- this.getXVelocity()
	 * 			|double deltaVy = secondObject.getYVelocity()-this.getYVelocity()
	 * 			|double deltaVR = (deltaVx*deltaRx)+(deltaVy*deltaRy)
	 * 			|
	 * 			|double J = (2*this.getMass()*secondObject.getMass()*deltaVR)/(sigma*(this.getMass()+secondObject.getMass()))
	 * 			|double Jx = (J*deltaRx)/(sigma)
	 * 			|double Jy = (J*deltaRy)/(sigma)
	 * 			|
	 * 			|(new this).getXVelocity = this.getXVelocity + Jx/this.getMass()
	 * 			|(new this).getYVelocity = this.getYVelocity + Jy/this.getMass()
	 * 
	 * @throws NullPointerException
	 * 				If the second space object is null.
	 * 					|(secondObject == null)
	 */
	
	public void resolveCollision (SpaceObject secondObject) {
		
		if (secondObject == null) { throw new NullPointerException(); }
		
		assert(!secondObject.equals(this));
		
		double sigma = getSumOfRadii(secondObject);
		double deltaRx = secondObject.getPosition().getX()-this.getPosition().getX();
		double deltaRy = secondObject.getPosition().getY()-this.getPosition().getY();
		double deltaVx = secondObject.getVelocity().getXVelocity()- this.getVelocity().getXVelocity();
		double deltaVy = secondObject.getVelocity().getYVelocity()-this.getVelocity().getYVelocity();
		double deltaVR = (deltaVx*deltaRx)+(deltaVy*deltaRy);
	
		double J = (2*this.getMass()*secondObject.getMass()*deltaVR)/(sigma*(this.getMass()+secondObject.getMass()));
		double Jx = (J*deltaRx)/(sigma);
		double Jy = (J*deltaRy)/(sigma);
		double newxvelocity = this.getVelocity().getXVelocity()+(Jx/this.getMass());
		double newyvelocity = this.getVelocity().getYVelocity()+(Jy/this.getMass());
		getVelocity().setVelocity(newxvelocity,newyvelocity);
		double newxvelocity2 = secondObject.getVelocity().getXVelocity()-(Jx/secondObject.getMass());
		double newyvelocity2 = secondObject.getVelocity().getYVelocity()-(Jy/secondObject.getMass());
		secondObject.getVelocity().setVelocity(newxvelocity2,newyvelocity2);
			
	}
		
		
		
	
	/**
	 * Terminates this object. The object will be removed from its current world.
	 * 
	 * @effect If this object is in a world, it will be removed from that world.
	 * 			| getWorld().removeObject(this)
	 * 			| removeWorld()
	 */
	protected void Die() {
		
		if(this.getWorld() != null) {
		this.getWorld().removeObject(this);
		this.removeWorld();
		}
		else {throw new NullPointerException();}
		
			
	}
	
	/**
	 * Returns the time (in seconds) when the object will collide with a boundary of his world.
	 * @return Positive_infinity if the object doesn't collide with a boundary.
	 * 			| if (getXvelocity() == 0 && getYVelocity() == 0)
	 * 			| then result = Double.POSITIVE_INFINITY
	 * @return The time (in seconds) when the object will collide with a boundary of his world.
	 * 			| double worldwidth = getWorld().getWorldWidth()
	 * 			| double worldheight = getWorld().getWorldHeight()
	 * 			| double timeToBoundaryX
	 * 			| double timeToBoundaryY
	 * 			| 
	 * 			| if(Util.fuzzyLessThanOrEqualTo(this.getYVelocity(),0))
	 * 			| 	then timeToBoundaryY = Math.abs((getY()- getRadius())/(getYVelocity()))
	 * 			| 
	 * 			| else if (!Util.fuzzyLessThanOrEqualTo(this.getYVelocity(),0))
	 * 			| 	then timeToBoundaryY = Math.abs((worldheight - getY() - getRadius())/(getYVelocity()))
	 * 			| 
	 * 			| else	
	 * 			| 	timeToBoundaryY = Double.POSITIVE_INFINITY;
	 *			|
	 *			| if(Util.fuzzyLessThanOrEqualTo(this.getXVelocity(),0))
	 *			| 	then timeToBoundaryX = Math.abs((getX()- getRadius())/(getXVelocity()))
	 *			|
	 *			| else if (!Util.fuzzyLessThanOrEqualTo(this.getXVelocity(),0))
	 *			| 	timeToBoundaryX = Math.abs((worldwidth - getX() - getRadius())/(getXVelocity()))
	 *			|
	 *			| else
	 *			| 	timeToBoundaryX = Double.POSITIVE_INFINITY
	 *			|
	 *			| result == Math.min(timeToBoundaryX, timeToBoundaryY)
	 */
	public double getTimeToBoundaryCollision() {
		
		double worldwidth = getWorld().getWorldWidth();
		double worldheight = getWorld().getWorldHeight();
		
		double timeToBoundaryX;
		double timeToBoundaryY;

		if(Util.fuzzyLessThanOrEqualTo(this.getVelocity().getYVelocity(),0)) {
			
			timeToBoundaryY = Math.abs((getPosition().getY()- getRadius())/(getVelocity().getYVelocity()));}
		
		else if (!Util.fuzzyLessThanOrEqualTo(this.getVelocity().getYVelocity(),0)) {
			
			timeToBoundaryY = Math.abs((worldheight - getPosition().getY() - getRadius())/(getVelocity().getYVelocity()));
		}
		
		else {
			timeToBoundaryY = Double.POSITIVE_INFINITY;
		}
		
		if(Util.fuzzyLessThanOrEqualTo(this.getVelocity().getXVelocity(),0)) {
			
			timeToBoundaryX = Math.abs((getPosition().getX()- getRadius())/(getVelocity().getXVelocity()));}
		
		else if (!Util.fuzzyLessThanOrEqualTo(this.getVelocity().getXVelocity(),0)) {
			
			timeToBoundaryX = Math.abs((worldwidth - getPosition().getX() - getRadius())/(getVelocity().getXVelocity()));
		}
		
		else {
			timeToBoundaryX = Double.POSITIVE_INFINITY;
		}
		
		
		
		
		return Math.min(timeToBoundaryX, timeToBoundaryY);
		
	}
	
	/**
	 * Resolves the collision between this space object and a boundary.
	 * 
	 * @effect If the space object hits a horizontal boundary, invert the y-velocity.
	 * 		   If the space object hits a vertical boundary, invert the x-velocity.
	 * 			| if(Util.fuzzyEquals(getX(),getRadius()) || Util.fuzzyEquals((getX() + getRadius()), getWorld().getWorldWidth()))
	 * 			| 	then this.setXVelocity(-(this.getXVelocity()))
	 * 			|
	 * 			| else
	 * 			| 	setYVelocity(-(this.getYVelocity()))
	 * 			|
	 * 			| makeVelocityValid(getXVelocity(),getYVelocity())
	 * 
	 * @throws NullPointerException
	 * 				This space object doesn't have a world.
	 */
	public void resolveBoundaryCollision()
	{
		if(getWorld()==null) throw new NullPointerException();
		
		if(Util.fuzzyEquals(getPosition().getX(),getRadius()) || 
				Util.fuzzyEquals((getPosition().getX() + getRadius()), getWorld().getWorldWidth())) {
			
			getVelocity().setVelocity(-(this.getVelocity().getXVelocity()),this.getVelocity().getYVelocity());
		}
		
		else {
			getVelocity().setVelocity(this.getVelocity().getXVelocity(),-(this.getVelocity().getYVelocity()));}
		
		
	}
	
	/**
	 * Resolves the collision between this space object and another space object.
	 * @param collisionobject
	 * 				The space object in collision with this space object.
	 */
	public abstract void resolve(SpaceObject collisionobject);
	
	/**
	 * This method terminates a space object.
	 * 
	 */
	public void terminate()
	{
		this.removeWorld();
		this.setState(State.TERMINATED);
	}
	
	/**
	 * Check whether this Space Object is terminated.
	 * 
	 * @return True if  the state of this object is terminated.
	 * 			
	 */
	public boolean isTerminated(){
		return (this.getState() == State.TERMINATED);
	}
	
}
		
		
		
		
		
		

			
		
	
	
		
	
	
	
		


