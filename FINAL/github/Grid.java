import java.awt.*;
import java.awt.event.*;
import objectdraw.*;

/**
 * Class makes an instance of a grid piece, defining how it works with a cell.
 * @author Joel Loo, Madeleine Appelmans
 *
 */


public class Grid extends ActiveObject implements MouseListener {
	
	public static boolean cellStatus = false;
	
	private FramedRect fr;
	private FilledRect status;
	
	
    // ctor which defines what a Grid piece is
    public Grid (int x, int y, int size, DrawingCanvas canvas) {
       fr = new FramedRect (x, y, size, size, canvas);
       fr.setColor(Color.GREEN);
    }
  

  public static void createBoard(int[][] Gen, FilledRect[][] rect, int cellSize, DrawingCanvas canvas) {
	  for(int i = 0; i < Gen.length; i++) {
		  for(int j = 0; j < Gen[i].length; j++) {
			  if(Gen[i][j] == 1) {
				  FilledRect status = new FilledRect(i * cellSize, j * cellSize, cellSize , cellSize, canvas);
				  status.setColor(Color.YELLOW);
				  rect[i][j] = status;
			  }
			  else {
				  FilledRect status = new FilledRect(i * cellSize, j * cellSize, cellSize , cellSize, canvas);
				  status.setColor(Color.GREEN);
				  rect[i][j] = status;

			  }
			  Grid g = new Grid(i * cellSize, j * cellSize, cellSize, canvas);
			  
		  }
	  } 
  }
  
  public static void setRectArray(int[][] Gen, FilledRect[][] rect) {
	  for(int i = 0; i < Gen.length; i++) {
		  for(int j = 0; j < Gen.length; j++) {
			if(Gen[i][j] == 1) {
				rect[i][j].setColor(Color.YELLOW);
			} else {
				rect[i][j].setColor(Color.GREEN);
			}
		  }
	  }
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
