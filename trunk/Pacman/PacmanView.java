package jamost.pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JOptionPane;
/**
 * 
 * @author Mohamed Elzayat
 * @version 2 March 12, 2009
 * The view will be implemented here
 */
public class PacmanView extends JFrame{
	private PacmanModel model;
	private GridLayout grid;
	private int[][] map ;
	private static final int COLS = 25, ROWS = 28, DOT =1, WALL =2, SPACE=0;
	private static final int PACMAN_WINS =1, GHOSTS_WIN = 2, UP=1, DOWN=2, LEFT=3, RIGHT=4;
	
	
	private JButton[][] Maze;
	private BorderLayout maingrid;
	private FlowLayout sidegrid;
	private JPanel panel , sidepanel;
	private JMenuBar jMenuBar;
	
	private JMenuItem newGame ,/*Restart,*/ Help, About,mapBuilder, Savegame, LoadGame ;
	private ViewGhostCheck ghostCheck;
	private JButton  Lives, Score, liveAns, Level, levelAns, dotsLeft, dotsLeftAns,Tips;
	
	
	
	public PacmanView(PacmanModel model){
		super("PacMan by team JaMoSt       JASON BROWN   MOHAMED ELZAYAT  STEVE LEGERE");
		
		this.model = model;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jMenuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");
		
		Score = new JButton("");
		Score.setText(Integer.toString(model.getScore()));
		Score.setHorizontalAlignment(JButton.RIGHT);
		Score.setEnabled(false);
		Score.setBackground(Color.black);
		Score.setBorderPainted(false);
		
		jMenuBar.add(file);
		jMenuBar.add(edit);
		jMenuBar.add(helpMenu);
		
		newGame = new JMenuItem("New Game");
		//Restart = new JMenuItem("Restart");
		Help = new JMenuItem("Help");
		About = new JMenuItem("About");
		mapBuilder = new JMenuItem("Map Builder");
		Savegame = new JMenuItem("Save Game");
		LoadGame = new JMenuItem("Load Game");
		
		file.add(newGame);
		//file.add(Restart);
		file.add(Savegame);
		file.add(LoadGame);
		edit.add(mapBuilder);
		helpMenu.add(Help);
		helpMenu.add(About);
		this.setJMenuBar(jMenuBar);
		
		maingrid = new BorderLayout();
		grid = new GridLayout(ROWS,COLS);
		sidegrid= new FlowLayout();
		panel = new JPanel(grid);
		
		sidepanel = new JPanel(sidegrid);
		buildSidePanel(sidepanel);
		
		this.getContentPane().setLayout(maingrid);
		this.getContentPane().add(Score,maingrid.NORTH);
		this.getContentPane().add(sidepanel,maingrid.EAST);
		
		ghostCheck = new ViewGhostCheck(this,model);
		
		Maze = new JButton[ROWS][COLS];
		map = model.getMaze().getArray();
		update();
		build();
		

		
	}
	
	public void update(){
		
		for (int j = 0; j < ROWS; j++) {
			   for (int i = 0; i < COLS; i++) {
				   if(model.getPacmanPosition().x == i && model.getPacmanPosition().y == j){
					   ImageIcon icon= new ImageIcon("pacman_left.gif");
					   ghostCheck.buildButtons(j, i, icon);
				   }
				   else if(model.getInky().x ==i && model.getInky().y ==j){
					   ImageIcon icon= new ImageIcon("inky_Right.jpg");
					  ghostCheck. buildButtons(j, i, icon);
				   }
				   else if(model.getPinky().x ==i && model.getPinky().y ==j){
					   ImageIcon icon= new ImageIcon("pink_right.jpg");
					   ghostCheck.buildButtons(j, i, icon);
				   }
				   else if(model.getClyde().x ==i && model.getClyde().y ==j){
					   ImageIcon icon = new ImageIcon("clyde.gif");
					   ghostCheck.buildButtons(j, i, icon);
				   }
				   else if(model.getBlinky().x ==i && model.getBlinky().y ==j){
					   ImageIcon icon= new ImageIcon("blinky_right.gif");
					   System.out.println("Blinky going:"+model.getGhostDirection("Blinky"));
					   ghostCheck.buildButtons(j, i, icon);
				   }
				   
				   else{
				   if(map[j][i]==DOT){
					   ImageIcon icon = new ImageIcon("Pacmandot.jpg");
					   ghostCheck.buildButtons(j, i, icon);
				   }
				   if(map[j][i]== WALL){
					   ImageIcon icon = new ImageIcon("wall.jpg");
						ghostCheck.buildButtons(j, i, icon);
						
					}
				   if(map[j][i]== SPACE){
					   JButton temp = new JButton("");
						temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						
						Maze[j][i] = temp;
					}
				   }
			   }
				   
		}
		this.getContentPane().removeAll();
		build();
		
	}

	public void update2() {
		sidepanel.removeAll();

		if (!(model.getPacmanPosition().equals(model.getCharactersLastPosition("Pacman")))) {
			Point lastPacman = model.getCharactersLastPosition("Pacman");
			Point pacman = model.getPacmanPosition();
			
			System.out.println("Pacman last position:"+lastPacman);
			System.out.println("Pacman current position:"+pacman);

			ImageIcon icon;
			if (model.getPacmansLastMove() == UP)
				icon = new ImageIcon("pacman_up.gif");
			else if (model.getPacmansLastMove() == DOWN)
				icon = new ImageIcon("pacman_down.gif");
			else if (model.getPacmansLastMove() == LEFT)
				icon = new ImageIcon("pacman_left.gif");
			else
				icon = new ImageIcon("pacman_right.gif");
			ghostCheck.emptySpace(lastPacman);
			ghostCheck.buildButtons(pacman.y, pacman.x, icon);

			

		}
		if (!(model.getInky().equals(model.getCharactersLastPosition("Inky")))) {
			ghostCheck.Inky();

		}
		if (!(model.getBlinky().equals(model.getCharactersLastPosition("Blinky")))) {
			ghostCheck.Blinky();

		}
		if (!(model.getPinky().equals(model.getCharactersLastPosition("Pinky")))) {
			ghostCheck.Pinky();
		}
		if (!(model.getClyde().equals(model.getCharactersLastPosition("Clyde")))) {
			ghostCheck.Clyde();
		}
		this.getContentPane().removeAll();
		build();
		buildSidePanel(sidepanel);
		
	}
	

	
	public JButton[][] getMaze(){
		return Maze;
	}
	
	public void build(){
		panel.removeAll();
		for (int j = 0; j < ROWS; j++) {
			   for (int i = 0; i < COLS; i++) {
				   panel.add(Maze[j][i]);
				   
			   }
	     }
		
		this.getContentPane().add(panel,maingrid.CENTER);
		this.getContentPane().add(Score,maingrid.NORTH);
		this.getContentPane().add(sidepanel,maingrid.EAST);
		Score.setText("Score:"+model.getScore());
		this.validate();
	}
	
	public void gameCheck(boolean status){
		
		
		 if(status){
			Object[] options = {"New Game",
            "Exit"};
			int n = JOptionPane.showOptionDialog(this,
					"You have , do you want to play again?",
					"Pacman GameOver!!!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
			       if(n ==0){
			    	   
			    	   model.resetGame();
			    	   model.resetCharacterPositions();
			    	   map = model.getMaze().getArray();
			    	   this.update();
			    	   this.sidepanel.removeAll();
			    	   this.buildSidePanel(sidepanel);
			    	   this.validate();
			       }
			       else{
			    	   model.exitGame();
			       }
		}
		 if(model.isCharacterReset()){
				map = model.getMaze().getArray();
				model.setCharacterReset(false);
				this.update();
				this.validate();
			}
		
	}
	public void buildSidePanel(JPanel panel){
		Lives= new JButton("Lives:");
		statusButton(Lives);
		
		
		liveAns= new JButton();
		liveAns.setText(Integer.toString(model.getLives()));
		statusButton(liveAns);
		
		
		Level = new JButton("Level:");
		statusButton(Level);
		
		
		levelAns = new JButton();
		levelAns.setText(Integer.toString(model.getLevel()));
		statusButton(levelAns);
		
		
		dotsLeft= new JButton("Dots:");
		statusButton(dotsLeft);
		
		
		dotsLeftAns= new JButton();
		dotsLeftAns.setText(Integer.toString(model.mazeGetDots()));
		statusButton(dotsLeftAns);
		
		ImageIcon icon= new ImageIcon("pacman-arrested1.jpg");
		Tips = new JButton(icon);
		statusButton(Tips);
		Tips.setPreferredSize(new Dimension(200,550));
		Tips.setDisabledIcon(icon);
		
		
		panel.add(Lives);
		panel.add(liveAns);
		panel.add(Level);
		panel.add(levelAns);
		panel.add(dotsLeft);
		panel.add(dotsLeftAns);
		panel.add(Tips);
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(200,760));
		
		
	}
	public JMenuItem getHelp(){
		return Help;
	}
	public JMenuItem getAbout(){
		return About;
	}
	public JMenuItem getNewGame(){
		return newGame;
	}
	public JMenuItem getMapBuilder(){
		return mapBuilder;
	}
	public JMenuItem getSaveGame(){
		return Savegame;
	}
	public JMenuItem getLoadGame(){
		return LoadGame;
	}
	
	private void statusButton(JButton button)
	{
		button.setHorizontalAlignment(JButton.RIGHT);
		button.setEnabled(false);
		button.setBackground(Color.black);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(80,30));
		Font f = new Font("Dialog", Font.BOLD, 14);
		button.setFont(f);
		
	}
	
	public void AboutClicked(){
		JOptionPane.showMessageDialog(this, "PacPounder \nVersion 1.6 \n\n" +
				"Contributors: Jason Brown,\nMohamed Elzayat, and Steve Legere.\n\n" +
				"CopyRight ©2009. All Right Reserved.\n","Team JaMoSt",1,new ImageIcon("pacmanicon.png"));
		
	}
	
	public void HelpClicked(){
		JOptionPane.showMessageDialog(this, 
				"Pacman by Team JaMoSt \n\n" +
				"Game Objective:\n" +
				"-The objective of the game is to consume\n" +
				"  all of the pac-dots on the map\n"+
		        "  before the ghosts get to you.\n" +
		        "  Stay as far away from them as possible!\n\n"+
				"Controls:\n"+
				"-The UP, DOWN, LEFT and RIGHT keys move Pacman\n" +
				"  in their respective directions\n\n"+
				"Display:\n"+
				"-The display on the right-hand side of the screen\n" +
				"  displays three different values:\n"+
			    "    -Lives: You start with three (3) lives.\n" +
			    "      If you lose all three, it's Game Over!\n"+
			    "    -Level: Once you consume all pac-dots,\n" +
			    "      you will move onto the next level!\n"+
			    "    -Dots: This shows how many dots you have left to eat\n" +
			    "      in the current level.\n\n"+
			    "Map Builder:\n"+
			    "-The map builder allows you to build your own custom maps\n" +
			    "  to play! Simply click the button corresponding to the type\n" +
			    "  of object you'd like to create, and click-and-drag\n"+
		        "  your mouse over the map to create your level!\n"+
		        "-Make sure not to block pac-dots into inaccessible areas,\n" +
		        "  or else you will not be able to beat your map!",
				" Team JaMoSt",1,new ImageIcon("arrowkeys4.jpg"));
		
	}
	
	

}
