package jamost.pacman;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*; 
import java.awt.event.*;
/**
 * 
 * @author Mohamed Elzayat
 * @version 1.2 March 2, 2009.
 * The PacmanController implements keyListener and 
 * according to key input from the user "Up", "Down", 
 * "Left" or "Right" a call is made to the Model
 * to execute the proper command
 */
public class PacmanController implements KeyListener, ActionListener{
	private PacmanView view;
	private PacmanModel model;
	final static int UP = 1;
	final static int DOWN = 2;
	final static int LEFT = 3;
	final static int RIGHT = 4;
	
	
	public PacmanController(PacmanView view, PacmanModel model){
		this.model = model;
		this.view = view;
		
		view.addKeyListener(this);
		view.getHelp().addActionListener(this);
		view.getRestart().addActionListener(this);
		view.getNewGame().addActionListener(this);
		view.getAbout().addActionListener(this);
		
	}
	

	/**
	 * implements the commands that will be excuted according
	 * to the key pressed
	 */
	public void keyPressed(KeyEvent e){
		
		String key = e.getKeyText(e.getKeyCode());
		if (key == "Up"){
			model.move(UP);
			view.update();
		}
		else if(key == "Down"){
			model.move(DOWN);
			view.update();
			
		}
		else if(key == "Right"){
			model.move(RIGHT);
			view.update();
			
		}
		else if(key == "Left"){
			model.move(LEFT);
			view.update();
			
			
		}
		view.validate();
		view.gameCheck(model.checkForWinners());
	}



	// Nothing happens here, not needed
	public void keyReleased(KeyEvent e) {
		
	}



	// Nothing happens here, not needed
	public void keyTyped(KeyEvent e) {
		
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "New Game"){
			model.resetGame();
			view.update();
			view.validate();
		}
		if(e.getActionCommand() == "Restart"){
			model.resetGame();
			view.update();
			view.validate();
		}
		if(e.getActionCommand() == "Help"){
			
		}
		if(e.getActionCommand() == "About"){
			
		}
		
	}


	
	

}
