package jamost.pacman;

import java.awt.Point;
import java.util.Observer;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Pinky extends Ghost implements Observer{
	/*
	 * The constructor will initialize the original position of the ghost on the
	 * map. It accepts one parameter, a point and calls the superclass
	 * constructor to initialize the point
	 * this ghost attempts to continue in its last direction, when it runs into
	 * a wall, it moves from vertical movement to horizontal or vice versa
	 */
	public Pinky(Point p) {
		super(p);
		maze = new Maze();
		pacman = new Point(1,1);
		super.lastMoveDirection = RIGHT;
	}

	public void move(Point pacman) {
		//first attempt to continue moving in the same direction as the last move
		if (repeatLastMove())
			return;
		
		/* Unable to move in the same direction as last time, now look for the next best move
		 * Attempts to move the ghost up in the Y direction first, failing that attempts
		 * to move in the X direction, Pinky always finds a move to make.
		 */
		
		int xDifference = Math.abs(super.getPosition().x - pacman.x);
		int yDifference = Math.abs(super.getPosition().y - pacman.y);
		if (xDifference > yDifference) {
			if (!(moveXDirection()))
				moveYDirection();
		}
		else {
			if (!(moveYDirection()))
				moveXDirection();
		}

	}
	

}
