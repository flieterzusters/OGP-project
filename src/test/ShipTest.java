package test;

import static org.junit.Assert.*;

import main.Ship;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hamcrest.core.*;

import asteroids.Util;

public class ShipTest {
	
	public static Ship defaultShip;
	public static Ship staticShip1;
	public static Ship staticShip2;
	public static Ship dynamicShip1;
	public static Ship dynamicShip2;
	

	@BeforeClass
	public static void setUpBeforeClass()  {
		defaultShip = new Ship();
		staticShip1 = new Ship(100, 200, 10000, 20000, 15, 0);
		staticShip2 = new Ship(-100, -200, -10000, -20000, 25, 1.75);
	}

	@Before
	public void setUp()  {
		dynamicShip1 = new Ship(50, 2000, 10, 5, 20, 1);
		dynamicShip2 = new Ship(-30.5, -20.1,-100, 200, 15, 3);
		
	}

	@Test
	public void constructorIllegalSpeedTest() {
		Ship newShip = new Ship(10, 10, 250000, 250000, 15, 0);
		assertTrue ("Speed has not been adjusted properly", Util.fuzzyLessThanOrEqualTo(newShip.getVelocity(), newShip.getSpeedLimit() ));
	}
	
	@Test
	public void constructorIllegalRadiusTest() {
		
		try {
			@SuppressWarnings("unused")
			Ship newShip = new Ship(10, 10, 250000, 250000, 5, 0);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thrown) {
			assertThat(thrown.getMessage(), Is.is("The provided radius is too small."));
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
}
