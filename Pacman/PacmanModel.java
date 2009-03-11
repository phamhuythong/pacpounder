package jamost.pacman;

import java.awt.Point;
import java.util.Observable;
/**
 * 
 * @author Steve Legere
 *
 */
public class PacmanModel extends Observable {
	private static final int UP		= 1;
	private static final int DOWN	= 2;
	private static final int LEFT	= 3;
	private static final int RIGHT	= 4;
	private static final int EMPTY_SPACE = 0;
	
	//private static final int DOT			= 1;
	private static final int WALL			= 2;
	
	Maze maze;
	Pacman pacman;
	private Pinky pinky;
	private Blinky blinky;
	private Inky inky;
	private Clyde clyde;
	private Score score;

	public PacmanModel() {
		maze = new Maze();
		pinky = new Pinky(new Point(12,7));
		blinky = new Blinky(new Point(12,7));
		clyde = new Clyde(new Point(12,7));
		inky = new Inky(new Point(12,7));
		pacman = new Pacman(new Point(12,13));
		score = new Score();
		
		addObserver(pinky);
		addObserver(blinky);
		addObserver(clyde);
		addObserver(inky);

	}

	public void move(int direction) {
		int x = pacman.getPosition().x;
		int y = pacman.getPosition().y;
		
		if (direction == UP) {
			System.out.println("up key was pressed");
			if (maze.getPosition(new Point(x,y-1))!= WALL) {
				pacman.setPosition(new Point(x,y-1));
			}
			updateScoreAndObservers();
		}
		else if (direction == DOWN) {
			System.out.println("DOWN key was pressed");
			if (maze.getPosition(new Point(x,y+1))!= WALL) {
				pacman.setPosition(new Point(x,y+1));
			}
			updateScoreAndObservers();
		}
		else if (direction == LEFT) {
			System.out.println("LEFT key was pressed");
			if (maze.getPosition(new Point(x-1,y))!= WALL) {
				pacman.setPosition(new Point(x-1,y));
			}
			updateScoreAndObservers();
		}
		else if(direction == RIGHT){
			System.out.println("RIGHT key was pressed");
			if (maze.getPosition(new Point(x+1,y))!= WALL) {
				pacman.setPosition(new Point(x+1,y));
			}
			updateScoreAndObservers();
		}
		maze.setPosition(pacman.getPosition(), EMPTY_SPACE);
		
	}
	/*
	 * This method simply updates the score, as well as notifying the observers of changes.
	 * Prior to notifying the observers, it also checks to see if pacman has won the game.
	 */
	private void updateScoreAndObservers() {
		score.incrementScore();
		winnerCheck();
		ghostsWin();				//check to see if pacman moved into a ghost
		setChanged();
		notifyObservers();		
		ghostsWin();				//check to see if the one of the ghosts caught pacman
		System.out.println(this.toString());
	}

	public boolean winnerCheck() {
		if (maze.getDots()==0)
			return true;
		return false;				//A method should be added to the maze to check for this
	}
	
	public boolean ghostsWin() {
		if (inky.getPosition().equals(pacman.getPosition())) {
			System.out.println("inky got you!, You lose!!");
			System.exit(0);
			return true;
		}
		if (pinky.getPosition().equals(pacman.getPosition())) {
			System.out.println("pinky got you!, You lose!!");
			System.exit(0);
			return true;
		}
		if (blinky.getPosition().equals(pacman.getPosition())) {
			System.out.println("blinky got you!, You lose!!");
			System.exit(0);
			return true;
		}
		if (clyde.getPosition().equals(pacman.getPosition())) {
			System.out.println("clyde got you!, You lose!!");
			System.exit(0);
			return true;
		}
		return false;
	}
	public String toString(){
		String Pinky = this.pinky.getPosition().toString();
		String Inky = this.inky.getPosition().toString();
		String Blinky = this.blinky.getPosition().toString();
		String Clyde= this.clyde.getPosition().toString();
		String Pacman = this.pacman.getPosition().toString();
		String Status = "The Status of the Game: \n";
		
		return Status + "Pacman's position " + Pacman + "\n" + "Pinky's postion " + Pinky + "\n" + "Inky's postion " + Inky + "\n"
		+ "Blinky's postion " + Blinky + "\n" + "Clyde's postion " + Clyde+"\n"+ maze.toString()+"\n-----------------------";
	}
}
