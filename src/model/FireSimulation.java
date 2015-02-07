package model;
import java.util.List;


import controller.Packager;
/**
 * this class obtains a grid and applies the fire simulation rules and returns a new grid
 * @author GA
 *
 */

public class FireSimulation extends Simulation {
	
	public FireSimulation(Grid aGrid, Packager attributes) {
		super(aGrid, attributes);
		
	}
	
	//returns the new state of the cell at (row, col)
	public CellState calculateNewCellState(int row, int col) {
		List<Cell> neighbors = super.getCurrentGrid().getAllNeighbors(row, col);
		Cell theCell = super.getCurrentGrid().getCell(row, col);
		double myProb;
		if (super.getCurrentGrid().getCell(row, col).getState().equals(CellState.TREE)) {
			for (Cell aCell : neighbors) {
				if (aCell.getState().equals(CellState.BURNING)) {
					myProb = myGenerator.nextInt(101);
					if (myProb >= theCell.getKey("THRESHOLD")) {
						
						// tree will change state to burning
						return CellState.BURNING;
					}
				}
			}
			return theCell.getState(); //tree stays tree if not past threshold
		} 
		//else in the case of a burning tree it will become empty
		return CellState.EMPTY;
	}

	
}
