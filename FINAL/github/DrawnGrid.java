import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import objectdraw.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


//extends WindowController/JFrame - uncomment shit if using this
public class DrawnGrid extends WindowController implements ActionListener,
                                                           ChangeListener,
                                                           MouseListener {

	//button objects for running, pausing, clearing all cells
	// implements ActionListener
	private JButton run = new JButton("Run");
	private JButton pause = new JButton("Pause");
	private JButton restart = new JButton("Restart");
	
	//slider object to change speed
	//implements ChangeListener
	private static double speed = 50.0;
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
	  
	  //using JFrame instead of WindowController
//	  DrawnGrid dg = new DrawnGrid();
//	  dg.setVisible(true);
//	  dg.begin();
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
	  Grid.setCellStatus(Automata2D.firstGen, cellSize, canvas);
			  
		 
	  
	  //necessary to complete layout - DO NOT CHANGE
	  this.validate();

	 // Grid.pause(1000);
	//  this.canvas.clear();
  }

  //how to override paint method?
  //new Thread object (swing's invoke later method?) which is called in actionperformed
  //or
  //where is paint being called? find and insert before?
  //or
  //use JFrame from Swing? 
@Override 
public void paint(Graphics g) {
	super.paint(g);
	System.out.println("lol");
}


// says what the buttons will do
  @Override
  public void actionPerformed(ActionEvent arg0) {
  	if(arg0.getSource() == run) {
  		System.out.println("1");
  		Thread t1 = new Thread(new Runnable() {
  			public void run() {
  				Runnable refresh = new Runnable() {
  					public void run() {
  						
  						Calendar cal = Calendar.getInstance();
  				        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
  				        System.out.println("Start");
  				        System.out.println( sdf.format(cal.getTime()) );
  				        
  						System.out.println("run");
  						//gets speed
  						int tmpSpeed = speedSlider.getValue();
  						setSpeed(tmpSpeed);
  						
  						//Initialize next matrix
  						Automata2D.createNextGen(Automata2D.firstGen, Automata2D.nextGen);
  						
  						Grid.pause(3000); //should be Grid.pause(speed * x);
  						System.out.println("test");
  						
  						//shows NextGen
  						Grid.setCellStatus(Automata2D.nextGen, cellSize, canvas);
  						
  						//Set FirstGen = NextGen
  						Automata2D.setNextGen(Automata2D.firstGen, Automata2D.nextGen);
  						
  						System.out.println("End");
  				        System.out.println( sdf.format(cal.getTime()) );
  						
  					}
  				};
  				System.out.println("2");
  				
  				//fix for indefinitely
  				int foo = 0;
  				while(foo < 4) {
  					SwingUtilities.invokeLater(refresh);
  					foo++;
  					Grid.pause(5000);
  				}
  			}
  		});
  		t1.start();
  		System.out.println("Started");
  	}
  	
  	if (arg0.getSource() == pause) {
  		setSpeed(0);
  	}
  	
  	if (arg0.getSource() == restart) {
  			// sets new generation - do not change
  			Automata2D.createFirstGen(Automata2D.firstGen);
  		    //Create new Grid Display  
  		    Grid.setCellStatus(Automata2D.firstGen, cellSize, canvas);				  
  	}
  	
  } // end of ActionPerformed

//Used for slider - changes speed of program
@Override
public void stateChanged(ChangeEvent arg0) {
	setSpeed(speedSlider.getValue());
	speedLabel.setText("Speed: " + getSpeed());
}  

public static double getSpeed() {
	return DrawnGrid.speed;
}

public void setSpeed(double speed) {
	DrawnGrid.speed = speed;
}

// NONE OF THESE NEEDED YET!!!!!
@Override
public void mouseClicked(MouseEvent arg0) {
}
@Override
public void mouseEntered(MouseEvent e) {
}
@Override
public void mouseExited(MouseEvent e) {
}
@Override
public void mousePressed(MouseEvent e) {
}
@Override
public void mouseReleased(MouseEvent e) {
}
  
} // end of class DrawnGrid
