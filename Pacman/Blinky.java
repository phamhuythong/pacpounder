package jamost.pacman;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

/* Public class Ghost extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Blinky extends Character implements Ghost, Observer{
	int lastMoveDirection = UP;
	Point pacman;
	Maze maze;
	//PacmanModel model;
	/*
	 * The constructor will initialize the original position of the ghost on the
	 * map. It accepts one parameter, a point and calls the superclass
	 * constructor to initialize the point
	 */
	public Blinky(Point p) {
		super(p);
		maze = new Maze();
		pacman = new Point(1,1);
	}

	public void update(Observable o, Object arg1) {
		PacmanModel model = (PacmanModel) o;
		pacman = model.pacman.getPosition();
		maze = model.maze;
		move(pacman);
	}
	
	/*public Ghost returnBlinky() {
		return this;		
	}*/

	//Blinky the smart ghost

	public void move(Point pacman) {
		/* Blinky will move in the most direct direction to catch pacman
		 * 
		 * If scatter mode is implemented, he may still pursue regardless
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

	private boolean moveYDirection() {
		int xGhost = super.getPosition().x;						//x position is unchanged
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
	
	// Attempts to move the ghost in the X direction
	private boolean moveXDirection() {
		int xGhost = super.getPosition().x;
		int yGhost = super.getPosition().y;
		
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


	public int getLastDirection() {
		return lastMoveDirection;
	}

}
