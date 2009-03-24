package jamost.pacman.test;
/*
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
import jamost.pacman.Ghost;
import jamost.pacman.Inky;
import jamost.pacman.PacmanModel;

import java.awt.Point;
import junit.framework.TestCase;

public class InkyTest extends TestCase {
	private Ghost inky;
	private PacmanModel model;

	protected void setUp() throws Exception {
		super.setUp();
		inky = new Inky(new Point(12,7));
		model = new PacmanModel();
	}

	public void testInky() {
		Ghost blinking = new Inky(new Point(5,5));
	}

	public void testUpdate() {//////******CHECK THIS***/////
		Point p = inky.getPosition();
		inky.update(model, model);
		assertTrue(p.equals(inky.getPosition()));
	}

	/*public void testReturnInky() {
		assertTrue(inky.returnInky() instanceof Inky);
	}*/

	public void testMove() {
		inky.move(model.getPacmanPosition());
		assertTrue(new Point(11,7).equals(inky.getPosition()));
	}

	public void testGetLastDirection() {
		inky.move(model.getPacmanPosition());
		assertEquals("3 represents left",3, inky.getLastDirection());
	}

	public void testGetPosition() {
		assertEquals(new Point(12,7), inky.getPosition());		
	}

	public void testSetPosition() {
		inky.setPosition(new Point(7,7));
		assertEquals(new Point(7,7), inky.getPosition());
	}

}