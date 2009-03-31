package jamost.pacman;
/*
 * Ghost is an abstract class which Blinky, Clyde, Inky and Pinky extend
 * Constants common amung all subclasses and the abstract method
 * move() contained in the class
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */

import java.awt.Point;
import java.util.Observable;

public abstract class Ghost extends Character{
	protected static final int UP = 1;
	protected static final int DOWN = 2;
	protected static final int LEFT = 3;
	protected static final int RIGHT = 4;
	protected static final int WALL = 2;
	protected Maze maze;
	protected Point pacman;
	
	public Ghost(Point p) {
		super(p);
	}
	
	public abstract void move(Point p);

	protected boolean moveXDirection() {
		int xGhost = super.getPosition().x;
		int yGhost = super.getPosition().y;
		
		if ((xGhost - pacman.x) > 0) {									//pacman is to the left of the ghost
			if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {	//2 indicates a wall
				super.setLastPositionType(maze.getPosition(new Point(xGhost-1, yGhost)));
				super.setPosition(new Point(xGhost-1,yGhost));				//set the new position of the ghost, move left
				lastMoveDirection = LEFT;
				return true;												//return true
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}	
		else {	//pacman is to the right of the ghost
			if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {	//if its not a wall move there
				super.setLastPositionType(maze.getPosition(new Point(xGhost+1, yGhost)));
				super.setPosition(new Point(xGhost+1,yGhost));				//set the new position of the ghost, move right
				lastMoveDirection = RIGHT;
				return true;
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}
		
	}

	protected boolean moveYDirection() {
		int xGhost = super.getPosition().x;						//x position is unchanged
		int yGhost = super.getPosition().y;
		if ((yGhost - pacman.y) > 0) {									//pacman is above the ghost
			if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {	//2 indicates a wall
				super.setLastPositionType(maze.getPosition(new Point(xGhost, yGhost-1)));
				super.setPosition(new Point(xGhost,yGhost-1));				//set the new position of the ghost, move up
				lastMoveDirection = UP;
				return true;												//return true
			}
			return false;	//if the move can't be made return false attempt to move in the X direction
		}	
		else {	//pacman is below the ghost
			
			if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {	//if its not a wall move there
				super.setLastPositionType(maze.getPosition(new Point(xGhost, yGhost+1)));
				super.setPosition(new Point(xGhost,yGhost+1));				//set the new position of the ghost, move down
				lastMoveDirection = DOWN;
				
				return true;
			}
			return false;	//if the move can't be made return false attempt to move in the Y direction
		}
			
		
	}

	protected boolean repeatLastMove() {
		int xGhost=(int) super.getPosition().x;
		int yGhost=(int) super.getPosition().y;
		if (lastMoveDirection == UP) {		//attempt to move up
			//if up is legal
			if (maze.getPosition(new Point(xGhost, yGhost-1))!= WALL) {
				super.setLastPositionType(maze.getPosition(new Point(xGhost, yGhost-1)));
				super.setPosition(new Point(xGhost, yGhost-1)); //move up
				return true;
			}
			return false;
		}
		else if (lastMoveDirection == DOWN) {	//attempt to move down
			//if down is legal
			if (maze.getPosition(new Point(xGhost, yGhost+1))!= WALL) {
				super.setLastPositionType(maze.getPosition(new Point(xGhost, yGhost+1)));
				super.setPosition(new Point(xGhost, yGhost+1)); //move down
				return true;
			}
			return false;
		}
		else if (lastMoveDirection == LEFT) {	//attempt to move left
			//if left is legal
			if (maze.getPosition(new Point(xGhost-1, yGhost))!= WALL) {
				super.setLastPositionType(maze.getPosition(new Point(xGhost-1, yGhost)));
				super.setPosition(new Point(xGhost-1, yGhost)); //move left
				return true;
			}
			return false;
		}
		else {					//attempt to move right
			//if right is legal
			if (maze.getPosition(new Point(xGhost+1, yGhost))!= WALL) {
				super.setLastPositionType(maze.getPosition(new Point(xGhost+1, yGhost)));
				super.setPosition(new Point(xGhost+1, yGhost)); //move right
				return true;
			}
			return false;
		}
		
	}

	public void update(Observable o, Object arg1) {
		PacmanModel model = (PacmanModel) o;
		pacman = model.getPacmanPosition();
		maze = model.getMaze();
		move(pacman);
	}
	
}
