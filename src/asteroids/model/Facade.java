package asteroids.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import org.antlr.v4.runtime.RecognitionException;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.ModelException;
import asteroids.IFacade.ParseOutcome;
import asteroids.IFacade.TypeCheckOutcome;
import asteroids.model.programs.*;
import asteroids.model.programs.parsing.*;
import asteroids.model.programs.Expression.*;
import asteroids.model.programs.Statement.*;

import java.io.*;

import java.lang.StringBuilder;



/**
 * @author: Tom De Ferm
 * @version: 0.1
 */

public class Facade implements IFacade<World, Ship, Asteroid, Bullet, asteroids.model.Program> {

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
		try {world.addObject(ship);}	
		catch (IllegalArgumentException illegalobject){
			throw new ModelException (illegalobject);}
		
	}
	
	@Override
	public void addAsteroid(World world, Asteroid asteroid) 
	{
		try {world.addObject(asteroid);}	
		catch (IllegalArgumentException illegalobject){
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
		
		try 
		{
			Vector position = new Vector(x,y);
			Vector velocity = new Vector(xVelocity, yVelocity);
			Ship ship1 = new Ship(position, velocity, radius, angle, mass);
			return ship1;
		}
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
		return ship.getPosition().getX();
	}

	@Override
	public double getShipY(Ship ship) 
	{
		return ship.getPosition().getY();
	}

	@Override
	public double getShipXVelocity(Ship ship) 
	{
		return ship.getVelocity().getX();
	}
	

	@Override
	public double getShipYVelocity(Ship ship) 
	{
		return ship.getVelocity().getY();
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
		try {
		ship.fireBullet();}
		catch (IllegalArgumentException illegalargument) {
			throw new ModelException (illegalargument);
		}
	}
		
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		
		try 
		{
			Vector position = new Vector (x,y);
			Vector velocity = new Vector(xVelocity, yVelocity);
			Asteroid asteroid = new Asteroid (position, velocity, radius);
			return asteroid;
		}
		catch (IllegalArgumentException illegalargument){
			throw new ModelException (illegalargument);}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius, Random random) throws ModelException {
		
		try 
		{
			Vector position = new Vector(x,y);
			Vector velocity = new Vector(xVelocity, yVelocity);
			Asteroid asteroid = new Asteroid (position, velocity, radius, random);
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
		return asteroid.getPosition().getX();
	}

	  
	public double getAsteroidY(Asteroid asteroid) 
	{
		return asteroid.getPosition().getY();
	}

	  
	public double getAsteroidXVelocity(Asteroid asteroid) 
	{
		return asteroid.getVelocity().getX();
	}

	 
	public double getAsteroidYVelocity(Asteroid asteroid) 
	{
		return asteroid.getVelocity().getY();
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
		return bullet.getPosition().getX();
	}

	public double getBulletY(Bullet bullet) 
	{
		return bullet.getPosition().getY();
	}

	public double getBulletXVelocity (Bullet bullet) 
	{
		return bullet.getVelocity().getX();
	}

	public double getBulletYVelocity(Bullet bullet) 
	{
		return bullet.getVelocity().getY();
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

	@Override
	public asteroids.IFacade.ParseOutcome<Program> parseProgram(String text) {
		 
		ProgramFactoryImplementation factory = new ProgramFactoryImplementation();
		    ProgramParser<Expression, Statement, Type> parser = new ProgramParser<>(factory);
		    try {parser.parse(text);
		        List<String> errors = parser.getErrors();
		        if(! errors.isEmpty()) {
		          return ParseOutcome.failure(errors.get(0));
		        } else {
		        	Statement statement = parser.getStatement();
		        	Program program = new Program();
		        	program.setGlobals(parser.getGlobals());
		        	program.setStatement(statement);
		        	factory.setProgram(program);
		          return ParseOutcome.success(program); 
		        }
		    } catch(RecognitionException e) {
		      return ParseOutcome.failure(e.getMessage());
		    }
	}
	

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromStream(
			InputStream stream) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromUrl(URL url)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTypeCheckingSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public asteroids.IFacade.TypeCheckOutcome typeCheckProgram(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShipProgram(Ship ship, Program program) {
		ship.setProgram(program);
		
	}

}

	


