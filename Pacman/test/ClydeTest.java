package jamost.pacman.test;
/*
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
import jamost.pacman.Clyde;
import jamost.pacman.PacmanModel;

import java.awt.Point;
import junit.framework.TestCase;

public class ClydeTest extends TestCase {
	private Clyde clyde;
	private PacmanModel model;

	protected void setUp() throws Exception {
		super.setUp();
		clyde = new Clyde(new Point(12,7));
		model = new PacmanModel();
	}

	public void testClyde() {
		Clyde blinking = new Clyde(new Point(5,5));
	}

	public void testUpdate() {//////******CHECK THIS***/////
		Point p = clyde.getPosition();
		clyde.update(model, model);
		assertTrue(p.equals(clyde.getPosition()));
	}

	/*public void testReturnClyde() {
		assertTrue(clyde.returnClyde() instanceof Clyde);
	}*/

	public void testMove() {
		clyde.move(model.getPacmanPosition());
		assertTrue(new Point(12,6).equals(clyde.getPosition()));
	}

	public void testGetLastDirection() {
		clyde.move(model.getPacmanPosition());
		assertEquals("1 represents up",1, clyde.getLastDirection());
	}

	public void testGetPosition() {
		assertEquals(new Point(12,7), clyde.getPosition());		
	}

	public void testSetPosition() {
		clyde.setPosition(new Point(7,7));
		assertEquals(new Point(7,7), clyde.getPosition());
	}

}