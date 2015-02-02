package model;
import java.util.Map;
import java.util.ArrayList;
import controller.Packager;
/**
 * this abstract class will define a general structure for any type of simulation
 * @author GA
 *
 */

public abstract class Simulation {
	private Grid currentGrid, nextGrid;
	private Map<String, String> colors;
	
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
		nextGrid = new Grid(currentGrid.getRowSize(),currentGrid.getColSize()); //setting the next grid a copy of the original
		currentGrid.toString();
		for (int r = 0; r< currentGrid.getRowSize(); r++) {
			for (int c = 0; c< currentGrid.getColSize(); c++) {
				CellState newState = calculateNewCellState(r,c);
				Cell target = new Cell(newState);
				nextGrid.putCell(target, r, c);
			}
		}
		//currentGrid = nextGrid; //updating the grid stored in the simulation
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
				System.out.println("getRowSize:       "+gridToReturn.getRowSize());
				System.out.printf("on row %d %d %s \n", c, r, state);
			}
			System.out.println(colorRow);
			colorGrid.getColorGrid().add(colorRow); //add to double array list
		}
		return colorGrid;
	}
	
}
