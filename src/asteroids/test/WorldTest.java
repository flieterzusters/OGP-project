package asteroids.test;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.*;
import asteroids.model.*;
import asteroids.model.Vector;
import java.util.*;



public class WorldTest {
	
	private static World world;
	private static Vector position;
	private static Vector velocity;
	private static SpaceObject ship;
	private static SpaceObject asteroid;
	private static SpaceObject bullet;
	private static Ship ship2;
	
	@Before
	public void setUpMutableFixture() throws Exception 
	{
		world = new World(1024,768);
		position = new Vector(12000,12000);
		velocity = new Vector(18000,18000);
		ship = new Ship(new Vector(50,50), new Vector(-5,10), 12, 3, 900 ); 
		asteroid = new Asteroid(new Vector(200,300), new Vector(-10,-15), 20);
		bullet = new Bullet(new Vector(500,600), new Vector(10,15), 3, (Ship) ship);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInfiniteDimensions() {
		world = new World (Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructorNegativeDimensions() {
		world = new World (-1,-1);
	}

	@Test
	public void testAddObject() {
		world.addObject(ship);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testAddNullObject() {
		Ship ship1 = null;
		world.addObject(ship1);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testAddObject_doesNotFitInWorld() {
		Ship ship = new Ship (new Vector(2000, 300),new Vector(0,0), 1, 1, 1);
		world.addObject(ship);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testAddObject_doesNotFitInWorld_Negative() {
		Ship ship = new Ship (new Vector(-2000, -300),new Vector(0,0), 1, 1, 1);
		world.addObject(ship);
	}

	@Test
	public void testRemoveObject() {
		world.addObject(ship);
		world.removeObject(ship);
		assertEquals(0, world.getObjects().size());
	}

	@Test (expected=IllegalArgumentException.class)
	public void testRemoveObject_IfNull() {
		world.removeObject(null);
	}


}
