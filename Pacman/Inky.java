package jamost.pacman;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Inky extends Character implements Ghost, Observer{
	int lastMoveDirection = LEFT;
	Point pacman;
	Maze maze;
	
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
	}

	public void update(Observable o, Object arg1) {
		PacmanModel model = (PacmanModel) o;
		pacman = model.pacman.getPosition();
		move(pacman);
	}

	public void move(Point pacman) {
		//first attempt to continue moving in the same direction as the last move
		if (repeatLastMove()==true)
			return;
		
		/* Unable to move in the same direction as last time, now look for the next best move *****************************
		 * Attempts to move the ghost up in the Y direction first, then down, failing that attempts
		 * to move in the X direction, if this fails the ghost is trapped until pacman moves.
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
	/*
	 * Pinky's first move choice is to repeat the last 
	 * move he made. This method does just that.
	 */
	private boolean repeatLastMove() {
		int xGhost=(int) super.getPosition().getX();
		int yGhost=(int) super.getPosition().getY();
		if (lastMoveDirection == UP) {		//attempt to move up
			//if up is legal
			if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {
				super.setPosition(new Point(xGhost, yGhost-1)); //move up
				lastMoveDirection = UP;
				return true;
			}
			return false;
		}
		else if (lastMoveDirection == DOWN) {	//attempt to move down
			//if down is legal
			if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {
				super.setPosition(new Point(xGhost, yGhost+1)); //move down
				lastMoveDirection = DOWN;
				return true;
			}
			return false;
		}
		else if (lastMoveDirection == LEFT) {	//attempt to move left
			//if left is legal
			if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {
				super.setPosition(new Point(xGhost-1, yGhost)); //move left
				lastMoveDirection = LEFT;
				return true;
			}
			return false;
		}
		else {					//attempt to move right
			//if right is legal
			if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {
				super.setPosition(new Point(xGhost+1, yGhost)); //move right
				lastMoveDirection = RIGHT;
				return true;
			}
			return false;
		}
		
	}
	
	// Attempts to move the ghost in the Y direction, always tries to go up first
	private boolean moveYDirection() {
		int xGhost = super.getPosition().x;						//x position is unchanged
		int yGhost = super.getPosition().y;
		if ((yGhost - pacman.y) > 0) {									//pacman is above the ghost
			if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {	//2 indicates a wall
				super.setPosition(new Point(xGhost,yGhost-1));				//set the new position of the ghost, move up
				return true;												//return true
			}
			return false;	//if the move can't be made return false attempt to move in the X direction
		}	
		else {	//pacman is below the ghost
			if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {	//if its not a wall move there
				super.setPosition(new Point(xGhost,yGhost+1));				//set the new position of the ghost, move down
				return true;
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}
			
		
	}
	
	// Attempts to move the ghost in the X direction
	private boolean moveXDirection() {
		int xGhost=super.getPosition().x;
		int yGhost=super.getPosition().y;
		
		if ((xGhost - pacman.x) > 0) {									//pacman is to the left of the ghost
			if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {	//2 indicates a wall
				super.setPosition(new Point(xGhost-1,yGhost));				//set the new position of the ghost, move left
				lastMoveDirection = LEFT;
				return true;												//return true
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}	
		else {	//pacman is to the right of the ghost
			if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {	//if its not a wall move there
				super.setPosition(new Point(xGhost+1,yGhost));				//set the new position of the ghost, move right
				lastMoveDirection = RIGHT;
				return true;
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}
		
	}

}