package asteroids.test;

import static org.junit.Assert.*;
import java.lang.Math;

import asteroids.model.Ship;

import org.junit.*;
import org.hamcrest.core.*;

import asteroids.Util;

public class ShipTest {
	
	private static final double SPEEDOFLIGHT = 300000;
	private static Ship staticShip1;
	private static Ship staticShip2;
	private static Ship dynamicShip1;
	private static Ship dynamicShip2;
	private static Ship staticShip3;
	private static Ship staticShip4;
	private static Ship collisionShip1;

	

	@BeforeClass
	public static void setUpBeforeClass()  {
		
		staticShip1 = new Ship(100, 200, 10000, 20000, 15, 0, 100);
		staticShip2 = new Ship(-100, -200, -10000, -20000, 25, 1.75,50);
		staticShip3 = new Ship(-100, -235, 0, 0, 10, 0,70);	
		staticShip4 = new Ship(-105, -230, 0, 0, 10, 0,300);
		collisionShip1 = new Ship(500, 500, -20, -20, 10, 0, 700);
		
		
	}
	@Before
	public void setUp()  {
		dynamicShip1 = new Ship(50, 2000, 10, 5, 20, 1,2);
		dynamicShip2 = new Ship(-30.5, -20.1,-100, 200, 15, 3, 55);
	
		
	}

	@Test
	public void constructorIllegalSpeedTest() {
		Ship newShip = new Ship(10, 10, 250000, 250000, 15, 0, 50);
		assertTrue ("Speed has not been adjusted properly", Util.fuzzyLessThanOrEqualTo(newShip.getVelocity(), newShip.getSpeedLimit() ));
	}
	
	@Test
	public void constructorIllegalRadiusTest() {
		
		try {
			@SuppressWarnings("unused")
			Ship newShip = new Ship(10, 10, 250000, 250000, 5, 0, 30);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thrown) {
			assertThat(thrown.getMessage(), Is.is("The provided radius is either not a number or is too small."));
		}
	}
	
	@Test 
	public void getXTest() {		
		assertEquals("Expected a value of 100 for staticShip1", 100 ,staticShip1.getX(),Util.EPSILON);
		assertEquals("Expected a value of -100 for staticShip2", -100, staticShip2.getX(), Util.EPSILON);
	}
	
	@Test
	public void getYTest() {
		assertEquals("200 Expected for staticShip1", 200, staticShip1.getY(), Util.EPSILON);
		assertEquals("-200 Expected for StaticShip2", -200, staticShip2.getY(), Util.EPSILON);
	}
	
	@Test
	public void moveTest_NormalCase() {
		dynamicShip1.move(10.135);
		assertTrue(Util.fuzzyEquals(dynamicShip1.getX(), 151.350 ));
		assertTrue(Util.fuzzyEquals(dynamicShip1.getY(), 2050.675 ));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void moveTest_IllegalCase() throws IllegalArgumentException {
		dynamicShip1.move(Math.sqrt(-1));
	}
	
	@Test
	public void getXVelocityTest() {
		assertEquals("expected a value of 10", 10, dynamicShip1.getXVelocity(), Util.EPSILON);
	}
	
	@Test
	public void getYVelocityTest() {
		assertEquals("expected a value of 5", 5, dynamicShip1.getYVelocity(), Util.EPSILON);
	}
	
	@Test
	public void thrustTest_LegalCase() {
		dynamicShip1.thrust(50) ;
		assertEquals("A value of approx. 37.015115 is expected", 37.015115, dynamicShip1.getXVelocity(), Util.EPSILON)	;
		assertEquals("A value of approx. 47.07355 is expected", 47.07355, dynamicShip1.getYVelocity(),Util.EPSILON);
		
	}
	
	@Test 
	public void thrustTest_SpeedLimitExceeded() {
		dynamicShip2.thrust(300000);
		assertEquals("A value of approx. -296971.7542 is expected", -296971.7542, dynamicShip2.getXVelocity(), Util.EPSILON);
		assertEquals("A value of approx. 42517.96 is expected", 42517.96350, dynamicShip2.getYVelocity(), Util.EPSILON);
		assertTrue("The speed is not aprox. equal to the speedlimit", Util.fuzzyEquals(dynamicShip2.getVelocity(), dynamicShip2.getSpeedLimit()));
	}
	
	@Test
	public void getRadiusTest() {
		assertEquals("A value of 25 was expected", 25, staticShip2.getRadius(), Util.EPSILON);
	}
	
	@Test
	public void getDirectionTest() {
		assertEquals("A value of 1.75 was expected", 1.75, staticShip2.getDirection(), Util.EPSILON);
	}
	
	@Test
	public void turnTest_NormalCase() {
		dynamicShip1.turn(1);
		assertEquals("The angle should be 2", 2, dynamicShip1.getDirection() , Util.EPSILON);
	}
	
	@Test
	public void turnTest_AngleOverflow() {
		dynamicShip2.turn(6);
		assertEquals("The angle should be 2.71681", 2.71681, dynamicShip2.getDirection() , Util.EPSILON);
	}
	
	@Test
	public void distanceBetweenTest_ZeroDistance() {
		assertEquals("Expected a distance of zero", 0, staticShip2.getDistanceBetween(staticShip3), Util.EPSILON);
	}
	
	@Test
	public void distanceBetweenTest_PosDistance() {
		assertEquals("Expected a distance of approx. 407.213595", 407.213595, staticShip2.getDistanceBetween(staticShip1), Util.EPSILON);
	}
	
	@Test
	public void distanceBetweenTest_NegDistance() {
		assertEquals("Expected a distance of approx. -12.9289 ", -12.9289 ,staticShip3.getDistanceBetween(staticShip4) ,Util.EPSILON);
	}
		
	@Test
	public void getTimeToCollisionTest_LegalCase() {
		assertEquals("wrong time", 24.292893, collisionShip1.getTimeToCollision(staticShip1), Util.EPSILON);
	}
	
	public void getTimeToCollisionTest_NoCollision() {
		assertEquals("Expectedd Double.POSITIVE_INFINITY", Double.POSITIVE_INFINITY, collisionShip1.getTimeToCollision(dynamicShip1), Util.EPSILON);
	}
	
	@Test
	public void getCollisionPositionTest_NotNull() {
		double[] array = collisionShip1.getCollisionPosition(staticShip1);
		assertEquals("Wrong x-coordinate",14.142140, array[0], Util.EPSILON);
		assertEquals("Wrong y-coordinate",14.142140, array[1], Util.EPSILON);
	}
	
	@Test
	public void getCollisionPositionTest_Null() {
		double[] array = collisionShip1.getCollisionPosition(dynamicShip1);
		assertTrue("Expected True", array == null);
	}
}
