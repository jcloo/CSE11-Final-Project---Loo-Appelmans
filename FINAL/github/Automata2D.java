import objectdraw.*;

public class Automata2D extends ActiveObject {
	
	//Set the size of the matrix
	static int row = 15;
	static int column = 25;
	
	//Create a new 2D array (First generation of cells)
	static int [][] firstGen = new int[column][row];	
	//Create next generation of cells
	static int [][] nextGen = new int[column][row];
	
	//Initialize values in array 
	//Sets each element equal to a random binary
	public static void createFirstGen(int[][] fGen) {
		for(int i = 0; i < fGen.length; i++) {
			for(int j = 0; j < fGen[i].length; j++) {
				fGen[i][j] = (int)(Math.random() * 2);	
			}
		}
	}	
	
	/**Creates next generation of cells based of first generation of cells
	 *fGen = first generation
	 *nGen = next generation
	 *@return nGen
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
	}

		
		//set one array equal to the nextGeneration
	public static void setNextGen(int[][] fGen, int[][] nGen) {
		for(int i = 0; i < column - 1; i++) {
			for(int j = 0; j < row -1 ; j++) {
				fGen[i][j] = nGen[i][j];
			}
		}
	}
	
}

