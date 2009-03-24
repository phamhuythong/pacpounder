package jamost.pacman.test;
/*
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
import jamost.pacman.Pinky;
import jamost.pacman.PacmanModel;

import java.awt.Point;
import junit.framework.TestCase;

public class PinkyTest extends TestCase {
	private Pinky pinky;
	private PacmanModel model;

	protected void setUp() throws Exception {
		super.setUp();
		pinky = new Pinky(new Point(12,7));
		model = new PacmanModel();
	}

	public void testPinky() {
		Pinky blinking = new Pinky(new Point(5,5));
	}

	public void testUpdate() {//////******CHECK THIS***/////
		Point p = pinky.getPosition();
		pinky.update(model, model);
		assertTrue(p.equals(pinky.getPosition()));
	}

	/*public void testReturnPinky() {
		assertTrue(pinky.returnPinky() instanceof Pinky);
	}*/

	public void testMove() {
		pinky.move(model.getPacmanPosition());
		assertTrue(new Point(13,7).equals(pinky.getPosition()));
	}

	public void testGetLastDirection() {
		pinky.move(model.getPacmanPosition());
		assertEquals("4 represents right",4, pinky.getLastDirection());
	}

	public void testGetPosition() {
		assertEquals(new Point(12,7), pinky.getPosition());		
	}

	public void testSetPosition() {
		pinky.setPosition(new Point(7,7));
		assertEquals(new Point(7,7), pinky.getPosition());
	}

}