package jamost.pacman;
/**
 * 
 * @author Mohamed Elzayat
 * @version 1.2 March 1, 2009.
 * PacmanGame initialises the model view and controller and link them together
 *
 */

public class PacmanGame {
	
	
	public static void main(String[] args) {
		PacmanModel model = new PacmanModel();
		PacmanView view = new PacmanView(model);
		PacmanController controller =  new PacmanController(view,model);
		view.setSize(945, 700);
		view.setVisible(true);
		view.show();
		
	}

}
