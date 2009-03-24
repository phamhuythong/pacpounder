package jamost.pacman;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Observable;
/**
 * 
 * @author Steve Legere, Jason Brown
 *
 * Class PacmanModel
 * 
 * This class is the model for the pacman game. It contains
 * the maze, pacman and the ghosts. It is an observable class
 * and is observed by the ghosts.
 */
public class PacmanModel extends Observable  implements Serializable{
	private static final int EMPTY_SPACE = 0;
	private static final int UP	= 1;
	private static final int DOWN	= 2;
	private static final int LEFT	= 3;
	private static final int RIGHT	= 4;
	
	private static final int WALL		 = 2;
	private static final int NO_WINNER   = 0;
	private static final int PACMAN_WINS = 1;
	private static final int GHOSTS_WIN  = 2;
	
	private Maze maze;
	private Pacman pacman;
	private Pinky pinky;
	private Blinky blinky;
	private Inky inky;
	private Clyde clyde;
	private Score score;

	/* The constructor simply creates the maze, ghosts,
	 * pacman and the score. It also registers all the
	 * ghosts as observers of the model
	 */
	public PacmanModel() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("lll.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		maze = new Maze();
		if (object instanceof Maze)
				maze = (Maze) object;
		fis.close();
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
	
	/* This method returns accepts a String parameter
	 * of a ghosts name and returns a integer value 
	 * corresponding the last direction that ghost moved
	 * in. -1 if a bad string was passed.
	 */
	public int getGhostDirection(String ghost) {
		if (ghost.equals("Blinky"))
			return blinky.getLastDirection();
		else if (ghost.equals("Clyde"))
			return clyde.getLastDirection();
		else if (ghost.equals("Inky"))
			return inky.getLastDirection();
		else if (ghost.equals("Pinky"))
			return pinky.getLastDirection();
		return -1;
	}
	
	/* This method is called to end the game */
	public void exitGame() {
		System.exit(0);
	}
	
	/* This method is called to reset the game */
	public void resetGame() {
		resetCharacterPositions();
		maze = new Maze();
		score.setScore(0);
	}
	
	/* This method is called to reset the position of
	 * all characters, pacman and the ghosts
	 */
	public void resetCharacterPositions() {
		pinky.setPosition(new Point(12,7));
		inky.setPosition(new Point(12,7));
		blinky.setPosition(new Point(12,7));
		clyde.setPosition(new Point(12,7));
		pacman.setPosition(new Point(12,13));
	}

	/* This method move confirms whether a requested move
	 * for pacman is a valid move. If it is, it updates
	 * pacmans position 
	 */
	public void move(int direction) {
		pacman.pacmanMove(direction, maze);
		updateScoreAndObservers();
		maze.setPosition(pacman.getPosition(), EMPTY_SPACE);
		
	}
	/*
	 * This method simply updates the score, as well as notifying the observers of changes.
	 * Prior to notifying the observers, it also checks to see if pacman has won the game.
	 */
	private void updateScoreAndObservers() {
		score.incrementScore();
		checkForWinners();				//see if pacman has won or moved into a ghost
		setChanged();
		notifyObservers();		
		checkForWinners();				//check to see if the one of the ghosts caught pacman
		System.out.println(this.toString());
	}

	/* This method determines whether pacman or the ghosts have
	 * won the game. It returns an integer to indicate whether or
	 * not a character has won the game.
	 */
	public int checkForWinners() {
		if (maze.getDots()==0)
			return PACMAN_WINS;
		if (inky.getPosition().equals(pacman.getPosition()) || pinky.getPosition().equals(pacman.getPosition()) || blinky.getPosition().equals(pacman.getPosition()) || clyde.getPosition().equals(pacman.getPosition())) {
			System.out.println("The ghosts got you!, You lose!!");
			return GHOSTS_WIN;
		}
		return NO_WINNER;
	}
	
	/* Returns a copy of the maze */
	public Maze getMaze(){
		return maze;
	}
	
	/* Returns the current state of the game */
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
	
	/* Returns pacman's position as a point*/
	public Point getPacmanPosition(){
		return pacman.getPosition();
	}
	/* Returns pinky's position as a point*/
	public Point getPinky(){
		return pinky.getPosition();
	}
	/* Returns inky's position as a point*/
	public Point getInky(){
		return inky.getPosition();
	}
	/* Returns blinky's position as a point*/
	public Point getBlinky(){
		return blinky.getPosition();
	}
	/* Returns clyde's position as a point*/
	public Point getClyde(){
		return clyde.getPosition();
	}
	/* Returns the score as an integer */
	public int getScore(){
		return score.getScore();
	}
	
	public int getPacmansLastMove() {
		return pacman.getLastDirection();
	}
	
}
