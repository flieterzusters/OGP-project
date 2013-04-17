package asteroids.model;

import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.ModelException;


/**
 * @author: Tom De Ferm
 * @version: 0.1
 */

public class Facade implements IFacade<World, Ship, Asteroid, Bullet> {

	@Override
	public World createWorld(double width, double height) {
		
		try {World world1 = new World(width, height);
		return world1;}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
	}
	
	@Override
	public double getWorldWidth (World world) 
	{
		return world.getWorldWidth();
	}
	
	
	@Override
	public double getWorldHeight (World world) 
	{
		return world.getWorldHeight();
	}
	
	@Override
	public Set<Ship> getShips(World world) 
	{
		return world.getShips();
	}
	
	@Override
	public Set<Asteroid> getAsteroids(World world) 
	{
		return world.getAsteroids();
	}
	
	@Override
	public Set<Bullet> getBullets(World world) 
	{
		return world.getBullets();
	}
	
	@Override
	public void addShip(World world, Ship ship) 
	{
		try {world.addObject(ship);
			ship.setWorld(world);}	
		catch (IllegalObjectException illegalobject){
			throw new ModelException (illegalobject);}
		
	}
	
	@Override
	public void addAsteroid(World world, Asteroid asteroid) 
	{
		try {world.addObject(asteroid);
			asteroid.setWorld(world);}	
		catch (IllegalObjectException illegalobject){
		throw new ModelException (illegalobject);}
	}
	
	@Override
	public void removeShip(World world, Ship ship) 
	{
		world.removeObject(ship);
		ship.removeWorld();
	}
	
	@Override
	public void removeAsteroid(World world, Asteroid asteroid) 
	{
		world.removeObject(asteroid);
		asteroid.removeWorld();
	}
	
	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) 
	{
		try
		{
			world.evolve(dt);
		}
		catch (IllegalArgumentException illegalargument) {
			throw new ModelException (illegalargument);}
		
		}
		
		
	

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle, double mass) throws ModelException {
		
		try {Ship ship1 = new Ship(x, y, xVelocity, yVelocity, radius, angle, mass);
		return ship1;}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
		
	}
	
	@Override
	public boolean isShip(Object o) {
		
		if (o == null) {
			throw new NullPointerException();}
		
		if (o instanceof Ship) {return true;}
		else {return false;}
	}

	@Override
	public double getShipX(Ship ship) 
	{
		return ship.getX();
	}

	@Override
	public double getShipY(Ship ship) 
	{
		return ship.getY();
	}

	@Override
	public double getShipXVelocity(Ship ship) 
	{
		return ship.getXVelocity();
	}
	

	@Override
	public double getShipYVelocity(Ship ship) 
	{
		return ship.getYVelocity();
	}
	

	@Override
	public double getShipRadius(Ship ship) 
	{
		return ship.getRadius();
	}

	@Override
	public double getShipDirection(Ship ship) 
	{
		return ship.getDirection();
	}
	
	@Override
	public double getShipMass(Ship ship) 
	{
		return ship.getMass();
	}
	
	
	@Override
	public World getShipWorld(Ship ship) 
	{
		return ship.getWorld();
	}
	
	@Override
	public boolean isShipThrusterActive(Ship ship) 
	{
		return ship.isThrusterActive();
	}
	
	@Override
	public void setThrusterActive(Ship ship, boolean active) 
	{
		ship.setThrusterActive(active);
	}
	
	@Override
	public void turn(Ship ship, double angle) 
	{
		ship.turn(angle);
	}
	
	@Override
	public void fireBullet(Ship ship) 
	{
		ship.fireBullet();
	}
		
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		
		try {Asteroid asteroid = new Asteroid (x, y, xVelocity, yVelocity, radius);
		return asteroid;}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius, Random random) throws ModelException {
		
		try {Asteroid asteroid = new Asteroid (x,y,xVelocity, yVelocity, radius, random);
		return asteroid;}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
	}
	

	@Override 
	public boolean isAsteroid(Object o) {

		if (o == null) {
			throw new NullPointerException();
		}
		if (o instanceof Asteroid) {return true;}
		else {return false;}
	}

	 
	public double getAsteroidX(Asteroid asteroid) 
	{
		return asteroid.getX();
	}

	  
	public double getAsteroidY(Asteroid asteroid) 
	{
		return asteroid.getY();
	}

	  
	public double getAsteroidXVelocity(Asteroid asteroid) 
	{
		return asteroid.getXVelocity();
	}

	 
	public double getAsteroidYVelocity(Asteroid asteroid) 
	{
		return asteroid.getYVelocity();
	}

	 
	public double getAsteroidRadius(Asteroid asteroid) 
	{
		return asteroid.getRadius();
	}

	 
	public double getAsteroidMass(Asteroid asteroid) 
	{
		return asteroid.getMass();
	}

	public World getAsteroidWorld(Asteroid asteroid) 
	{
		return asteroid.getWorld();
	}

	 
	public boolean isBullets(Object o) {
		
		if(o==null) {throw new NullPointerException();}
		if (o instanceof Bullet) {return true;}
		else {return false;}
	}
		

	public double getBulletX(Bullet bullet) 
	{
		return bullet.getX();
	}

	public double getBulletY(Bullet bullet) 
	{
		return bullet.getY();
	}

	public double getBulletXVelocity (Bullet bullet) 
	{
		return bullet.getXVelocity();
	}

	public double getBulletYVelocity(Bullet bullet) 
	{
		return bullet.getYVelocity();
	}

	public double getBulletRadius(Bullet bullet) 
	{
		return bullet.getRadius();
	}
		
	public double getBulletMass(Bullet bullet) 
	{
		return bullet.getMass();
	}

	public World getBulletWorld(Bullet bullet) 
	{
		return bullet.getWorld();
	}

	public Ship getBulletSource(Bullet bullet) 
	{
		return bullet.getSource();
	}

}

	


