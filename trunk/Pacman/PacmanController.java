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
		//view.getRestart().addActionListener(this);
		view.getNewGame().addActionListener(this);
		view.getAbout().addActionListener(this);
		view.getMapBuilder().addActionListener(this);
		view.getSaveGame().addActionListener(this);
		view.getLoadGame().addActionListener(this);
		
	}
	

	/**
	 * implements the commands that will be excuted according
	 * to the key pressed
	 */
	public void keyPressed(KeyEvent e){
		
		String key = e.getKeyText(e.getKeyCode());
		if (key == "Up"){
			model.move(UP);
			//view.update();
			view.update2();
		}
		else if(key == "Down"){
			model.move(DOWN);
			//view.update();
			view.update2();
			
		}
		else if(key == "Right"){
			model.move(RIGHT);
			//view.update();
			view.update2();
			
		}
		else if(key == "Left"){
			model.move(LEFT);
			//view.update();
			view.update2();
			
			
		}
		view.validate();
		view.gameCheck(model.getGameStatus());
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
			//model.resetGame();
			//view.update();
			//view.validate();
		}
		if(e.getActionCommand()== "Map Builder"){
			MazeBuilder builder= new MazeBuilder();
		}
		if(e.getActionCommand() == "Help"){
			view.HelpClicked();
		}
		if(e.getActionCommand() == "About"){
			view.AboutClicked();
		}
		if(e.getActionCommand()== "Save Game"){
			model.saveGameState();
		}
		if(e.getActionCommand()== "Load Game"){
			model.loadGameState();
			view.update();
			view.validate();
		}
	}


	
	

}
