package jamost.pacman;

import java.awt.Point;
import java.util.Observer;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Inky extends Ghost implements Observer{
	/*
	 * The constructor will initialize the original position of the ghost on the
	 * map. It accepts one parameter, a point and calls the superclass
	 * constructor to initialize the point
	 * this ghost attempts to continue in its last direction, when it runs into
	 * a wall, it moves from vertical movement to horizontal or vice versa
	 */
	public Inky(Point p) {
		super(p);
		maze = new Maze();
		pacman = new Point(1,1);
		super.setLastDirection(LEFT);
	}

	public void move(Point pacman) {
		//first attempt to continue moving in the same direction as the last move
		if (repeatLastMove()==true)
			return;
		
		/* Unable to move in the same direction as last time, now look for the next best move
		 * Attempts to move the ghost up in the Y direction first, failing that attempts
		 * to move in the X direction, Inky will always find a move to make
		 */
		
		if ((lastMoveDirection == LEFT) || (lastMoveDirection == RIGHT)) {
			if (!(moveYDirection()))
				moveXDirection();
		}
		else {
			if (!(moveXDirection()))
				moveYDirection();
		}

	}

}