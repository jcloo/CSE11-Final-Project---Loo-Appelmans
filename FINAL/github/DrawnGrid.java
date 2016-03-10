import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import objectdraw.*;

public class DrawnGrid extends WindowController implements ActionListener,
                                                           ChangeListener,
                                                           MouseListener {

	//button objects for running, pausing, clearing all cells
	// implements ActionListener
	private JButton run = new JButton("Run");
	private JButton pause = new JButton("Pause");
	private JButton clear = new JButton("Clear All");
	
	//slider object to change speed
	//implements ChangeListener
	private double speed = 50.0;
	private JSlider speedSlider = new JSlider();
	private JLabel speedLabel;
	
	//Panels to set up GUI Components
	private JPanel panel, panel2, panel3;
	
	// dimensions for size of canvas
	private final static int FRAME_WIDTH = 1000;
	private final static int FRAME_HEIGHT = 1000;
	
	//Size of cells variable
	private final int cellSize = 20;
	
	//TO-DO: is there anything needed for when Grid objects are drawn?
	
  public static void main (String [] args) {
	// Runs the program with correct dimensions, DO NOT CHANGE
    new Acme.MainFrame(new DrawnGrid(), args, FRAME_WIDTH, FRAME_HEIGHT);
  }
  
  public void begin() {
	  //creates panels
	  panel = new JPanel();
	  panel2 = new JPanel();
	  panel3 = new JPanel();
	  
	  speedLabel = new JLabel("Speed: " + speed);
	  
	  //adds objects (buttons/sliders/labels) to panel, panel3
	  panel2.add(run);
	  panel2.add(pause);
	  panel2.add(clear);
	  panel.add(panel2);
	  panel3.add(speedLabel);
	  panel3.add(speedSlider);
	  
	  //calls to add listeners to objects
	  //needs ActionListener or MouseListener (?) for Grid
	  run.addActionListener(this);
	  pause.addActionListener(this);
	  clear.addActionListener(this);
	  speedSlider.addChangeListener(this);
	  
	  //adds panels onto top and bottom of frame
	  this.add(panel, BorderLayout.NORTH);
	  this.add(panel3, BorderLayout.SOUTH);
	  
	 
	  Automata2D.initializeArray();
	  //Create new Grid 
	  for(int i = 0; i < Automata2D.column; i++) {
		  for(int j = 0; j < Automata2D.row; j++) {
			  Grid g = new Grid(i * cellSize, j * cellSize, cellSize, canvas);
			  if(Automata2D.firstGen[i][j] == 1) {
				  FilledRect status = new FilledRect(i * cellSize, j * cellSize, cellSize , cellSize, canvas);
			  }
		  }
	  }		  
	  
	  
	  //necessary to complete layout - DO NOT CHANGE
	  this.validate();
  }

//Used for slider - changes speed of program
@Override
public void stateChanged(ChangeEvent arg0) {
	// will not work until Grid.java is updated
	speed = speedSlider.getValue();
	speedLabel.setText("Speed: " + speed);
	
}
    
// methods to be overwritten  
  
@Override
public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
  
} // end of class DrawnGrid
