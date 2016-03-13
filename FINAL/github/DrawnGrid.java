import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import objectdraw.*;

/**
 * Class creates window w/ GUI components, runs the cellular automation
 * and listens for event handling.
 * @author Joel Loo, Madeleine Appelmans
 * @email jcloo@ucsd.edu, mappelma@ucsd.edu
 * @pid a12623303, a13101416
 */

public class DrawnGrid extends WindowController implements ActionListener,
                                                           ChangeListener {

	// button objects for running, pausing, clearing all cells
	// implements ActionListener
	private JButton run = new JButton("Run");
	private JButton pause = new JButton("Pause");
	private JButton restart = new JButton("Restart");
	
	// slider object to change speed
	// implements ChangeListener
	private static double speed = 50.0;
	private static double fakeSpeed = 0.0;
	private JSlider speedSlider = new JSlider();
	private JLabel speedLabel;
	
	// Panels to set up GUI Components
	private JPanel panel, panel2, panel3;
	
	// Dimensions for size of canvas
	private final static int FRAME_WIDTH = 600;
	private final static int FRAME_HEIGHT = 675;
	
	// Size of cells variable
	private final int cellSize = 20;
	
	// Boolean for pause button
	private static boolean paused = false;
	
	// Thread which event handling will run on
	private static Thread t1;
	
	// used to refer to a Thread's monitor
	private static Object lock = new Object();

	/**
	 * Main method, only used to create frame and call begin method
	 * @param args Represents a string of arguments written by user in cmd line
	 */
	public static void main (String [] args) {
		// Runs the program with correct dimensions
		new Acme.MainFrame(new DrawnGrid(), args, FRAME_WIDTH, FRAME_HEIGHT);
	} // end of main method
  
	/**
	 * Creates panels and adds buttons/sliders/labels to these panels, shown
	 * in the frame. Also creates the first generation of values of cells, and
	 * draws these cells (with the grid) on the frame's canvas
	 */
	public void begin() {
		//creates panels
		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
	  
		speedLabel = new JLabel("Speed: " + speed);
	  
		//adds objects (buttons/sliders/labels) to panel, panel3
		panel2.add(run);
		panel2.add(pause);
		panel2.add(restart);
		panel.add(panel2);
		panel3.add(speedLabel);
	  	panel3.add(speedSlider);
	  
	  	//calls to add listeners to objects
	  	//needs ActionListener or MouseListener (?) for Grid
	  	run.addActionListener(this);
	  	pause.addActionListener(this);
	  	restart.addActionListener(this);
	  	speedSlider.addChangeListener(this);
	  
	  	//adds panels onto top and bottom of frame
	  	this.add(panel, BorderLayout.NORTH);
	  	this.add(panel3, BorderLayout.SOUTH);
	 
	  	//Initialize first matrix
	  	Automata2D.createFirstGen(Automata2D.firstGen);
	  
	  	//Create new Grid Display  
	  	Grid.createBoard(Automata2D.firstGen, Automata2D.rectArray, cellSize, canvas);

	  	//necessary to complete layout
	  	this.validate();
	} //end of method begin

	/**
	 * Listener for when any action is performed. Checks the source of the
	 * event. If event comes from a button, whatever the button is supposed
	 * to do (Run/Pause/Restart) takes place.
	 * @param arg0 Represents the ActionEvent that has just taken place
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// if event comes from Run button
		if(arg0.getSource() == run) {
			// creates new Thread which enables new generations of cells to
			// appear, also maximizes efficiency of program by running constant
			// animations in another CPU
			t1 = new Thread(new Runnable() {
				// overriding t1's run method (which makes new generations)
				public void run() {
					Runnable refresh = new Runnable() {
						public void run() {
							//Initialize next matrix
							Automata2D.createNextGen(Automata2D.firstGen, Automata2D.nextGen);
							
							//shows NextGen
							Grid.setRectArray(Automata2D.nextGen, Automata2D.rectArray);
  						
							//Set FirstGen = NextGen
							Automata2D.setNextGen(Automata2D.firstGen, Automata2D.nextGen);
						}
					};
					// changes speed of cell generations depending on slider
					while(true) {
						//gets speed
						fakeSpeed = 101 - speed;			
						SwingUtilities.invokeLater(refresh);
						Grid.pause(fakeSpeed);
						// while running, program constantly checks if Pause
						// button is pressed
						checkPause();
					}
				}
			});
			t1.start(); // starts thread
		}
		// if event comes from Pause button
		if (arg0.getSource() == pause) {
			paused = !paused;
			// sets Pause to "Resume" if Pause is pressed, also does opposite
			pause.setText(paused?"Resume":"Pause");
			// used to notify all threads to do something
			synchronized(lock) {
				lock.notifyAll();
			}
    		 
		}
		// if event comes from Restart button
		if (arg0.getSource() == restart) {
				// sets new generation - do not change
  				Automata2D.createFirstGen(Automata2D.firstGen);
  				//Create new Grid Display  
  				Grid.createBoard(Automata2D.firstGen, Automata2D.rectArray,
  						         cellSize, canvas);				  
		}
  	
	} // end of ActionPerformed

	/**
	 * Used to start and stop threads. Stops if boolean paused is true.
	 */
	public static void checkPause() {
		if(paused) {
			synchronized(lock) {
				while(paused) {
					try {
						lock.wait();
					} catch(InterruptedException e) {
						// nothing
					}
				}
			} 
		} 
	} //end of method checkPause
  
  
	/**
	 * Used with the slider. Changes speed of cells based on slider value.
	 * @param arg0 Used when the slider detects a ChangeEvent
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		setSpeed(speedSlider.getValue());
		speedLabel.setText("Speed: " + getSpeed()); //Speed: speed
	}  //end of method stateChanged

	/**
	 * Gets the value of DrawnGrid.speed
	 * @return returns value of double speed
	 */
	public static double getSpeed() {
		return DrawnGrid.speed;
	} //end of method getSpeed
	
	/**
	 * Sets the value of double speed
	 * @param speed Represents the new value of DrawnGrid.speed
	 */
	public void setSpeed(double speed) {
		DrawnGrid.speed = speed;
	} //end of method setSpeed
  
} // end of class DrawnGrid