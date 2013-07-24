package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of position describes a x position and an y position in the two-dimensional space.
 * 
 * @invar 	x always needs to have a valid value.
 * 			| isValidValue(getX())
 * @invar	y always needs to have a valid value.
 * 			| isValidValue(getY())
 * 
 * @author Tom De Ferm
 * @version 0.1
 *
 */
@Value
public class Position {
	/**
	 * Initialize this new coordinate with the given x and y values.
	 * @param 	x
	 * 			The value for the x-coordinate.
	 * @param 	y
	 * 			The value for the y-coordinate.
	 * @post	The x value of the new coordinate is equal to the given x value.
	 * 			| new.getX() == x
	 * @post	The y value of the new coordinate is equal to the given y value.
	 * 			| new.getY() == y
	 * @throws	IllegalValueException
	 * 			The given x is not a valid value for the x coordinate.
	 * 			| ! isValidValue(x)
	 * @throws	IllegalValueException
	 * 			The given y is not a valid value for the y coordinate.
	 * 			| ! isValidValue(y)
	 */
	public Position(double x, double y){
		if(!isValidValue(x)){
			throw new IllegalArgumentException();
		}
		if(!isValidValue(y)){
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * Initialize this new position with initial x and y values equal to zero.
	 * 
	 * @effect	This new position is initialized with zero as x and y value.
	 * 			|this(0,0)
	 */
	public Position(){
		this(0,0);
	}


	/**
	 * Return the x-value of the current position.
	 */
	@Basic @Raw
	public double getX(){return x;}

	/**
	 * x-value of the current position. 
	 */
	private double x;

	/**
	 * Return the y-value of the current position.
	 */
	@Basic @Raw
	public double getY(){return y;}

	/**
	 * y-value of the current position.
	 */
	private double y;

	/**
	 * Checks whether the given coordinate has a valid value.
	 * @param 	value
	 * 			The value to check.
	 * @return	True if and only if the given value is a number.
	 * 			| result == !(Double.isNaN(value)
	 */
	private static boolean isValidValue(double value){
		return !(Double.isNaN(value));
	}

	/**
	 * Returns the distance between this position and an other position. This distance is the shortest distance the space objects would have to move
	 * to be adjacent to each other. When the given space object is the same as this space object, the distance is zero, when the space objects overlap, the distance is negative.
	 * @param other 
	 * 				The other position to which the distance should be calculated.
	 * 
	 * @return The distance between the 2 positions. The distance is negative if and only if the space objects overlap.
	 * 			|double centerDistanceSquared = Math.pow(this.getX()-other.getX() , 2) + Math.pow(this.getY()-other.getY(), 2)
	 * 			|result == Math.sqrt(centerDistanceSquared)
	 * @throws IllegalArgumentException
	 * 			if the other position is not initialized.
	 * 			|other == null
	 */
	public double getDistanceBetween(Position other) throws IllegalArgumentException{
		try{
			double centerDistanceSquared = 0.0;
			centerDistanceSquared = Math.sqrt(Math.pow(this.getX() - other.getX(),2) + Math.pow(this.getY() - other.getY(), 2));
			return centerDistanceSquared;
		}catch (NullPointerException nu){
			assert (other == null);
			throw new IllegalArgumentException("Not an initialized position");
		}

	}
	

	/**
	 * Modify the position.
	 * @param 	newPosition
	 * 			The other position to transform to.
	 * @effect	The x and y values of the other position are assigned to 
	 * 			the x and y values of this position.
	 * 			|new.setX(newPosition.getX());
	 * 			|new.setY(newPosition.getY());
	 * @throws IllegalArgumentException
	 * 			The other position is not initialized.
	 * 			|newPosition == null
	 */
	public void setPosition(Position newPosition) throws IllegalArgumentException{
		try{
			this.setXPos(newPosition.getX());
			this.setYPos(newPosition.getY());

		}catch (NullPointerException nu){
			assert (newPosition == null);
			throw new IllegalArgumentException("Not an initialized coordinate");
		}
	}

	/**
	 * Set the value of the x coordinate of the position.
	 * @param 	value
	 * 			The new value for the x coordinate.
	 */
	private void setXPos(double value){this.x = value;}

	/**
	 * Set the value of the y coordinate of the position.
	 * @param 	value
	 * 			The new value for the y coordinate.
	 */
	private void setYPos(double value){this.y = value;}


}