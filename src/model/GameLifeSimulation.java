package model;

import java.util.List;
import controller.Packager;

/**
 * this class obtains a grid and applies the game of life simulation rules 
 * @author GA
 *
 */

public class GameLifeSimulation extends Simulation {

	public GameLifeSimulation(Grid aGrid, Packager attributes) {
		super(aGrid, attributes);

	}

	// returns the new state of the cell at (row, col)
	public CellState calculateNewCellState(int row, int col) {
		List<Cell> neighbors = super.getCurrentGrid().getAllNeighbors(row, col);
		CellState currentState = super.getCurrentGrid().getCell(row, col).getState();
		
		int countAlive =0; 
		for (Cell aCell : neighbors) {
			if (aCell.getState().equals(CellState.ALIVE)) {
				countAlive++;
			}
		}
		if (currentState.equals(CellState.ALIVE)) {
			if (countAlive < 2 || countAlive > 3) { // overcrowding and under-population
				return CellState.EMPTY;
			} else {
				// keeping the same state
				return CellState.ALIVE;
			}
		} else {
			if (countAlive == 3) { // reproduction
				return CellState.ALIVE;
			}
			// else in the case of a burning tree it will become empty
			return CellState.EMPTY;
		}

	}
}
