
public class Automata2D {

	static int row = 15;
	static int column = 25;
	
	//Create a new 2D array
	static int [][] firstGen = new int[column][row];
	
	//Create next generation of cells
	static int [][] nextGen = new int[column][row];
	
	public static void initializeArray() {
		int sumNeighbor; //Keeps track of sum of neighbor cells
		int r, c; //Used to get value of neighbor cells one away from cell
		
		//Initialize values in array
		for(int i = 0; i < firstGen.length; i++) {
			for(int j = 0; j < firstGen[i].length; j++) {
				firstGen[i][j] = (int)(Math.random() * 2);	
			}
		}
		
		
	/*	for(int i = 1; i < column - 1; i++){
			for(int j = 1; j < row - 1; j++) {
				
				sumNeighbor = 0; //Keeps track of sum of neighbor cells
				for(c = -1; c <= 1; c++) {
					for(r = -1; r <= 1; r++) {
							sumNeighbor += firstGen[c + i][r + j];
					}
				} 
				sumNeighbor -= firstGen[i][j]; //Do not need middle cell value
				//Set next 2d array values based on first gen's neighbors and game of life rules
				if(firstGen[i][j] == 1 && (sumNeighbor == 3 || sumNeighbor == 4)) {
					nextGen[i][j] = 1;
				} else if(firstGen[i][j] == 0 && sumNeighbor != 3) {
					nextGen[i][j] = 0;
				} else if(nextGen[i][j] == 0 && sumNeighbor == 3) {
					nextGen[i][j] = 1;
				} else
					nextGen[i][j] = 0; //Set edge cases
				
			}
		}

		
		//set firstGen equal to nextGen		
		for(int i = 0; i < column - 1; i++) {
			for(int j = 0; j < row -1 ; j++) {
				firstGen[i][j] = nextGen[i][j];
			}
		}*/
		
	}
}
