package model;

import java.util.List;
import java.util.Random;

import controller.Packager;


public class SegregationSimulation extends Simulation {
	private Random myGenerator = new Random(1234);

	public SegregationSimulation(Grid aGrid, Packager attributes) {
		super(aGrid, attributes);
	}
	
	//returns the new state of the cell at (row, col)
	public CellState calculateNewCellState(int row, int col) {
		List<Cell> neighbors = super.getCurrent().getAllNeighbors(row, col);
		Cell theCell = super.getCurrent().getCell(row, col);
		float count = 0;
		for (Cell aCell : neighbors) {
			if (aCell.getState() == super.getCurrent().getCell(row, col).getState()) {
				count ++;
			}
		}
		if (count/neighbors.size() < theCell.getKeyToDouble("THRESHOLD")) {

			return theCell.getState();
			
		}
		//TODO: ACCOUNT FOR MOVING THE CELL TO NEW LOCATION
		
		return CellState.EMPTY;
	}
	
	
	
	
	
	
	
}
