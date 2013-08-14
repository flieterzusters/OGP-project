package asteroids.test;

import static org.junit.Assert.*;
import org.junit.*;
import java.lang.Math;
import asteroids.model.*;
import org.hamcrest.core.*;

import asteroids.Util;

public class ShipTest {
	
	
	private static Ship staticShip1;
	private static Ship staticShip2;
	private static Ship staticShip3;
	private static Ship staticShip4;
	private static Ship dynamicShip1;
	private static Ship dynamicShip2;
	private static Ship collisionShip1;
	private static World testWorld;

	
	@Test (expected=IllegalArgumentException.class)
	public void moveTest_IllegalCase() throws IllegalArgumentException {
		dynamicShip1.move(Math.sqrt(-1));
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
	
	@Test (expected=NullPointerException.class)
	public void fireBullet_Null() {
		staticShip1.fireBullet();
		
	}
	
	@Test
	public void fireInWorld() {
		testWorld.addObject(dynamicShip1);
		dynamicShip1.fireBullet();
	}
}
