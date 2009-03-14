package jamost.pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

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
/**
 * 
 * @author Mohamed Elzayat
 * @version 1.5 March 11, 2009
 * The view will be implemented here
 */
public class PacmanView extends JFrame{
	//super("PacMan by team JaMoSt    JASON BROWN   MOHAMED ELZAYAT  STEVE LEGERE");
	private PacmanModel model;
	private GridLayout grid;
	private int[][] map ;
	private static final int COLS = 25;
	private static final int ROWS = 28;
	private static final int DOT  = 1;
	private static final int WALL = 2;
	private static final int SPACE = 0;
	private JButton[][] Maze;
	private BorderLayout maingrid;
	private JPanel panel;
	private JMenuBar jMenuBar;
	private JButton Score;
	
	//private JTextField text = new JTextField();
	public PacmanView(PacmanModel model){
		super("PacMan by team JaMoSt       JASON BROWN   MOHAMED ELZAYAT  STEVE LEGERE");
		this.model = model;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jMenuBar = new JMenuBar();
		
		JMenu menu = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		Score = new JButton("");
		Score.setText(Integer.toString(model.getScore()));
		Score.setHorizontalAlignment(JButton.RIGHT);
		Score.setEnabled(false);
		Score.setBackground(Color.black);
		Score.setBorderPainted(false);
		
		jMenuBar.add(menu);
		jMenuBar.add(edit);
		
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem Restart = new JMenuItem("Restart");
		JMenuItem mapBuilder = new JMenuItem("Map Builder");
		
		menu.add(newGame);
		menu.add(Restart);
		edit.add(mapBuilder);
		this.setJMenuBar(jMenuBar);
		
		maingrid = new BorderLayout();
		grid = new GridLayout(ROWS,COLS);
		panel = new JPanel(grid);
		
		this.getContentPane().setLayout(maingrid);
		this.getContentPane().add(Score,maingrid.NORTH);
		//this.getContentPane().add(panel,maingrid.CENTER);
		//text.setText("MOHAMED");
		//this.getContentPane().add(text,maingrid.NORTH);
		
		/*
		 * Second trial
		 */
		Maze = new JButton[ROWS][COLS];
		map = model.getMaze().getArray();
		update();
		build();
		

		
	}
	public void update(){
		
		for (int j = 0; j < ROWS; j++) {
			   for (int i = 0; i < COLS; i++) {
				   if(model.getPacmanPosition().x == i && model.getPacmanPosition().y == j){
					   ImageIcon icon = new ImageIcon("pacman2.gif");
					   //JLabel label = new JLabel(icon);
					   JButton temp = new JButton(icon);
					    temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setDisabledIcon(icon);
						temp.setBackground(Color.black);
						Maze[j][i] = temp;
						//temp.setEnabled(true);
				   }
				   else if(model.getInky().x ==i && model.getInky().y ==j){
					   ImageIcon icon = new ImageIcon("blue.jpg");
					   JButton temp = new JButton(icon);
					    temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						temp.setDisabledIcon(icon);
						Maze[j][i]= temp;
				   }
				   else if(model.getPinky().x ==i && model.getPinky().y ==j){
					   ImageIcon icon = new ImageIcon("pink.jpg");
					   JButton temp = new JButton(icon);
					    temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						temp.setDisabledIcon(icon);
						Maze[j][i]= temp;
				   }
				   else if(model.getClyde().x ==i && model.getClyde().y ==j){
					   ImageIcon icon = new ImageIcon("clyde.gif");
					   JButton temp = new JButton(icon);
					    temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						temp.setDisabledIcon(icon);
						Maze[j][i]= temp;
				   }
				   else if(model.getBlinky().x ==i && model.getBlinky().y ==j){
					   ImageIcon icon = new ImageIcon("red.jpg");
					   JButton temp = new JButton(icon);
					    temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						temp.setDisabledIcon(icon);
						Maze[j][i]= temp;
				   }
				   
				   else{
				   if(map[j][i]==DOT){
					   ImageIcon icon = new ImageIcon("Pacmandot.jpg");
					   JButton temp = new JButton(icon);
					    temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						temp.setDisabledIcon(icon);
						Maze[j][i] = temp;
						//this.getContentPane().add(button);
				   }
				   if(map[j][i]== WALL){
					   ImageIcon icon = new ImageIcon("wall.jpg");
						//button = new JButton("wall",icon);
						JButton temp = new JButton(icon);
						temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						temp.setDisabledIcon(icon);
						Maze[j][i]= temp;
						//this.getContentPane().add(button);
					}
				   if(map[j][i]== SPACE){
					   JButton temp = new JButton("");
						temp.setBorderPainted(false);
						temp.setEnabled(false);
						temp.setBackground(Color.black);
						//this.getContentPane().add(button);
						Maze[j][i] = temp;
					}
				   }
			   }
				   
		}
		this.getContentPane().removeAll();
		build();
		
	}
	
	public void build(){
		panel.removeAll();
		for (int j = 0; j < ROWS; j++) {
			   for (int i = 0; i < COLS; i++) {
				   panel.add(Maze[j][i]);
				   //this.getContentPane().add(Maze[j][i],maingrid.CENTER);
				  //this.getContentPane().add(Maze[j][i]);
				   
			   }
	     }
		//this.getContentPane().add(text,maingrid.NORTH);
		this.getContentPane().add(panel,maingrid.CENTER);
		this.getContentPane().add(Score,maingrid.NORTH);
		Score.setText("Score:"+model.getScore());
	}
	

}
