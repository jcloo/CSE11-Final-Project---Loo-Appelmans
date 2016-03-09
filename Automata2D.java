
public class Automata2D {
	
	static int row = 10;
	static int column = 10;
	
	//Method prints out array 
	public static void printArray(int[][] array) {
		for(int i = 0; i < column - 1; i++) {
			for(int j = 0; j < row -1 ; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void main(String[]args) {
		int sumNeighbor; //Keeps track of sum of neighbor cells
		int r, c; //Used to get value of neighbor cells one away from cell
		
		//Create a new 2D array
		int [][] firstGen = new int[column][row];
		
		//Create next generation of cells
		int [][] nextGen = new int[column][row];
		
		//Initialize values in array
		for(int i = 0; i < firstGen.length; i++) {
			for(int j = 0; j < firstGen[i].length; j++) {
				firstGen[i][j] = (int)(Math.random() * 2);	
			}
		}
		int foo = 0;
		
		while(foo <= 5) {
		
		for(int i = 1; i < column - 1; i++){
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
		//print out firstGen
		printArray(firstGen);
		//print out nextGen
		printArray(nextGen);
		
		//set firstGen equal to nextGen		
		for(int i = 0; i < column - 1; i++) {
			for(int j = 0; j < row -1 ; j++) {
				firstGen[i][j] = nextGen[i][j];
			}
		}
		
		foo++;
		
		} //End of while loop	
	}
	
	
}
