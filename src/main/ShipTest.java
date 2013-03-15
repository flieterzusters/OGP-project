package main;

import static org.junit.Assert.*;

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
	public void constructorIllegalSpeed() {
		Ship newShip = new Ship(10, 10, 250000, 250000, 15, 0);
		assertTrue ("Speed has not been adjusted properly", Util.fuzzyLessThanOrEqualTo(newShip.getVelocity(), newShip.getSpeedLimit() ));
	}
	
	@Test
	public void constructorIllegalRadius() {
		
		try {
			Ship newShip = new Ship(10, 10, 250000, 250000, 5, 0);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thrown) {
			assertThat(thrown.getMessage(), Is.is("The provided radius is too small."));
		}
	}
}
