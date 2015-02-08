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
	private Map<String, String> colors;
	public static final Random myGenerator = new Random(1234); //seeding the random so we get consistent results
	
	public Simulation(Grid aGrid, Packager attributes) {
		currentGrid = aGrid;
		colors = attributes.getColorMap(); //stores the state to color map
		
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
	
	//class that takes a grid of states and packages the grid of colors
	public Packager createColorGrid(Grid gridToReturn) {
		Packager colorGrid = new Packager();
		for (int r = 0; r< gridToReturn.getRowSize(); r++) {
			ArrayList<String> colorRow = new ArrayList<String>();
			for (int c = 0; c< gridToReturn.getColSize(); c++) {
				String state = gridToReturn.getCell(c, r).toString();
				colorRow.add(colors.get(state)); //gets the color

			}
			colorGrid.getColorGrid().add(colorRow); //add to double array list
		}
		return colorGrid;
	}
	
}
