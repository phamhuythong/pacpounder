package jamost.pacman;

import java.awt.Point;
/* Public class Pacman extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Pacman extends Character {
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;
	static final int WALL = 2;
	/*
	 * The constructor will initialize the original position of the
	 * ghost on the map. It accepts one parameter, a point and calls
	 * the superclass constructor to initialize the point
	 */
	public Pacman(Point p) {
		super(p);
		super.setLastDirection(LEFT);
	}
	
	public void pacmanMove(int direction, Maze maze) {
		int x = super.getPosition().x;
		int y = super.getPosition().y;
		
		if (direction == UP) {
			super.setLastDirection(UP);
			//System.out.println("up key was pressed");
			if (maze.getPosition(new Point(x,y-1))!= WALL) {
				super.setPosition(new Point(x,y-1));
			}
		}
		else if (direction == DOWN) {
			super.setLastDirection(DOWN);
			//System.out.println("DOWN key was pressed");
			if (maze.getPosition(new Point(x,y+1))!= WALL) {
				super.setPosition(new Point(x,y+1));
			}
		}
		else if (direction == LEFT) {
			super.setLastDirection(LEFT);
			//System.out.println("LEFT key was pressed");
			if (maze.getPosition(new Point(x-1,y))!= WALL) {
				super.setPosition(new Point(x-1,y));
			}
		}
		else if(direction == RIGHT){
			super.setLastDirection(RIGHT);
			//System.out.println("RIGHT key was pressed");
			if (maze.getPosition(new Point(x+1,y))!= WALL) {
				super.setPosition(new Point(x+1,y));
			}
		}
	}

}

