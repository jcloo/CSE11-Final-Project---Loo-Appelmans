import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objectdraw.*;

/**
 * Class makes an instance of a grid piece, defining how it works with a cell.
 * @author Joel Loo, Madeleine Appelmans
 *
 */


public class Grid implements MouseListener{
	
	public static boolean cellStatus = false;
	
	private FramedRect fr;
	private FilledRect status;
	
  // ctor which defines what a Grid piece is
  public Grid (int x, int y, int size, DrawingCanvas canvas) {
	  fr = new FramedRect (x, y, size, size, canvas);
	  status = new FilledRect(x, y, size -5 , size -5, canvas);
  }
  
  public void setCellStatus() {
	  // if alive, set cellStatus to true
	  // else, set cellStatus to false
  }
  
  public void setCell() {
	  if (cellStatus) {
		  status.setColor(Color.YELLOW);
	  }
	  else {
		  status.setColor(Color.GRAY);
	  }
  }

// shows the FramedRect with a color representing if cell is dead/alive
  public void show() {
	  fr.show();
	  setCellStatus();
	  setCell();
	  status.show();
  }
  
  public boolean equals(Object o) {
	  return true;
  }
  
// Event handling for mouse events (ex. clicked)
  public void mouseClicked(MouseEvent e) {
	  
  }
  
  //slider
  public void mousePressed(MouseEvent e) {
	//if (e.getSource() == slider)  
  }
  
  //not needed
  public void mouseEntered(MouseEvent e) { 
  }
  //not needed
  public void mouseExited(MouseEvent e) {  
  }
  //not needed
  public void mouseReleased(MouseEvent e) {
  }
  
} // end of class Grid
