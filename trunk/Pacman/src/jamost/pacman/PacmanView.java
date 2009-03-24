package jamost.pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	///////
	private Maze maze;
	///////
	private JButton[][] mazeButtons;
	private BorderLayout maingrid;
	private JPanel panel;
	private JMenuBar jMenuBar;
	private JButton Score;
	
	private JMenuItem newGame ;
	private JMenuItem Restart ;
	private JMenuItem Help ;
	private JMenuItem About ;
	private JMenuItem mapBuilder;
	
	public PacmanView(PacmanModel model) throws IOException, ClassNotFoundException{
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
		Restart = new JMenuItem("Restart");
		Help = new JMenuItem("Help");
		About = new JMenuItem("About");
		mapBuilder = new JMenuItem("Map Builder");
		
		
		file.add(newGame);
		file.add(Restart);
		edit.add(mapBuilder);
		helpMenu.add(Help);
		helpMenu.add(About);
		this.setJMenuBar(jMenuBar);
		
		maingrid = new BorderLayout();
		grid = new GridLayout(ROWS,COLS);
		panel = new JPanel(grid);
		
		this.getContentPane().setLayout(maingrid);
		this.getContentPane().add(Score,maingrid.NORTH);
		
		/*
		 * Second trial
		 */
		mazeButtons = new JButton[ROWS][COLS];
		map = model.getMaze().getArray();
		update();
		build();
		

		
	}
	public void update(){
		
		for (int j = 0; j < ROWS; j++) {
			   for (int i = 0; i < COLS; i++) {
				   if(model.getPacmanPosition().x == i && model.getPacmanPosition().y == j){
					   ImageIcon icon;
					   if (model.getPacmansLastMove()==UP){
						   icon = new ImageIcon("pacman_up.gif");
					   }
					   else if (model.getPacmansLastMove()==DOWN){
						   icon = new ImageIcon("pacman_down.gif");
					   }
					   else if (model.getPacmansLastMove() ==LEFT){
						   icon = new ImageIcon("pacman_left.gif");
					   }
					   else{
						   icon = new ImageIcon("pacman_right.gif");
					   }
					   buildButtons(j, i, icon);
						
				   }
				   else if(model.getInky().x ==i && model.getInky().y ==j){
					   ImageIcon icon;
					   if (model.getGhostDirection("Inky")==UP){
						   icon = new ImageIcon("inky_Up.jpg");
					   }
					   else if (model.getGhostDirection("Inky")==DOWN){
						   icon = new ImageIcon("inky_Down.jpg");
					   }
					   else if (model.getGhostDirection("Inky")==LEFT){
						   icon = new ImageIcon("inky_Left.jpg");
					   }
					   else{
						   icon = new ImageIcon("inky_Right.jpg");
					   }
					   
					   
					   buildButtons(j, i, icon);
				   }
				   else if(model.getPinky().x ==i && model.getPinky().y ==j){
					   ImageIcon icon;
					   int direction = model.getGhostDirection("Pinky");
					   if (direction==UP){
						   icon = new ImageIcon("pink_up.jpg");
					   }
					   else if (direction==DOWN){
						   icon = new ImageIcon("pink_down.jpg");
					   }
					   else if (direction==LEFT){
						   icon = new ImageIcon("pink_left.jpg");
					   }
					   else{
						   icon = new ImageIcon("pink_right.jpg");
					   }
					   
					   
					   buildButtons(j, i, icon);
				   }
				   else if(model.getClyde().x ==i && model.getClyde().y ==j){
					   ImageIcon icon = new ImageIcon("clyde.gif");
					   buildButtons(j, i, icon);
				   }
				   else if(model.getBlinky().x ==i && model.getBlinky().y ==j){
					   ImageIcon icon;
					   if (model.getGhostDirection("Blinky")==UP){
						   icon = new ImageIcon("blinky_up.gif");
					   }
					   else if (model.getGhostDirection("Blinky")==DOWN){
						   icon = new ImageIcon("blinky_down.gif");
					   }
					   else if (model.getGhostDirection("Blinky")==LEFT){
						   icon = new ImageIcon("blinky_left.gif");
					   }
					   else{
						   icon = new ImageIcon("blinky_right.gif");
					   }
					   System.out.println("Blinky going:"+model.getGhostDirection("Blinky"));
					   buildButtons(j, i, icon);
				   }
				   
				   else{
				   if(map[j][i]==DOT){
					   ImageIcon icon = new ImageIcon("Pacmandot.jpg");
					   buildButtons(j, i, icon);
				   }
				   if(map[j][i]== WALL){
					   ImageIcon icon = new ImageIcon("wall.jpg");
						buildButtons(j, i, icon);
						
					}
				   if(map[j][i]== SPACE){
					   JButton temp = new JButton("");
						temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						
						mazeButtons[j][i] = temp;
					}
				   }
			   }
				   
		}
		this.getContentPane().removeAll();
		build();
		
	}
	private void buildButtons(int j, int i, ImageIcon icon) {
		JButton temp = new JButton(icon);
		temp.setBorderPainted(false);
		temp.setEnabled(false);
		temp.setBackground(Color.black);
		temp.setDisabledIcon(icon);
		mazeButtons[j][i]= temp;
	}
	
	public void build(){
		panel.removeAll();
		for (int j = 0; j < ROWS; j++) {
			   for (int i = 0; i < COLS; i++) {
				   panel.add(mazeButtons[j][i]);
				   
			   }
	     }
		
		this.getContentPane().add(panel,maingrid.CENTER);
		this.getContentPane().add(Score,maingrid.NORTH);
		Score.setText("Score:"+model.getScore());
		this.validate();
	}
	
	public void gameCheck(int status){
		String state;
		if(status == PACMAN_WINS){
			state = "WON";
		}else{
			state = "LOST";
		}
		if(status !=0){
			Object[] options = {"New Game",
            "Exit"};
			int n = JOptionPane.showOptionDialog(this,
					"You have "+state+", do you want to play again?",
					"Pacman GameOver!!!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
			       if(n ==0){
			    	   model.resetGame();
			    	   map = model.getMaze().getArray();
			    	   this.update();
			       }
			       else{
			    	   model.exitGame();
			       }
		}
			
		
		
		
	}
	public JMenuItem getHelp(){
		return Help;
	}
	public JMenuItem getAbout(){
		return About;
	}
	public JMenuItem getRestart(){
		return Restart;
	}
	public JMenuItem getNewGame(){
		return newGame;
	}
	
	

}
