import java.awt.*;
import objectdraw.*;

/**
 * Class makes an instance of a grid piece, defining how it works with a cell.
 * @author Joel Loo, Madeleine Appelmans
 * @email jcloo@ucsd.edu, mappelma@ucsd.edu
 * @pid a12623303, a13101416
 */

public class Grid extends ActiveObject {
	
	//status of a cell. is it alive or dead?
	public static boolean cellStatus = false;
	
	// FramedRect object used for Grid
	private FramedRect fr;
	
    /**
     * Creates a FramedRect object, which will have the "cells" inside of it
     * @param x X-coordinate of FramedRect
     * @param y Y-coordinate of FramedRect
     * @param size Size of the FramedRect
     * @param canvas Defines what DrawingCanvas object the Grid is shown on
     */
    public Grid (int x, int y, int size, DrawingCanvas canvas) {
       fr = new FramedRect (x, y, size, size, canvas);
       fr.setColor(Color.GRAY);
    } // end of constructor Grid
  
    /**
     * Creates the board of Grid objects, and creates the cells inside the Grid
     * with either the status of dead or alive
     * @param Gen 2D array which holds the status of the cell (dead/alive)
     * @param rect 2D array which will hold all FilledRect objects and colors
     * @param cellSize Defines the size of each cell
     * @param canvas Defines what DrawingCanvas object the board is shown on
     */
    public static void createBoard(int[][] Gen, FilledRect[][] rect,
    		           int cellSize, DrawingCanvas canvas) {
    	
    	//nested for loop to place cells on screen
    	for(int i = 0; i < Gen.length; i++) {
    		for(int j = 0; j < Gen[i].length; j++) {
    			if(Gen[i][j] == 1) { //if a cell is alive (value of 1)
    				FilledRect status = new FilledRect(i * cellSize, 
    						   j * cellSize, cellSize , cellSize, canvas);
    				status.setColor(Color.YELLOW); //signifies an "alive" cell
    				rect[i][j] = status; //saves the FilledRect and its color
    			}
    			else { // if a cell is dead (value of 0)
    				FilledRect status = new FilledRect(i * cellSize,
    						   j * cellSize, cellSize , cellSize, canvas);
    				status.setColor(Color.GREEN); //signifies a "dead" cell
    				rect[i][j] = status; //saves the FilledRect and its color
    			}
    			//places a FramedRect in the same spot
    			Grid g = new Grid(i * cellSize, j * cellSize, cellSize,
    					 canvas);	  
    		}
    	} 
    } //end of method createBoard
    
    /**
     * Used to change the status of a cell based on the values of next
     * generation.
     * @param Gen 2D Array of values of the next generation.
     * @param rect 2D Array of FilledRect objects, which change color depending
     */
    public static void setRectArray(int[][] Gen, FilledRect[][] rect) {
    	//nested for loop
    	for(int i = 0; i < Gen.length; i++) {
    		for(int j = 0; j < Gen.length; j++) {
    			if(Gen[i][j] == 1) { //if cell is now alive
    				rect[i][j].setColor(Color.YELLOW);
    			} else { //if cell is now dead
    				rect[i][j].setColor(Color.GREEN);
    			}
    		}
	 	}
    } // end of method setRectArray
  
} // end of class Grid