package asteroids.test;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.*;
import asteroids.model.*;



public class WorldTest {
	
	private static World emptyWorld, worldWithObj;
	private static SpaceObject ship1, asteroid1, bullet1;
	private static Ship bulletSource;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emptyWorld = new World(1024, 768);
	}
	
	@Before
	public void setUp() throws Exception {
		ship1 = new Ship(50, 50, 100, 200, 15, 0, 100);
		asteroid1 = new Asteroid(300, 400, -45, -60, 20);
		bullet1 = new Bullet(500, 700, 60, -20, 40, worldWithObj, bulletSource);
		worldWithObj = new World(1000, 1000);
		worldWithObj.addObject(ship1);
		worldWithObj.addObject(bullet1);
			
	}
	
	@Test
	public void ExtendedConstructor_legalCase() {
		assertTrue(emptyWorld.getWorldWidth() == 1024);
		assertTrue(emptyWorld.getWorldHeight() == 768);
		assertTrue(emptyWorld.getObjects() == null);
	}
	
	
	@Test
	public void ExtendedConstructor_NegativeBoundaryCase() {
		try {
			@SuppressWarnings("unused")
			World world = new World(-50, -75);
			fail("Expected IllegalArgumentException");}
			catch (IllegalArgumentException thrown) {
				assertThat(thrown.getMessage(), Is.is("The provided width and/or height is negative"));
		}
	}
	
	@Test
	public void getShipsTest() {
		World world = new World(1000, 1000);
		world.addObject(ship1);
		assertTrue(world.getShips() != null);
		assertEquals(emptyWorld.getShips(),null);
	}
	
	@Test
	public void getAsteroidsTest() {
		World world = new World(1000,1000);
		world.addObject(asteroid1);
		assertTrue(world.getAsteroids() != null);
		assertEquals(emptyWorld.getAsteroids(),null);
	}
	
	@Test
	public void getBulletsTest() {
		World world = new World (800,800);
		world.addObject(bullet1);
		assertTrue(world.getBullets() != null);
		assertEquals(emptyWorld.getBullets(),null);
	}
	
	@Test
	public void testAddObject_NormalCase() {
		World world = new World (1000,1000);
		world.addObject(ship1);
		assertTrue(ship1.getWorld() == world);
	}
	
	@Test (expected=NullPointerException.class)
	public void testAddObject_Null() {
		World world = new World (800,800);
		Ship ship = null;
		world.addObject(ship);
	}
	
	@Test (expected=IllegalObjectException.class)
	public void testAddObject_validSpaceObj() {
		Asteroid asteroid = new Asteroid(49, 49, 300, -210, 20);
		worldWithObj.addObject(asteroid);
		
	}
	
	@Test
	public void testRemoveObject_NormalCase() {
		worldWithObj.removeObject(ship1);
		assertTrue(ship1.getWorld() == null);
		
		
	}
	
	@Test (expected=NullPointerException.class)
	public void testRemoveObject_Null() {
		Asteroid asteroid = null;
		worldWithObj.removeObject(asteroid);
	}
	
	// TO DO
	
	@Test
	public void evolve() {
		
	}
	
	
	
	
	

}
