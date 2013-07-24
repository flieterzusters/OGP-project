package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of velocity describes a velocity in the two-dimensional space, with an x-component
 * and an y-component.
 * 
 * @invar 	The value of XVelocity must always be valid. It may never be higher than the
 * 			speed of light.
 * 			| isValidVelocity(getXVelocity())
 * @invar	The value of YVelocity must always be valid. It may never be higher than the
 * 			speed of light.
 * 			| isValidVelocity(getYVelocity())
 * @invar	The value of maxVelocity must always be valid. It may never be higher than the
 * 			speed of light.
 * 			| getMaxVelocity() <= SPEED_OF_LIGHT)
 * 
 * 
 * @author Tom De Ferm
 * @version 0.1
 *
 */
@Value
public class Velocity {
	/**
	 * Initialize a new velocity. 
	 * The given maximum velocity can never be higher than the speed of light.
	 * 
	 * @param	XVelocity
	 * 			The value for the velocity in the x-direction.
	 * @param	YVelocity
	 * 			The value for the velocity in the y-direction.
	 * @param	maxVelocity
	 * 			The value for the maximum velocity. This value may
	 * 			never be higher than the speed of light.
	 * 
	 * @post	The velocity in the x and y direction is equal to the given 
	 * 			velocity in the x and y direction, if the given velocity is valid.
	 * 			If the given velocity is invalid the velocity of this ship will be modified by the method 'makeVelocityValid'.
	 * 			| if (isValidVelocity(getTotalVelocity(velocityX,velocityY))
	 * 			| 	then new.getXVelocity() == velocityX
	 * 			|		 new.getYVelocity() == velocityY
	 * 			| else
	 * 			| 	makeVelocityValid(velocityX,velocityY)
	 * 
	 * @post	If the given maximum velocity is valid, this ship's maximum velocity is equal to the given 
	 * 			maximum velocity, else this ship's maximum velocity is equal to the speed of light.
	 * 			| if (maxVelocity > SPEED_OF_LIGHT || Double.isNaN(maxVelocity)) 
	 * 			| 	then new.getMaxVelocity == SPEED_OF_LIGHT
	 * 			|else
	 * 			|	new.getMaxVelocity == maxVelocity
	 */
	public Velocity(double velocityX, double velocityY, double maxVelocity){
		if(maxVelocity > SPEED_OF_LIGHT || Double.isNaN(maxVelocity)){
			maxVelocity = SPEED_OF_LIGHT; 
		}
		this.maxVelocity = maxVelocity;

		if(!isValidVelocity(getTotalVelocity(velocityX,velocityY))){
			makeVelocityValid(velocityX, velocityY);
		}
		this.XVelocity = velocityX;
		this.YVelocity = velocityY;

	}

	/**
	 * Initialize this new velocity in the two-dimensional space.
	 * Maximum velocity is automatically set to the speed of light.
	 * 
	 * @param	velocityX
	 * 			The value for the velocity in the x-direction.
	 * @param	velocityY
	 * 			The value for the velocity in the y-direction.
	 * @effect	This new velocity is initialized with given velocities in x and y direction and
	 * 			the speed of light as maximum velocity.
	 * 			| this(velocityX,velocityY,SPEED_OF_LIGHT)
	 */

	public Velocity(double velocityX, double velocityY){
		this(velocityX,velocityY,SPEED_OF_LIGHT);
	}


	/**
	 * Return the velocity in the x direction.
	 */
	@Basic @Raw
	public double getXVelocity(){return XVelocity;}

	/**
	 * The velocity in the x direction.
	 */
	private double XVelocity;

	/**
	 * Return the velocity in the y direction.
	 */
	@Basic @Raw
	public double getYVelocity(){return YVelocity;}

	/**
	 * The velocity in the y direction.
	 */
	private double YVelocity;

	/**
	 * Return the maximum velocity.
	 */
	@Basic @Raw
	public double getMaxVelocity(){return maxVelocity;}

	/**
	 *The maximum velocity.
	 */
	private final double maxVelocity;

	/**
	 * Return the total velocity  of this object.
	 * 
	 * @param	XVelocity
	 * 			The given velocity in the X direction.
	 * @param	YVelocity
	 * 			The given velocity in the Y direction.
	 * @return	The total velocity.
	 */
	public double getTotalVelocity(double XVelocity, double YVelocity){
		double velocity = 0.0;
		velocity = Math.sqrt(Math.pow(getXVelocity(),2)+Math.pow(getYVelocity(),2));
		return velocity;
	}

	/**
	 * The speed of light.
	 */
	public static final double SPEED_OF_LIGHT = 300000;

	/**
	 * Return true if the given velocity is a valid value.
	 * The given velocity may not exceed the maximum velocity or not be less than
	 * zero, and must be a number.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the given velocity does not exceed the
	 * 			maximum velocity, is not less than zero and is a number.
	 * 			| result ==
	 * 			|	velocity <= maxVelocity
	 * 			|	velocity >= 0
	 * 			|	!Double.isNaN(velocity)
	 */
	private boolean isValidVelocity(double velocity){
		if(velocity <= getMaxVelocity() && velocity >= -getMaxVelocity() && !Double.isNaN(velocity)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method reduces a space object's velocity to make it comply with it's speed limit without changing the direction of movement.
	 * @param xVelocity The x-velocity the space object currently possesses.
	 * @param yVelocity The y-velocity the space object currently possesses.
	 * @post The new getXVelocity (getYVelocity) satisfies the upper speed limit of the space object and the direction of movement is the same as before.
	 * 		 | new getVelocity() <= this.speedLimit
	 * 		 | new atan2(getYVelocity(),getXVelocity()) = atan2(getYvelocity(), getXVelocity)	
	 */
	@Raw @Model
	public void makeVelocityValid(double Xvelocity, double Yvelocity) {
		
		double angleOfMovement = Math.atan2(Xvelocity, Yvelocity);
		double xvelocity = getMaxVelocity()*Math.cos(angleOfMovement);
		double yvelocity = getMaxVelocity()*Math.sin(angleOfMovement);
		setXVelocity(xvelocity);
		setYVelocity(yvelocity);
		
	}

	/**
	 * Set the velocity in the x an y direction.
	 * @param 	Xvelocity
	 * 			The velocity in the x direction.
	 * @param 	Yvelocity
	 * 			The velocity in the y direction.
	 * @post	If the given velocity in the x direction is a valid velocity,
	 * 			the velocity in the x direction of this class is set to the
	 * 			given value.
	 * 			| if(isValidVelocity(velocityX)
	 * 			|	then new.getXVelocity == velocityX
	 * @post	if the given velocity in the Y direction is a valid velocity,
	 * 			the velocity in the Y direction of this class is set to the
	 * 			given value.
	 * 			| if(isValidVelocity(velocityY)
	 * 			|	then new.getYVelocity == velocityY
	 */
	public void setVelocity(double velocityX, double velocityY){
		if(isValidVelocity(velocityX))
			this.setXVelocity(velocityX);
		if(isValidVelocity(velocityY))
			this.setYVelocity(velocityY);
		if(!isValidVelocity(getTotalVelocity(velocityX,velocityY)))
		{
			makeVelocityValid(velocityX,velocityY);
		}
	}

	/**
	 * Set the value of the velocity in the X direction.
	 * @param 	velocityX
	 * 			The new velocity in the x direction.
	 */
	private void setXVelocity(double velocityX){this.XVelocity = velocityX;}

	/**
	 * Set the value of the velocity in the y direction.
	 * @param 	velocityY
	 * 			The new velocity in the y direction.
	 */
	private void setYVelocity(double velocityY){this.YVelocity = velocityY;}

	public static double computeXVelocity(double angle, double trueVelocity){
		return Math.cos(angle) * trueVelocity;
	}

	public static double computeYVelocity(double angle, double trueVelocity){
		return Math.sin(angle) * trueVelocity;
	}


	



	

}