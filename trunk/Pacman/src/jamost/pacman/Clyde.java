package jamost.pacman;

import java.awt.Point;
import java.util.Observer;
import java.util.Random;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Clyde extends Ghost implements Observer{
	private int number;
	private Random random;

	/*
	 * The constructor will initialize the original position of the ghost on the
	 * map. It accepts one parameter, a point and calls the superclass
	 * constructor to initialize the point
	 * this ghost will move in somewhat random directions which can cause pacman
	 */
	public Clyde(Point p) {
		super(p);
		maze = new Maze();
		pacman = new Point(12,7);
		random = new Random();
		super.setLastDirection(UP);
	}

	private void moveRandom() {
		boolean success = false;
		int xGhost = super.getPosition().x;
		int yGhost = super.getPosition().y;
		while (! success ) {
			number = random.nextInt(3);
			if (number == 0) {		//attempt to move up
									//if up is legal
				if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {
					super.setPosition(new Point(xGhost, yGhost-1)); //move up
					lastMoveDirection = UP;
					success = true;
				}
			}
			else if (number == 1) {	//attempt to move down
				//if down is legal
				if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {
					super.setPosition(new Point(xGhost, yGhost+1)); //move down
					lastMoveDirection = DOWN;
					success = true;
				}
			}
			else if (number == 2) {	//attempt to move left
				//if left is legal
				if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {
					super.setPosition(new Point(xGhost-1, yGhost)); //move left
					lastMoveDirection = LEFT;
					success = true;
				}
			}
			else {					//attempt to move right
				//if right is legal
				if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {
					super.setPosition(new Point(xGhost+1, yGhost)); //move right
					lastMoveDirection = RIGHT;
					success = true;
				}
			}
		}
		
	}

	/*Aside from being trapped, Clyde will give up not always make a move.
	 * Like two of his companions, he always tries to repeat his last move.
	 * Failing that, if his last move was left or right, he attempts a vertical
	 * move. If his last move was vertical, he will attempt to move horizontally.
	 */
	public void move(Point pacman) {
		//first attempt to continue moving in the same direction as the last move
		if (repeatLastMove()==true)
			return;
		
		//Unable to move in the same direction as last time, now look for the next best move
		
		if ((lastMoveDirection == LEFT) || (lastMoveDirection == RIGHT))
			moveYDirection();
		else
			moveXDirection();
	}

}
