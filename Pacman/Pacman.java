package jamost.pacman;

import java.awt.Point;
/* Public class Pacman extends the Character class.
 * 
 * @author Jason Brown
 * @ version 2.0 March 1, 2009.
 */
public class Pacman extends Character {
	/*
	 * The constructor will initialize the original position of the
	 * ghost on the map. It accepts one parameter, a point and calls
	 * the superclass constructor to initialize the point
	 */
	public Pacman(Point p) {
		super(p);
	}

}

