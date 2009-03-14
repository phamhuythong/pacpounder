package jamost.pacman;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Clyde extends Character implements Ghost, Observer{
	int lastMoveDirection=UP, number;
	Point pacman;
	Random random;
	Maze maze;
	/*
	 * The constructor will initialize the original position of the ghost on the
	 * map. It accepts one parameter, a point and calls the superclass
	 * constructor to initialize the point
	 * this ghost will move in somewhat random directions
	 * Either he is blind or not that smart
	 */
	public Clyde(Point p) {
		super(p);
		maze = new Maze();
		pacman = new Point(12,7);
		random = new Random();
	}

	public void update(Observable o, Object arg1) {
		PacmanModel model = (PacmanModel) o;
		pacman = model.pacman.getPosition();
		number = random.nextInt(4);
		if (number == 3)
			moveRandom();				
		else
			move(pacman);
		
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

	//Clyde somewhat random ghost
	/*
	 * The ghost must either receive pacmans position as a point
	 * or contain a copy of pacman, this is what they will base their
	 * movement on
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
	/*
	 * Clydes first move choice unless its random is to repeat the last
	 * direction that he moved in. This method does just that.
	 */
	private boolean repeatLastMove() {
		int xGhost = super.getPosition().x;
		int yGhost = super.getPosition().y;
		if (lastMoveDirection == UP) {		//attempt to move up
			//if up is legal
			if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {
				super.setPosition(new Point(xGhost, yGhost-1)); //move up
				return true;
			}
			return false;
		}
		else if (lastMoveDirection == DOWN) {	//attempt to move down
			//if down is legal
			if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {
				super.setPosition(new Point(xGhost, yGhost+1)); //move down
				return true;
			}
			return false;
		}
		else if (lastMoveDirection == LEFT) {	//attempt to move left
			//if left is legal
			if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {
				super.setPosition(new Point(xGhost-1, yGhost)); //move left
				return true;
			}
			return false;
		}
		else {					//attempt to move right
			//if right is legal
			if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {
				super.setPosition(new Point(xGhost+1, yGhost)); //move right
				return true;
			}
			return false;
		}
		
	}
	/*
	 * Attempts to move the ghost in the Y direction, failing
	 * that attempts to move in the X direction, if this fails
	 * the ghost is trapped until pacman moves.
	 */
	private boolean moveYDirection() {
		int xGhost = super.getPosition().x;
		int yGhost = super.getPosition().y;
		if ((yGhost - pacman.y) > 0) {									//pacman is above the ghost
			if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {	//2 indicates a wall
				super.setPosition(new Point(xGhost,yGhost-1));				//set the new position of the ghost, move up
				lastMoveDirection = UP;
				return true;												//return true
			}
			return false;	//if the move can't be made return false attempt to move in the X direction
		}	
		else {	//pacman is below the ghost
			if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {	//if its not a wall move there
				super.setPosition(new Point(xGhost,yGhost+1));				//set the new position of the ghost, move down
				lastMoveDirection = DOWN;
				return true;
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}
			
		
	}

	private boolean moveXDirection() {
		int xGhost = super.getPosition().x;
		int yGhost = super.getPosition().y;
		
		if ((xGhost - pacman.x) > 0) {									//pacman is to the left of the ghost
			if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {	//2 indicates a wall
				super.setPosition(new Point(xGhost-1,yGhost));				//set the new position of the ghost, move left
				lastMoveDirection = LEFT;
				return true;												//return true
			}
			return false;	//if the move can't be made return false attempt to move in the X direction
		}	
		else {	//pacman is to the right of the ghost
			if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {	//if its not a wall move there
				super.setPosition(new Point(xGhost+1,yGhost));				//set the new position of the ghost, move right
				lastMoveDirection = RIGHT;
				return true;
			}
			return false;	//if the move can't be made return false attempt to move in the X direction
		}
		
	}
	
	public int getLastDirection() {
		return lastMoveDirection;
	}

}
