package model;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.Packager;
/**
 * this class obtains a grid and applies the fire simulation rules and returns a new grid
 * @author GA
 *
 */

public class FireSimulation extends Simulation {
	private Random myGenerator = new Random(1234);
	
	public FireSimulation(Grid aGrid, Packager attributes) {
		super(aGrid, attributes);
		
	}
	
	//returns the new state of the cell at (row, col)
	public CellState calculateNewCellState(int row, int col) {
		List<Cell> neighbors = super.getCurrent().getAllNeighbors(row, col);
		Cell theCell = super.getCurrent().getCell(row, col);
		double myProb;
		if (super.getCurrent().getCell(row, col).getState().equals(CellState.TREE)) {
			for (Cell aCell : neighbors) {
				if (aCell.getState().equals(CellState.BURNING)) {
					myProb = myGenerator.nextDouble();
					if (myProb >= theCell.getKeyToInt("THRESHOLD")) {
						// tree will change state to burning
						return CellState.BURNING;
					}
				}
			}
		}
		//else in the case of a burning tree it will become empty
		return CellState.EMPTY;
	}
	
	
	
}