package jamost.pacman;

import java.io.IOException;

/**
 * 
 * @author Mohamed Elzayat
 * @version 1.5 March 11, 2009.
 * PacmanGame initialises the model view and controller and link them together
 *
 */

public class PacmanGame {
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		PacmanModel model = new PacmanModel();
		PacmanView view = new PacmanView(model);
		PacmanController controller =  new PacmanController(view,model);
		view.setSize(700, 760);
		view.setVisible(true);
		view.setResizable(false);
		view.setVisible(true);
		//MazeBuilder mb = new MazeBuilder();
		
	}

}
