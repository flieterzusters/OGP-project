package asteroids.model;
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of space objects: ships, asteroids and bullets.
 * 
 * @invar The radius of each space object must be valid.
 * 		  | isValidRadius(getRadius())
 * 
 * @invar The velocity of each space object must be valid.
 * 		  | isValidVelocity(getVelocity())
 * 
 * @author Tom
 * @version 0.1
 */
public abstract class SpaceObject {
	
	/**
	 * Initializes a space object with a given position, velocity and radius.
	 * 
	 * @param position
	 * 		  The initial position of the space object.
	 * @param velocity
	 * 		  The initial velocity of the space object.
	 * @param radius
	 * 		  The initial radius of the space object.
	 * 
	 * @post The given position is set as the new position of the space object.
	 * 		 |new.getPosition().equals(position)
	 * @post The given velocity is set as the new velocity of the space object.
	 * 		 |new.getVelocity().equals(velocity)
	 * @post The given radius is set as the new radius of the space object.
	 * 		 |new.getRadius() == radius
	 * 
	 * @throws IllegalArgumentException
	 * 		   If the given radius isn't valid.
	 * 		   |!isValidRadius(getRadius())
	 *
	 */
	public SpaceObject(Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		
		setPosition(position);
		setVelocity(velocity);							
		if (isValidRadius(radius)) {this.radius = radius;} 
		else {
				throw new IllegalArgumentException ();
			 }
		}
	
	/**
	 * Returns the position of the space object.
	 * @return position
	 */
	@Basic
	public Vector getPosition() {
		
		return this.Position;
	}
	
	/**
	 * Sets a new position in space for this space object.
	 * @param newPos
	 * 		  The new position for this space object.
	 * 
	 * @post The given position is set as the new position of the space object.
	 * 		 |new.getPosition().equals(position)
	 */
	public void setPosition(Vector newPos) {
		if(newPos == null) {throw new NullPointerException();}
		this.Position = newPos;
		
	}
	
	/**
	 * The current position of the space object.
	 */
	protected Vector Position;
	
	/**
	 * Returns the velocity of the space object.
	 * @return velocity
	 */
	@Basic
	public Vector getVelocity() {
		
		return this.Velocity;
	}
	
	
	
	/**
	 * Sets the given velocity as the new velocity of the space object.
	 * @param newVelocity
	 * 		  The new velocity of the ship.
	 * 
	 * @post The given velocity is set as the new position of the space object.
	 * 		 |new.getVelocity().equals(velocity)
	 */
	public void setVelocity(Vector newVelocity) {
		if(!isValidVelocity(newVelocity)) {
			double factor = (SPEED_OF_LIGHT)/(newVelocity.getNorm());
			this.Velocity = new Vector(newVelocity.getX()*factor,newVelocity.getY()*factor);
		}
		this.Velocity = newVelocity;
	}
	
	/**
	 * Return true if the given velocity has a valid value.
	 * The given velocity may not exceed the speed of light.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the given velocity does not exceed the speed of light.
	 * 			| result == velocity <= SPEED_OF_LIGHT
	 */
	private boolean isValidVelocity(Vector velocity){
		return velocity.getNorm() < SPEED_OF_LIGHT;
	}
	
	/**
	 * The speed of light.
	 */
	public static final double SPEED_OF_LIGHT = 300000;

	/**
	 * The space object's current velocity in space.
	 */
	protected Vector Velocity;
	
	/**
	 * Moves the space object for a duration <code>dt</code> according to its current state.
	 * The space object will be moved according to its current position and its current velocity during the specified duration <code>dt</code>.
	 * The specified duration <code>dt</code> can never be less than zero.
	 * If the space object is a ship, the ship will thrust.
	 * @param dt Specifies the duration of the move command.
	 * @post The new xPos (yPos) is equal to the old xPos (yPos) changed by the xVelocity (yVelocity) multiplied by the given duration. 
	 * 		| new Position.getX() = Position.getX() + Velocity.getX()*dt 
	 * 		| new Position.getY() = Position.getY() + Velocity.getY()*dt	 
	 */
	
	public void move(double dt){	
		
		if (dt>0){
			
		double xpos = (getPosition().getX() + getVelocity().getX()*dt);
		double ypos = (getPosition().getY() + getVelocity().getY()*dt);
		setPosition(new Vector(xpos,ypos));
		}
	}
	
	/**
	 * Returns the world this space object is in, null if this space object has no world.
	 * @return world
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	
	/**
	 * Puts this object in a particular world if this world is suitable.
	 * @param world 
	 * 		  The world in which this object will be placed.
	 * 
	 * @post The given world is set as the new world of the space object.
	 * 		 |new.getWorld().equals(world)
	 */
	public void setWorld(World world) {
		if (this.canHaveAsWorld(world))
		{this.world = world;}
	}
	
	/**
	 * Check whether this space object has a world.
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
	 */
	@Basic @Raw
	public void removeWorld () {
		if(this.hasWorld())
		{
			this.setWorld(null);
			
		}
	}
	
	/**
	 * Check whether this object fits in a given world.
	 * @param world
	 * 		  The world we check to see either this space object fits in it or not.
	 * @return True if this object fits in the given world, false if it doesn't.
	 */
	public boolean fitsInWorld (World world) {
		
		double xpos = getPosition().getX();
		double ypos = getPosition().getY();
		double radius = getRadius();
		
		if (xpos-radius <= 0 || xpos+radius >= world.getWorldWidth() || ypos-radius <= 0 || ypos+radius >= world.getWorldHeight())
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
	 * Returns the space object's radius.
	 * @return radius
	 */
	@Basic @Immutable
	public double getRadius() {
		
		return radius;
	}
	
	/**
	 * @param secondObject
	 * @return The sum of the radii of this space object and a second space object. 
	 */
	private double getSumOfRadii(SpaceObject secondObject) {
		return this.getRadius() + secondObject.getRadius();
	}

	/**
	 * Check whether the radius of this space object is valid.
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
	 *  The state of a space object.
	 */
	protected State state = State.ACTIVE;
	
	/**
	 * Returns the distance between this space object and a given space object. This distance is the shortest distance the space objects would have to move
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
	 * 
	 * @return If the result is finite, the distance between the two space objects will be the sum of their radii if they
	 * 		   move during the resulting time.
	 * 		   | if(result < Double.POSITIVE_INFINITY)
	 * 		   | this.move(result)
	 * 		   | secondObject.move(result)
	 * 		   | this.getDistanceBetween(secondObject) == this.getRadius()+secondObject.getRadius()
	 * 
	 * @return If the result is infinite, the distance between the two space objects will always be positive and never be equal to zero.
	 * 		   | if(result == Double.POSITIVE_INFINITY)
	 * 		   | this.getDistanceBetween(secondObject) > 0
	 */
	public double getTimeToCollision(SpaceObject secondObject) {
	
		Vector deltaR 		= secondObject.getPosition().subtract(this.getPosition());
		Vector deltaV 		= secondObject.getVelocity().subtract(this.getVelocity());
		double sigma 		= getRadius() + secondObject.getRadius();
		double deltaVdeltaR = deltaV.multiply(deltaR);
		double RSquared		= deltaR.multiply(deltaR);
		double VSquared		= deltaV.multiply(deltaV);
		double d 			= Math.pow(deltaVdeltaR, 2) - VSquared*(RSquared - Math.pow(sigma, 2));

		if(deltaVdeltaR >= 0||d < 0  )
		{
			return Double.POSITIVE_INFINITY;
		}
		else
		{
			return -(deltaVdeltaR + Math.sqrt(d))/(VSquared);
		}				

	}
		
	/**
	 * Returns the position of the collision with the given space object or null if they never collide.
	 * A space object can never collide with itself.
	 * @param secondObject 
	 * 			The space object to which the collision position should be calculated.
	 * 
	 * @return The position of the collision with the given space object or Double.POSITIVE_INFINITY if they never collide.
	 * 			|if (deltaT == Double.POSITIVE_INFINITY)
	 * 			|	then return null
	 * 			|
	 * 			|otherwise
	 * 			|double[] collisionPos = new double[2]
	 * 			|double theta = Math.atan2(secondObject.getPosition().getY()-this.getPosition().getY(), secondObject.getPosition().getX()-this.getPosition().getX())
	 * 			|if((secondObject.getPosition().getX()-this.getPosition().getX())<0)
	 * 			|	theta+=Math.PI*2
	 * 			|
	 * 			|collisionPos[0] = this.getPosition().getX() + this.radius * Math.cos(theta)
	 * 			|collisionPos [1] = this.getPosition().getY() + this.radius * Math.sin(theta)
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
	 * 			|double deltaRx = secondObject.getPosition().getX()-this.getPosition().getX()
	 * 			|double deltaRy = secondObject.getPosition().getY()-this.getPosition().getY()
	 * 			|double deltaVx = secondObject.getVelocity().getX()- this.getVelocity().getX()
	 * 			|double deltaVy = secondObject.getVelocity().getY()-this.getVelocity().getY()
	 * 			|double deltaVR = (deltaVx*deltaRx)+(deltaVy*deltaRy)
	 * 			|
	 * 			|double J = (2*this.getMass()*secondObject.getMass()*deltaVR)/(sigma*(this.getMass()+secondObject.getMass()))
	 * 			|double Jx = (J*deltaRx)/(sigma)
	 * 			|double Jy = (J*deltaRy)/(sigma)
	 * 			|
	 * 			|(new this).getXVelocity = this.getVelocity().getX() + Jx/this.getMass()
	 * 			|(new this).getYVelocity = this.getVelocity().getY() + Jy/this.getMass()
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
		double deltaVx = secondObject.getVelocity().getX()- this.getVelocity().getX();
		double deltaVy = secondObject.getVelocity().getY()-this.getVelocity().getY();
		double deltaVR = (deltaVx*deltaRx)+(deltaVy*deltaRy);
	
		double J = (2*this.getMass()*secondObject.getMass()*deltaVR)/(sigma*(this.getMass()+secondObject.getMass()));
		double Jx = (J*deltaRx)/(sigma);
		double Jy = (J*deltaRy)/(sigma);
		double newxvelocity = this.getVelocity().getX()+(Jx/this.getMass());
		double newyvelocity = this.getVelocity().getY()+(Jy/this.getMass());
		setVelocity(new Vector(newxvelocity,newyvelocity));
		double newxvelocity2 = secondObject.getVelocity().getX()-(Jx/secondObject.getMass());
		double newyvelocity2 = secondObject.getVelocity().getY()-(Jy/secondObject.getMass());
		secondObject.setVelocity(new Vector(newxvelocity2,newyvelocity2));
		secondObject.getWorld().addModifiedSpaceObject(secondObject);
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
	 * 			| if(Util.fuzzyLessThanOrEqualTo(getVelocity().getY(),0))
	 * 			| 	then timeToBoundaryY = Math.abs((getPosition().getY()- getRadius())/(getYVelocity()))
	 * 			| 
	 * 			| else if (!Util.fuzzyLessThanOrEqualTo(getVelocity().getY(),0))
	 * 			| 	then timeToBoundaryY = Math.abs((worldheight - getPosition().getY() - getRadius())/(getVelocity().getY()))
	 * 			| 
	 * 			| else	
	 * 			| 	timeToBoundaryY = Double.POSITIVE_INFINITY;
	 *			|
	 *			| if(Util.fuzzyLessThanOrEqualTo(getVelocity().getX(),0))
	 *			| 	then timeToBoundaryX = Math.abs((getPosition().getX()- getRadius())/(getVelocity().getX()))
	 *			|
	 *			| else if (!Util.fuzzyLessThanOrEqualTo(getVelocity().getX(),0))
	 *			| 	timeToBoundaryX = Math.abs((worldwidth - getPosition().getX() - getRadius())/(getVelocity().getX()))
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

		if(Util.fuzzyLessThanOrEqualTo(this.getVelocity().getY(),0)) {
			
			timeToBoundaryY = Math.abs((getPosition().getY()- getRadius())/(getVelocity().getY()));}
		
		else {
			
			timeToBoundaryY = Math.abs((worldheight - getPosition().getY() - getRadius())/(getVelocity().getY()));
		}
		
		if(Util.fuzzyLessThanOrEqualTo(this.getVelocity().getX(),0)) {
			
			timeToBoundaryX = Math.abs((getPosition().getX()- getRadius())/(getVelocity().getX()));}
		
		else {
			
			timeToBoundaryX = Math.abs((worldwidth - getPosition().getX() - getRadius())/(getVelocity().getX()));
		}
		
		
		return Math.min(timeToBoundaryX, timeToBoundaryY);
		
	}
	
	/**
	 * Resolves the collision between this space object and a boundary.
	 * 
	 * @effect If the space object hits a horizontal boundary, invert the y-velocity.
	 * 		   If the space object hits a vertical boundary, invert the x-velocity.
	 * 			| if(Util.fuzzyEquals(getPosition().getX(),getRadius()) || Util.fuzzyEquals((getPosition().getX() + getRadius()), getWorld().getWorldWidth()))
	 * 			| 	then this.setVelocity(new Vector(-getVelocity().getX(),getVelocity().getY()))
	 * 			|
	 * 			| else
	 * 			| 	setVelocity(new Vector(getVelocity().getX(),-getVelocity.getY())
	 * 			|
	 * @throws NullPointerException
	 * 				This space object doesn't have a world.
	 */
	public void resolveBoundaryCollision()
	{
		if(getWorld()==null) throw new NullPointerException();
		
		if(Util.fuzzyEquals(getPosition().getX(),getRadius()) || 
				Util.fuzzyEquals((getPosition().getX() + getRadius()), getWorld().getWorldWidth())) {
			
			setVelocity(new Vector(-(this.getVelocity().getX()),this.getVelocity().getY()));
		}
		
		else {
			setVelocity(new Vector(this.getVelocity().getX(),-(this.getVelocity().getY())));}
		getWorld().addModifiedSpaceObject(this);
		
		
	}
	
	/**
	 * Resolves the collision between this space object and another space object.
	 * @param collisionobject
	 * 				The space object in collision with this space object.
	 */
	public abstract void resolve(SpaceObject collisionobject);
	
	/**
	 * Terminates this object. The object will be removed from its current world.
	 * 
	 * @effect If this object is in a world, it will be removed from that world.
	 * 	
	 */
	protected void terminate()
	{
		if(getWorld() == null) {throw new NullPointerException();}
		getWorld().removeObject(this);
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
