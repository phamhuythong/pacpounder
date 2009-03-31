package jamost.pacman;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Observable;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
/**
 * 
 * @author Jason Brown, Steve Legere
 *
 * Class PacmanModel
 * 
 * This class is the model for the pacman game. It contains
 * the maze, pacman and the ghosts. It is an observable class
 * and is observed by the ghosts.
 */
public class PacmanModel extends Observable  implements Serializable
{
	private static final int EMPTY_SPACE = 0;
	private static final int DOT 		 = 1;
	private static final int WALL		 = 2;
	
	private static final int UP		     = 1;
	private static final int DOWN		 = 2;
	private static final int LEFT		 = 3;
	private static final int RIGHT		 = 4;
	
	private static final int NO_WINNER   = 0;
	private static final int PACMAN_WINS = 1;
	private static final int GHOSTS_WIN  = 2;
	private static final int START_MAZE  = 1;
	
	private Maze maze;
	private Pacman pacman;
	private Pinky pinky;
	private Blinky blinky;
	private Inky inky;
	private Clyde clyde;
	private Score score;
	private int lives,level;
	private boolean gameover, characterReset;
	private MazeExportImport mazeEI;

	/* The constructor simply creates the maze, ghosts,
	 * pacman and the score. It also registers all the
	 * ghosts as observers of the model
	 */
	public PacmanModel(){
		mazeEI = new MazeExportImport();
		try {
			maze = getNewMaze(START_MAZE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		pinky = new Pinky(new Point(12,7));
		blinky = new Blinky(new Point(12,7));
		clyde = new Clyde(new Point(12,7));
		inky = new Inky(new Point(12,7));
		pacman = new Pacman(new Point(12,13));
		score = new Score();
		lives = 3;
		level = 1;
		gameover = false;
		characterReset = false;
		
		addObserver(pinky);
		addObserver(blinky);
		addObserver(clyde);
		addObserver(inky);
	}
	
	public void saveGameState() {
		mazeEI = new MazeExportImport(pacman.getPosition(),blinky.getPosition(), inky.getPosition(), clyde.getPosition(), pinky.getPosition(), score.getScore(), lives, level);
		try {
			mazeEI.exportMazeDataToXML(maze,"gameState.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadGameState() {
		try {
			maze = mazeEI.importMazeDataFromXML("gameState.txt");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		pacman.setPosition(new Point(mazeEI.getPacman()));
		blinky.setPosition(new Point(mazeEI.getBlinky()));
		clyde.setPosition( new Point (mazeEI.getClyde()));
		inky.setPosition( new Point(mazeEI.getInky()));
		pinky.setPosition( new Point(mazeEI.getPinky()));
		score.setScore(mazeEI.getScore());
		level = mazeEI.getLives();
		lives = mazeEI.getLevel();
	}
	
	public boolean getGameStatus() {
		return gameover;
	}

	public int getLives() {
		return lives;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int mazeGetDots() {
		return maze.getDots();
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
		characterReset = true; 
		lives = 3;
		level = 1;
		gameover = false;
		try {
			maze = getNewMaze(START_MAZE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
		characterReset = true;
	}

	/* This method move confirms whether a requested move
	 * for pacman is a valid move. If it is, it updates
	 * pacmans position 
	 */
	public void move(int direction) {
		pacman.pacmanMove(direction, maze);
		if (maze.getPosition(pacman.getPosition()) == DOT) {
			score.incrementScore();
		}
		updateScoreAndObservers();
		maze.setPosition(pacman.getPosition(), EMPTY_SPACE);
		saveGameState();
	}
	/*
	 * This method simply updates the score, as well as notifying the observers of changes.
	 * Prior to notifying the observers, it also checks to see if pacman has won the game.
	 */
	private void updateScoreAndObservers() {
		if (checkForWinners() == PACMAN_WINS) {				//see if pacman has won or moved into a ghost
			level++;
			resetCharacterPositions();
			try {
				maze = getNewMaze(level);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		else if (checkForWinners() == GHOSTS_WIN) {
			lives--;
			if (lives ==0) gameover = true;
			else resetCharacterPositions();
		}
		else {
			setChanged();
			notifyObservers();		
			if (checkForWinners() == GHOSTS_WIN) {				//check to see if the one of the ghosts caught pacman
				lives--;
				if (lives ==0)
					gameover = true;
				else
					resetCharacterPositions();
			}
			System.out.println(this.toString());
		}
	}

	/* This method determines whether pacman or the ghosts have
	 * won the game. It returns an integer to indicate whether or
	 * not a character has won the game.
	 */
	private int checkForWinners() {
		if (maze.getDots()==0) {
			return PACMAN_WINS;
		}
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

	public Point getCharactersLastPosition(String string) {
		if (string.equalsIgnoreCase("Blinky")) return blinky.getLastPosition();
		if (string.equalsIgnoreCase("Clyde")) return clyde.getLastPosition();
		if (string.equalsIgnoreCase("Inky")) return inky.getLastPosition();
		if (string.equalsIgnoreCase("Pinky")) return pinky.getLastPosition();
		if (string.equalsIgnoreCase("Pacman")) return pacman.getLastPosition();
		return null;
	}

	public int getLastPositionType(String string) {
		if (string.equalsIgnoreCase("Blinky")) return blinky.getLastPositionType();
		if (string.equalsIgnoreCase("Clyde")) return clyde.getLastPositionType();
		if (string.equalsIgnoreCase("Inky")) return inky.getLastPositionType();
		if (string.equalsIgnoreCase("Pinky")) return pinky.getLastPositionType();
		if (string.equalsIgnoreCase("Pacman")) return pacman.getLastPositionType();
		return 0;
	}
	
	public Maze getNewMaze(int level) throws Throwable{
		Maze tempMaze;
		String selectLevel;
		
		if (level == 1)
			selectLevel = "maze1_xml.txt";
		else if (level ==2)
			selectLevel = "maze2_xml.txt";
		else if (level ==3)
			selectLevel = "maze3_xml.txt";
		else if (level ==4)
			selectLevel = "maze4_xml.txt";
		else
			return new Maze();
		tempMaze = mazeEI.importMazeDataFromXML(selectLevel);
		/*FileInputStream fis = new FileInputStream(selectLevel);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		tempMaze = new Maze();
		if (object instanceof Maze)
				tempMaze = (Maze) object;
		fis.close();*/
		return tempMaze;
	}

	public boolean isCharacterReset() {
		return characterReset;
	}

	public void setCharacterReset(boolean characterReset) {
		this.characterReset = characterReset;
	}

	
	
}
