package jamost.pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
/**
 * 
 * @author Mohamed Elzayat
 * @version 1.1
 * The view will be implemented here
 */
public class PacmanView extends JFrame{
	private PacmanModel model;
	private GridLayout grid;
	private int[][] map ;
	private static final int COLS = 25;
	private static final int ROWS = 28;
	private static final int DOT  = 1;
	private static final int WALL = 2;
	private static final int SPACE = 0;
	public PacmanView(PacmanModel model){
		
		this.model = model;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		grid = new GridLayout(ROWS,COLS);
		this.getContentPane().setLayout(grid);
		
		/*
		 * Second trial
		 */
		map = model.getMaze().getArray();
		update();
		

		
	}
	public void update(){
		for (int j = 0; j < ROWS; ++j) {
		   for (int i = 0; i < COLS; ++i) {
				JButton button;
				if(map[j][i]==DOT){
					button = new JButton(".");
					button.setBorderPainted(false);
					button.setEnabled(false);
					button.setBackground(Color.black);
					this.getContentPane().add(button);
					
				}
				if(map[j][i]== WALL){
					ImageIcon icon = new ImageIcon("wall.gif","wall");
					//button = new JButton("wall",icon);
					button = new JButton("");
					button.setBorderPainted(false);
					button.setEnabled(false);
					button.setBackground(Color.BLUE);
					this.getContentPane().add(button);
				}
				if(map[j][i]== SPACE){
					button = new JButton("");
					button.setBorderPainted(false);
					button.setEnabled(false);
					button.setBackground(Color.black);
					this.getContentPane().add(button);
				}
			}
		}
	}
	

}
