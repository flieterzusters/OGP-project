package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Util;

/**
 * A class of vectors describes a x value and an y value in the two-dimensional space.
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

public class Vector {
	

	/**
	 * Initialize this new vector with the given x and y values.
	 * @param 	x
	 * 			The value for the x-coordinate.
	 * @param 	y
	 * 			The value for the y-coordinate.
	 * @post	The x value of the new coordinate is equal to the given x value.
	 * 			| new.getX() == x
	 * @post	The y value of the new coordinate is equal to the given y value.
	 * 			| new.getY() == y
	 * @throws	IllegalArgumentException
	 * 			The given x is not a valid value for the x coordinate.
	 * 			| ! isValidValue(x)
	 * @throws	IllegalArgumentException
	 * 			The given y is not a valid value for the y coordinate.
	 * 			| ! isValidValue(y)
	 */
	public Vector(double x, double y) 
	{		
		if(!isValidValue(x)){
			throw new IllegalArgumentException();
		}
		if(!isValidValue(y)){
			throw new IllegalArgumentException();
		}
		this.xpos = x;
		this.ypos = y;
	}
	
	/**
	 * Initialize this new vector with initial x and y values equal to zero.
	 * 
	 * @effect	This new vector is initialized with zero as x and y value.
	 * 			|this(0,0)
	 */
	public Vector(){
		this(0,0);
	}

	/**
	 * Return the x-value of the current position.
	 */
	@Basic @Raw
	public double getX(){return xpos;}

	/**
	 * x-value of the current position. 
	 */
	private double xpos;

	/**
	 * Return the y-value of the current position.
	 */
	@Basic @Raw
	public double getY(){return ypos;}

	/**
	 * y-value of the current position.
	 */
	private double ypos;
	
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
	public double getDistanceBetween(Vector other) throws IllegalArgumentException{
		try{
			double centerDistanceSquared = 0.0;
			centerDistanceSquared = Math.sqrt(Math.pow(this.getX() - other.getX(),2) + Math.pow(this.getY() - other.getY(), 2));
			return centerDistanceSquared;
		}catch (NullPointerException nu){
			assert (other == null);
			throw new IllegalArgumentException("Not an initialized vector");
		}

	}
	

	/**
	 * Modify the coordinates.
	 * @param 	newVector
	 * 			The other coordinates to transform to.
	 * @effect	The x and y values of the other coordinates are assigned to 
	 * 			the x and y values of this coordinates.
	 * 			|new.setX(newVector.getX());
	 * 			|new.setY(newVector.getY());
	 * @throws IllegalArgumentException
	 * 			The other position is not initialized.
	 * 			|newVector == null
	 */
	public void setVector(Vector newVector) throws IllegalArgumentException{
		try{
			this.setX(newVector.getX());
			this.setY(newVector.getY());

		}catch (NullPointerException nu){
			assert (newVector == null);
			throw new IllegalArgumentException("Not an initialized coordinate");
		}
	}

	/**
	 * Set the value of the x coordinate of the position.
	 * @param 	value
	 * 			The new value for the x coordinate.
	 */
	private void setX(double value){this.xpos = value;}

	/**
	 * Set the value of the y coordinate of the position.
	 * @param 	value
	 * 			The new value for the y coordinate.
	 */
	private void setY(double value){this.ypos = value;}

	/**
	 * @param secondVector
	 * @return The sum of two vectors.
	 */
	public Vector add (Vector secondVector)
	{
		if (secondVector == null)
			return this;

		double x1, x2, y1, y2;
		x1 = this.getX();
		x2 = secondVector.getX();
		y1 = this.getY();
		y2 = secondVector.getY();
		return new Vector(x1+x2, y1+y2);
	}

	/**
	 * @param secondVector
	 * @return The difference between two vectors.
	 */
	public Vector subtract (Vector secondVector)
	{
		if (secondVector == null)
			return this;
		return this.add(  new Vector(  -secondVector.getX(), -secondVector.getY()  )  );
	}

	/**
	 * @param secondVector
	 * @return The product of two vectors.
	 */
	public double multiply (Vector secondVector) 
	{
		if (secondVector == null)
			throw new IllegalArgumentException();

		double x1, x2, y1, y2;
		x1 = this.getX();
		x2 = secondVector.getX();
		y1 = this.getY();
		y2 = secondVector.getY();
		return x1*x2 + y1*y2;
	}

	/**
	 * @param constant
	 * @return The product of this vector and a certain constant.
	 */
	public Vector multiply (double constant)
	{
		if (Double.isNaN(constant))
			return this;
		return new Vector( getX()*constant, getY()*constant);
	}
	
	/**
	 * @return The norm of this vector.
	 */
	public double getNorm ()
	{
		return Math.sqrt( Math.pow(getX(), 2) + Math.pow(getY(), 2) );
	}

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

	public boolean equals (Object other)
	{
		if (other == null || !(other instanceof Vector) )
			return false;
		return Util.fuzzyEquals(getX(), ( (Vector) other).getX() ) && Util.fuzzyEquals(getY(), ( (Vector) other).getY() );
	}

	

}


