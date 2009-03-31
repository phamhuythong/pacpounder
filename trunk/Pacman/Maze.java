package jamost.pacman;

import java.awt.Point;
import java.io.Serializable;

/**
 * 
 * @author Steve Legere
 *
 */
public class Maze implements Serializable{
	private static final int EMPTY_SPACE 	= 0;
	private static final int DOT 			= 1;
	private static final int WALL			= 2;
	private static final int COLS			= 25;
	private static final int ROWS			= 28;

	private int[][] map;
	private int numberOfDots = 0;
	
	public Maze() {	
		map = new int[ROWS][COLS];
	}

	public int getPosition(Point p) {
		return map[p.y][p.x];
	}

	/*
	 * If a space or wall is changed to a dot, increment the number of
	 * dots. If a dot is removed, decrement the number of dots.
	 */
	public Boolean setPosition(Point p, int element) {
		/* Check if we're creating a dot. If so, increment the count. */
		if (element == DOT) {
			if (map[p.y][p.x] == WALL || map[p.y][p.x] == EMPTY_SPACE) {
				++numberOfDots;
			}
		}
		/* Check if we're removing a dot. If so, decrement the count. */
		if (element == WALL || element == EMPTY_SPACE) {
			if (map[p.y][p.x] == DOT) {
				--numberOfDots;
			}
		}
		/* Change the array element. */
		map[p.y][p.x] = element;
		return true;
	}
	
	public int getDots() {
		return numberOfDots;
	}
	
	public String toString(){
		return "The maze currently has " + getDots() + " dots.";
	}
	public int[][] getArray(){
		return map;
	}
	

	public void incrementNumberOfDots() {
		numberOfDots++;
	}

	public void decrementDots() {
		numberOfDots--;
	}
}
