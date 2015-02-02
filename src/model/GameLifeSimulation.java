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
		List<Cell> neighbors = getCurrentGrid().getAllNeighbors(row, col);
		CellState currentState = getCurrentGrid().getCell(row, col).getState();
		int countAlive = 0; //how many neighbors you have alive
		for (Cell aCell : neighbors) {
			if (aCell.getState().equals(CellState.ALIVE)) {
				countAlive++;
			}
		}
		if (currentState.equals(CellState.ALIVE)) {
			if (countAlive < 2 || countAlive > 3) { // overcrowding and under-population
				return CellState.EMPTY;
			}
		} else { //you are dead
			if (countAlive == 3) { // reproduction
				return CellState.ALIVE;
			}
		}
		return currentState;
	}
}
