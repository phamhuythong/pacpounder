package jamost.pacman;

import java.awt.Point;

/* Maze is based on 2 dimensional integer array. This class stores
 * the information related to the board, empty space, dots and walls.
 * Currently it contains 28 rows and 25 spaces.
 * 
 * @author Mohamed Elzayat, Jason Brown
 * @ version 1.0 February 16, 2009.
 */
public class Maze {
	private static final int EMPTY_SPACE = 0;
	private static final int DOT = 1;
	private static final int WALL = 2;
	private static final int ROWS = 28;
	private static final int COLS = 25;
	int[][] map;

	/*
	 * The constructor initializes the array to its default size and assigns
	 * each element its appropriate value.
	 */

	public Maze() {
		map = new int[ROWS][COLS];
		// assign values to elements
		// first assign dots to all spaces
		// next replace dots with walls
	}

	/*
	 * Accepts a point as a parameter and returns an integer value indicating
	 * what is located at the position in the array.
	 */
	public int getPosition(Point p) {
		return 0;
	}

	/*
	 * Updates the value assigned to positions in the array, returns a boolean
	 * value on success or failure
	 */
	public Boolean setLocation(Point p, int i) {
		return false;
	}

	// Gets the location of pacman
	public Point getPacman() {
		return new Point();
	}
}
