package jamost.pacman;
/*
 * Ghost is an interface which Blinky, Clyde, Inky and Pinky Implement
 * Constants common amung all subclasses and the abstract method
 * move() contained in the interface
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */

import java.awt.Point;

public interface Ghost {
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int WALL = 2;
	
	public abstract void move(Point p);
}
