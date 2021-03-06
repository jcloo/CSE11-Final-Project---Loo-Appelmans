# CSE11-Final-Project---Loo-Appelmans

Authors: Madeleine Appelmans, Joel Loo
Tutors: Neil Sengupta. Marco Flowers

To compile: javac -cp ./objectdraw.jar:./Acme.jar:. DrawnGrid.java
To run: java -cp ./objectdraw.jar:./Acme.jar:. DrawnGrid

About: For our Final Project we recreated Conway's Game of Life using cellular automata. This is a simple model of computation using a grid with cells. Each cell has a state of either 1, meaning it is alive, or 0, meaning it is dead. The grid constantly regenerates and certain rules are set up to specifically create the game of life. The cell value for the next generation is determined by the sum of the cell's neighbors in the previous generation. The specific rules we have set up for this grid come from the famous simulation, Conway's Game of Life. They state that if a cell has three or four neighbors, it is alive in the next generation. If it has any more or any less, it is dead in the next generation. To run the simulation, start the code, and you will see a grid pop up with yellow cells representing alive cells and green cells representing dead cells. Each cell is initialized to a random binary number. When you press the Run button, you will see each generation appear based on the previous generation. You can use the Pause button to examine each generation closer. Remember to press the Resume button after the Pause button if you want the simulation to run again. What you will see is that as the grid clears up, certain patterns will appear in the center of the grid. Some are static and some oscilate. Press restart to explore which patterns appear out of the chaos in the beginning. Have fun!
