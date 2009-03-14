package jamost.pacman.test;
/*
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
import jamost.pacman.Blinky;
import jamost.pacman.PacmanModel;

import java.awt.Point;
import junit.framework.TestCase;

public class BlinkyTest extends TestCase {
	private Blinky blinky;
	private PacmanModel model;

	protected void setUp() throws Exception {
		super.setUp();
		blinky = new Blinky(new Point(12,7));
		model = new PacmanModel();
	}

	public void testBlinky() {
		Blinky blinking = new Blinky(new Point(5,5));
	}

	public void testUpdate() {//////******CHECK THIS***/////
		Point p;
		p = blinky.getPosition();
		blinky.update(model, model);
		assertTrue(p.equals(blinky.getPosition()));
	}

	/*public void testReturnBlinky() {
		assertTrue(blinky.returnBlinky() instanceof Blinky);
	}*/

	public void testMove() {
		Point p = model.getPacmanPosition();
		System.out.println("Pacmans position" + p);
		blinky.move(p);
		
		System.out.println(blinky.getPosition());
		assertTrue(new Point(12,8).equals(blinky.getPosition()));
	}

	public void testGetLastDirection() {
		blinky.move(model.getPacmanPosition());
		assertEquals("2 represents down",2, blinky.getLastDirection());
	}

	public void testGetPosition() {
		assertEquals(new Point(12,7), blinky.getPosition());		
	}

	public void testSetPosition() {
		blinky.setPosition(new Point(9,9));
		assertEquals(new Point(9,9), blinky.getPosition());
	}

}