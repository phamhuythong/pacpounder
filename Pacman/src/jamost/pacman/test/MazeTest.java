package jamost.pacman.test;

/**
 * @author Steve Legere
 * @version 02.03.09
 */


import jamost.pacman.Maze;

import java.awt.Point;
import junit.framework.TestCase;

public class MazeTest extends TestCase {

	private static final int EMPTY_SPACE 	= 0;
	private static final int DOT 			= 1;
	private static final int WALL			= 2;
	private static final int COLS			= 25;
	private static final int ROWS			= 28;
	
	Maze maze;
	
	protected void setUp() {
		maze = new Maze();
	}
	/*
	public void testMaze() {
		
		int numberOfSpaces = 0;
		int numberOfDots = 0;
		int numberOfWalls = 0;
		
		for (int i = 0; i < COLS; ++i) {
			for (int j = 0; j < ROWS; ++j) {
				if (maze.getPosition(new Point(i,j)) == EMPTY_SPACE) ++numberOfSpaces;
				else if (maze.getPosition(new Point(i,j)) == DOT) ++numberOfDots;
				else ++numberOfWalls;
				
				assertTrue(maze.getPosition(new Point(i,j)) >= EMPTY_SPACE);
				assertTrue(maze.getPosition(new Point(i,j)) <= WALL);
				
			} //end for
		} // end for
		
		assertTrue(maze.getDots() == numberOfDots);
		assertTrue((COLS * ROWS) == numberOfSpaces + numberOfDots + numberOfWalls);
		assertTrue(maze.toString().equals("The maze currently has " + numberOfDots + " dots."));
	}
	*/
	public void testGetPosition() {
		assertTrue(maze.getPosition(new Point(0,0)) == WALL);
		assertFalse(maze.getPosition(new Point(12,13)) == WALL);
		assertFalse(maze.getPosition(new Point(12,7)) == EMPTY_SPACE);		
	}
	
	public void testSetPosition() {
		maze.setPosition(new Point(0,0), EMPTY_SPACE);
		assertFalse(maze.getPosition(new Point(0,0)) == WALL);
		assertFalse(maze.getPosition(new Point(0,0)) == DOT);
		assertTrue(maze.getPosition(new Point(0,0)) == EMPTY_SPACE);
	}
}
