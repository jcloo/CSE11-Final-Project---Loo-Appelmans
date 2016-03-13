import objectdraw.FilledRect;

/**
 * Class holds the values of cells (alive or dead) and holds rules for how
 * cells interact.
 * @author Joel Loo, Madeleine Appelmans
 * @email jcloo@ucsd.edu, mappelma@ucsd.edu
 * @pid a12623303, a13101416
 */

public class Automata2D {
	
	//Set the size of the matrix
	static int row = 30;
	static int column = 30;
	
	//Create a new 2D array (First generation of cells)
	static int [][] firstGen = new int[column][row];	
	//Create next generation of cells
	static int [][] nextGen = new int[column][row];
	//Create an array to hold rectangle objects
	static FilledRect[][] rectArray = new FilledRect[column][row];
	
	
	/**
	 * Initializes values in array, sets each element equal to a random binary.
	 * @param fGen Represents the 2D array which will hold the values.
	 */
	public static void createFirstGen(int[][] fGen) {
		//nested for loop
		for(int i = 0; i < fGen.length; i++) {
			for(int j = 0; j < fGen[i].length; j++) {
				// sets to a random 1 or 0 value
				fGen[i][j] = (int)(Math.random() * 2);	
			}
		}
	} //end of method createFirstGen	
	
	/**
	 * Creates next generation of cells based of first generation of cells
	 * @param fGen Previous generation of cells
	 * @param nGen Next generation of cells
	 * @return Returns nGen
	 */
	public static int[][] createNextGen(int[][] fGen, int[][] nGen){
		int sumNeighbor; //Keeps track of sum of neighbor cells
		int r, c; //Used to get value of neighbor cells one away from cell
		
		for(int i = 1; i < column - 1; i++){
			for(int j = 1; j < row - 1; j++) {
				
				sumNeighbor = 0; //Keeps track of sum of neighbor cells
				for(c = -1; c <= 1; c++) {
					for(r = -1; r <= 1; r++) {
						sumNeighbor += fGen[c + i][r + j];
					}
				} 
				
				sumNeighbor -= fGen[i][j]; //Do not need middle cell value
				//Set next 2d array values based on first gen's neighbors and game of life rules
				if(fGen[i][j] == 1 && (sumNeighbor == 3 || sumNeighbor == 4)) {
					nGen[i][j] = 1;
				} else if(fGen[i][j] == 0 && sumNeighbor != 3) {
					nGen[i][j] = 0;
				} else if(nGen[i][j] == 0 && sumNeighbor == 3) {
					nGen[i][j] = 1;
				} else {
					nGen[i][j] = 0; //Set edge cases
				}
				
			}
		}
		return nGen;
	} // end of method createNextGen

	/**
	 * Sets one array equal to the next array/next generation.
	 * @param fGen Previous generation of values
	 * @param nGen Next generation of values
	 */
	public static void setNextGen(int[][] fGen, int[][] nGen) {
		for(int i = 0; i < column - 1; i++) {
			for(int j = 0; j < row -1 ; j++) {
				fGen[i][j] = nGen[i][j];
			}
		}
	} // end of method setNextGen
	
} // end of class Automata2D