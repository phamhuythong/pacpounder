package jamost.pacman;

import java.awt.Point;
import java.util.Observer;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Blinky extends Ghost implements Observer{
	/*
	 * The constructor will initialize the original position of the ghost on the
	 * map. It accepts one parameter, a point and calls the superclass
	 * constructor to initialize the point
	 */
	public Blinky(Point p) {
		super(p);
		maze = new Maze();
		pacman = new Point(1,1);
		super.setLastDirection(DOWN);
	}

	public void move(Point pacman) {
		/* Blinky will move in the most direct direction to catch pacman
		 * Blinky always finds a move to make. Be careful, this ghost is
		 * aggressive, he comes right at you.
		 */
		this.pacman = pacman;
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
