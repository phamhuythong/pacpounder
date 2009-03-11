package jamost.pacman;
/*
 * This class should be the main class for pacman
 * it will initialize the model, view, and controller
 * and enable the view to visible
 * 
 * @author Mohamed Elzayat, Jason Brown
 * @ version 1.0 February 16, 2009.
 */
public class PacmanGame {
	PacmanView view;
	PacmanController controller;
	PacmanModel model;
	// the constructor initialize the mode, view, and controller
	// and set view to visible
	public PacmanGame(){
	  model = new PacmanModel();
	  view = new PacmanView(model);
	  controller = new PacmanController(view,model);
	  
	  view.setVisible(true);
	}

}
