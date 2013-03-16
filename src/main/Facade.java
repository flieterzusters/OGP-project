package main;

import asteroids.IFacade;
import asteroids.IShip;
import asteroids.ModelException;


/**
 * @author: Jasper, Tom
 * @version: 0.1
 */

public class Facade implements IFacade {

	@Override
	public IShip createShip() {
		// TODO Auto-generated method stub
		Ship ship1 = new Ship();
		return ship1;
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) {
		// TODO Auto-generated method stub
		try {Ship ship1 = new Ship(x, y, xVelocity, yVelocity, radius, angle);
		return ship1;}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
		
	}

	@Override
	public double getX(IShip ship) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		return bigShip1.getX();
	}

	@Override
	public double getY(IShip ship) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		return bigShip1.getY();
	}

	@Override
	public double getXVelocity(IShip ship) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		return bigShip1.getXVelocity();
	}

	@Override
	public double getYVelocity(IShip ship) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		return bigShip1.getYVelocity();
	}

	@Override
	public double getRadius(IShip ship) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		return bigShip1.getRadius();
	}

	@Override
	public double getDirection(IShip ship) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		return bigShip1.getDirection();
	}

	@Override
	public void move(IShip ship, double dt) {
		// TODO Auto-generated method stub
		try {Ship bigShip1 = (Ship)ship;
		bigShip1.move(dt);}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
	}

	@Override
	public void thrust(IShip ship, double amount) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		bigShip1.thrust(amount);
	}

	@Override
	public void turn(IShip ship, double angle) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship;
		bigShip1.turn(angle);
		
	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship1;
		return bigShip1.getDistanceBetween((Ship)ship2);
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship1;
		return bigShip1.overlap((Ship)ship2);
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship1;
		return bigShip1.getTimeToCollision((Ship)ship2);
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		Ship bigShip1 = (Ship)ship1;
		return bigShip1.getCollisionPosition((Ship)ship2);
	}

}
