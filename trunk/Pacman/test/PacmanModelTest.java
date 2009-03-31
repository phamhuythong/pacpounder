package jamost.pacman.test;

import java.awt.Point;

import jamost.pacman.PacmanModel;
import junit.framework.TestCase;

public class PacmanModelTest extends TestCase {
	private static final int UP		     = 1;
	private static final int DOWN		 = 2;
	private static final int LEFT		 = 3;
	private static final int RIGHT		 = 4;
	private static final int EMPTY_SPACE = 0;
	private static final int DOT 		 = 1;
	private static final int WALL		 = 2;
	PacmanModel pm;

	protected void setUp() throws Exception {
		super.setUp();
		pm = new PacmanModel();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPacmanModel() {
		pm = new PacmanModel();
	}

	public void testGetLives() {
		assertEquals(3, pm.getLives());
	}

	public void testGetLevel() {
		assertEquals(1, pm.getLevel());
	}

	public void testMazeGetDots() {
		assertEquals(246,pm.mazeGetDots());
		pm.move(RIGHT);
		assertEquals(245,pm.mazeGetDots());
	}

	public void testGetGhostDirection() {
		pm.move(RIGHT);
		System.out.println(pm.getGhostDirection("Clyde"));
		assertEquals(UP, pm.getGhostDirection("Clyde"));
	}

	public void testResetCharacterPositions() {
		pm.move(RIGHT);
		assertEquals(new Point(13,13),pm.getPacmanPosition());
		assertEquals(new Point(12,8),pm.getBlinky());
		assertEquals(new Point(11,7),pm.getInky());
		assertEquals(new Point(13,7),pm.getPinky());
		assertEquals(new Point(12,6),pm.getClyde());
		pm.resetCharacterPositions();
		assertEquals(new Point(12,13),pm.getPacmanPosition());
		assertEquals(new Point(12,7),pm.getBlinky());
		assertEquals(new Point(12,7),pm.getInky());
		assertEquals(new Point(12,7),pm.getPinky());
		assertEquals(new Point(12,7),pm.getClyde());
	}

	public void testMove() {
		assertEquals(new Point(12,13),pm.getPacmanPosition());
		pm.move(RIGHT);
		assertEquals(new Point(13,13),pm.getPacmanPosition());
		pm.move(DOWN);
		assertEquals(new Point(13,14),pm.getPacmanPosition());
		pm.move(RIGHT);pm.move(RIGHT);
		pm.move(DOWN);pm.move(DOWN);
		pm.move(LEFT);
		assertEquals(new Point(14,16),pm.getPacmanPosition());
	}

	public void testGetPacmanPosition() {
		pm.move(RIGHT);
		assertEquals(new Point(13,13),pm.getPacmanPosition());
	}

	public void testGetPinky() {
		assertEquals(new Point(12,7),pm.getPinky());
	}

	public void testGetInky() {
		pm.move(LEFT);
		assertEquals(new Point(11,7),pm.getInky());
	}

	public void testGetBlinky() {
		assertEquals(new Point(12,7),pm.getBlinky());
	}

	public void testGetClyde() {
		assertEquals(new Point(12,7),pm.getClyde());
	}

	public void testGetScore() {
		assertEquals(0,pm.getScore());
		pm.move(RIGHT);
		assertEquals(10,pm.getScore());
	}

	public void testGetPacmansLastMove() {
		pm.move(RIGHT);
		assertEquals(RIGHT,pm.getPacmansLastMove());
	}

	public void testGetCharactersLastPosition() {
		pm.move(RIGHT);
		assertEquals(new Point(12,7),pm.getCharactersLastPosition("pinky"));
		pm.move(DOWN);
		assertEquals(new Point(13,7),pm.getCharactersLastPosition("pinky"));
	}

	public void testGetLastPositionType() {
		pm.move(RIGHT);
		assertEquals(EMPTY_SPACE,pm.getLastPositionType("blinky"));
	}

	public void testIsCharacterReset() {
		assertEquals(false, pm.isCharacterReset());
		pm.setCharacterReset(true);
		assertEquals(true, pm.isCharacterReset());
	}

}
