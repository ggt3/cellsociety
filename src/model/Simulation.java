package model;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

import controller.Packager;
/**
 * this abstract class will define a general structure for any type of simulation
 * @author GA
 *
 */

public abstract class Simulation {
	private Grid currentGrid, nextGrid;
	private Map<String, Integer> initialState;
	public static final Random myGenerator = new Random(1234); //seeding the random so we get consistent results
	
	public Simulation(Grid aGrid, Packager attributes) {
		currentGrid = aGrid;
		initialState = attributes.getPropertiesMap(); //stores the state to color map
		
	}


 	public Grid getCurrentGrid() {
		return currentGrid;
	}	
	public Grid getNextGrid() {
		return nextGrid;
	}
	public void setNextGrid(Grid x) {
		nextGrid = x;
	}
	//returning the next grid states
	public Grid makeNextGrid() {
		nextGrid = currentGrid.copyGrid(); //setting the next grid a new grid
		for (int r = 0; r< currentGrid.getRowSize(); r++) {
			for (int c = 0; c< currentGrid.getColSize(); c++) {
				CellState newState = calculateNewCellState(r,c);
				nextGrid.getCell(r, c).setState(newState); //changing the cell state in the new grid

			}
		}

		return nextGrid;
	}

	public void updateGrid(Grid x) {
		currentGrid = x;
	}
	//rules logic implemented here
	public abstract CellState calculateNewCellState(int row, int col);
	
	
}
