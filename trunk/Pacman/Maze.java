package jamost.pacman;

import java.awt.Point;

/**
 * 
 * @author Steve Legere
 *
 */
public class Maze {
	//private static final int EMPTY_SPACE 	= 0;
	private static final int DOT 			= 1;
	private static final int WALL			= 2;
	private static final int COLS			= 25;
	private static final int ROWS			= 28;

	private int[][] map;
	private int numberOfDots = 0;
	
	public Maze() {
		
		map = new int[ROWS][COLS];

		for (int i = 0; i < COLS; ++i) {
			for (int j = 0; j < ROWS; ++j) {
				
				/* Sets all array indices to dots. */
				setPosition(new Point(i,j), DOT);
				++numberOfDots;
				
				/* Check if at an edge. If so, change the element ID. */
				if (i == 0 || i == COLS-1 || j == 0 || j == ROWS-1) {
					if (getPosition(new Point(i,j)) != WALL) {
						setPosition(new Point(i,j), WALL);
						--numberOfDots;
					}
				}
				/* Make the 11 top and bottom wall columns. */
				if (((j >= 2 && j <= 5) || (j >= 14 && j <= 17)) && (i % 2 == 0)) {
					setPosition(new Point(i,j), WALL);
					--numberOfDots;
				}
				/* Make the wall rows at rows 8 and 11. */
				if ((j == 8 || j == 11) && ((i >= 2  && i <= 10) || (i >= 14  && i <= 22))) {
					setPosition(new Point(i,j), WALL);
					--numberOfDots;
				}
				/* Make the bottom 3 rows of walls. */
				if ((j == 20 || j == 22 || j == 24) && (i >= 6 && i <= 18)) {
					setPosition(new Point(i,j), WALL);
					--numberOfDots;
				}
			} // end for
		} // end for
	}

	public int getPosition(Point p) {
		return map[p.y][p.x];
	}

	public Boolean setPosition(Point p, int element) {
		map[p.y][p.x] = element;
		return true;
	}
	
	public int getDots() {
		return numberOfDots;
	}
	
	public String toString(){
		return "The maze currently has " + getDots() + " dots.";
	}
}
