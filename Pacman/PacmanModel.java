package jamost.pacman;

import java.util.Observable;
/*
 * The Model for pacman MVC design it is the core
 * of the game.
 * 
 * @author Mohamed Elzayat, Jason Brown
 * @ version 1.0 February 16, 2009.
 */
public class PacmanModel extends Observable{
	
	// Creating fields
	private Maze maze;
	private Pacman pacman;
	private Ghost ghost;
	private Score score;
	
	// constructor for the model initialize the fields
	public PacmanModel(){
		
	}
	// impliments what will happen when a user
	// press the upArrow key on the keyboard
	public void moveUp(){
		
	}
	// impliments what will happen when a user
	// press the downArrow key on the keyboard
	public void moveDown(){
		
	}
	// impliments what will happen when a user
	// press the leftArrow key on the keyboard
	public void moveLeft(){
		
	}
	// impliments what will happen when a user
	// press the rightArrow key on the keyboard
	public void moveRight(){
		
	}
	// checks for 3 cases, 1. packman ate all the dots
	// 2. the ghost caught packman. 3. game is still going
	public Boolean winnerCheck(){
		return false;
	}
}
