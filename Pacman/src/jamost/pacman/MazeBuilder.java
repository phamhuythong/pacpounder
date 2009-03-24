package jamost.pacman;

/**
 * 
 * @author Jason Brown
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MazeBuilder implements ActionListener, Serializable{
	private JFrame viewFrame;
	private BorderLayout borderLayout;
	private JPanel centerPanel,sidePanel;
	private GridLayout centerLayout, sideLayout;
	private JTextArea instructions;
	private Maze maze;
	private JButton [][]buttons;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem mi_export, mi_import, mi_exportXML, mi_importXML;
	private int buttonChoice;
	private boolean mousePressed;
	private static final int COLS = 25, ROWS = 28;
	private static final int SPACE = 0, DOT  = 1, WALL = 2;
	
	public MazeBuilder() {
		borderLayout = new BorderLayout();
		buttons = new JButton[ROWS][COLS];
		viewFrame = new JFrame();
		centerLayout = new GridLayout(ROWS,COLS);
		sideLayout = new GridLayout(4,1);
		centerPanel = new JPanel(centerLayout);
		sidePanel = new JPanel(sideLayout);
		maze = new Maze();
		menubar = new JMenuBar();
		menu = new JMenu("Export");
		mi_export = new JMenuItem("Export Maze");
		mi_import = new JMenuItem("Import Maze");
		mi_exportXML = new JMenuItem("Export Maze to XML");
		mi_importXML = new JMenuItem("Import Maze to XML");
		instructions = new JTextArea();
		buttonChoice = 0;
		mousePressed = false;
		createMaze();
	}
	
	public void createMaze() {
		JButton spaceButton = new JButton("spaceButton");
		spaceButton.setIcon(null);
		JButton dotButton = new JButton("dotButton");
		dotButton.setIcon(new ImageIcon("Pacmandot.jpg"));
		JButton wallButton = new JButton("wallButton");
		wallButton.setIcon(new ImageIcon("wall.jpg"));
		spaceButton.addActionListener(new controlListener());
		dotButton.addActionListener(new controlListener());
		wallButton.addActionListener(new controlListener());
		mi_export.addActionListener(new menuListener());
		mi_import.addActionListener(new menuListener());
		mi_exportXML.addActionListener(new menuListener());
		mi_importXML.addActionListener(new menuListener());
		instructions.setText("Select a maze piece then\nclick and drag the mouse\nto design your own\ncustom maze!\n\nSelect 'Export' Export Maze\nto save your maze");
		menu.add(mi_export);
		menu.add(mi_import);
		menu.add(mi_exportXML);
		menu.add(mi_importXML);
		menubar.add(menu);
		menubar.setVisible(true);
		viewFrame.setTitle("Pacman Maze Builder");
		viewFrame.setJMenuBar(menubar);
		viewFrame.getContentPane().setLayout(borderLayout);
		viewFrame.getContentPane().add(sidePanel,borderLayout.EAST);
		viewFrame.getContentPane().add(centerPanel, borderLayout.CENTER);
		sidePanel.add(instructions);
		sidePanel.add(spaceButton);
		sidePanel.add(dotButton);
		sidePanel.add(wallButton);

		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				maze.setPosition(new Point(j,i), DOT);
				maze.incrementNumberOfDots();
				JButton button = new JButton(new ImageIcon("Pacmandot.jpg"));
				button.setBorderPainted(false);
				button.setBackground(Color.black);
				//button.addActionListener(this);
				button.addMouseListener(new mazeListener());	/* Add MouseListener */ /* Add MouseListener */
				buttons[i][j] = button;
			}
		}
		build();
		viewFrame.setSize(800, 758);
		viewFrame.setResizable(false);
		viewFrame.setDefaultCloseOperation(viewFrame.DISPOSE_ON_CLOSE);
		viewFrame.setVisible(true);
	}
	
	private void build(){
		for (int i = 0; i < ROWS; ++i) {
			   for (int j = 0; j < COLS; ++j) {
				   centerPanel.add(buttons[i][j]);				   
			   }
	     }
	}
	
	private void rebuildMaze() {
		centerPanel.removeAll();
		for (int i = 0; i < ROWS; ++i) {
			   for (int j = 0; j < COLS; ++j) {
				   if (maze.getPosition(new Point(j,i))==SPACE ) {
					   buttons[i][j] = new JButton();
					   buttons[i][j].setBackground(Color.black);
					   buttons[i][j].setBorderPainted(false);
				   }
				   else if (maze.getPosition(new Point(j,i))==DOT ) {
					   buttons[i][j] = new JButton(new ImageIcon("Pacmandot.jpg"));
					   buttons[i][j].setBackground(Color.black);
					   buttons[i][j].setBorderPainted(false);
				   }
				   else if (maze.getPosition(new Point(j,i))==WALL ) {
					   buttons[i][j] = new JButton(new ImageIcon("wall.jpg"));
					   buttons[i][j].setBackground(Color.black);
					   buttons[i][j].setBorderPainted(false);
				   }
				   buttons[i][j].addMouseListener(new mazeListener());
				   centerPanel.add(buttons[i][j]);				   
			   }
	     }
		viewFrame.validate();
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (buttonChoice == DOT) {
			button.setIcon(new ImageIcon("Pacmandot.jpg"));
			button.setName("dot");
		}
		else if (buttonChoice == WALL) {
			button.setIcon(new ImageIcon("wall.jpg"));
			button.setName("wall");
		}
		else if (buttonChoice == SPACE) {
			button.setIcon(null);
			button.setName("space");
		}
		
			//maze.setPosition(p, WALL); **How can I do this**?
			//updateMaze();	//to be called prior to export
		
	}
	//old export method
	/*public void exportMazeData() throws IOException {
		updateMaze();
		String fileName = JOptionPane.showInputDialog("Please enter a file name.");
		FileWriter fw = new FileWriter(fileName+".txt");
		for (int i = 0;i<ROWS;i++) {
			for (int j = 0;j<COLS;j++) {
				fw.write(maze.getPosition(new Point(j,i))+"\r\n");
			}
			//fw.write(bud.toString()+"\r\n");
		}
		fw.close();			
		
	}*/
	
	private class controlListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getText().equals("spaceButton"))
				buttonChoice = SPACE;
			else if (button.getText().equals("dotButton"))
				buttonChoice = DOT;
			else if (button.getText().equals("wallButton"))
				buttonChoice = WALL;
		}
		
	}
	
	private class menuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			if (item.getText().equals("Export Maze")) {
				try {
					exportMazeData();
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (item.getText().equals("Import Maze")) {
				try {
					importMazeData();
				}
				catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException ee) {
					ee.printStackTrace();
				}
			}
			if (item.getText().equals("Import Maze to XML")) {
				importMazeDataFromXML();
			}
			if (item.getText().equals("Export Maze to XML")) {
				try {
					exportMazeDataToXML();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	/* Private Inner Class to handle mouse listener events */
	private class mazeListener implements MouseListener {
		
		public void mousePressed(MouseEvent e) {
			mousePressed = true;
		}

		public void mouseReleased(MouseEvent e) {
			mousePressed = false;
		}
		
		public void mouseEntered(MouseEvent e) {
			if (mousePressed == true) {
				JButton button = (JButton) e.getSource();
				if (buttonChoice == DOT) {
					button.setIcon(new ImageIcon("Pacmandot.jpg"));
					button.setName("dot");
				}
				else if (buttonChoice == WALL) {
					button.setIcon(new ImageIcon("wall.jpg"));
					button.setName("wall");
				}
				else if (buttonChoice == SPACE) {
					button.setIcon(null);
					button.setName("space");
				}
			}
		}

		public void mouseExited(MouseEvent e) {
			//currently
		}
		
		public void mouseClicked(MouseEvent e) {
			//currently not used
		}
	}
	
	public void updateMaze() {
		for (int i = 0; i < ROWS; i++) {
			   for (int j = 0; j < COLS; j++) {
				   if (buttons[i][j].getName() != null) {
					   if (buttons[i][j].getName().equals("wall")) {
						   maze.setPosition(new Point(j,i), WALL);
					   }
					   else if (buttons[i][j].getName().equals("dot")) {
						   maze.setPosition(new Point(j,i), DOT);
					   }
				   }
					   
			   }
	     }
	}
	
	public void exportMazeData() throws IOException {
		updateMaze();	//update the mazes data
		String fileName = JOptionPane.showInputDialog("Please enter a file name.");
		FileOutputStream fos = new FileOutputStream(fileName+".txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(maze);
		fos.close();	
	}
	
	public void importMazeData() throws IOException, ClassNotFoundException {
		String fileName = JOptionPane.showInputDialog("Please enter a maze name to open.");
		FileInputStream fis = new FileInputStream(fileName+".txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		if (object instanceof Maze)
				maze = (Maze) object;
		fis.close();
		rebuildMaze();
	}//function end
	
	public void exportMazeDataToXML() throws IOException {
		FileWriter fw = new FileWriter("maze_xml.txt");
		fw.write("<Maze>\r\n");
		for (int i = 0; i < ROWS; i++) {
			fw.write("<Row>");
			   for (int j = 0; j < COLS; j++) {
				   fw.write("<Element>" + maze.getPosition(new Point(i,j)) + "</Element>");
			   }
			fw.write("</Row>");
		}
		fw.write("</Maze>\r\n");
		fw.close();	
	}
	
	public void importMazeDataFromXML() throws IOException, ClassNotFoundException {
		String fileName = JOptionPane.showInputDialog("Please enter a maze name to open.");
		FileInputStream fis = new FileInputStream(fileName+".txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		if (object instanceof Maze)
				maze = (Maze) object;
		fis.close();
		rebuildMaze();
	}//function end
	
}// end of class
