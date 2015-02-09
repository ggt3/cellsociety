package model;

import java.util.ArrayList;
import java.util.List;
/**
 * finite grid edge where there is no wrap around grid edges
 * @author GA
 *
 */
public class FiniteGrid extends Grid {

	public FiniteGrid(int row, int column) {
		super(row, column);
	}
	public List<Cell> getDirectNeighbors(int row, int col) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; //E S W N
		 for (int i = 0; i < deltas.length; i++) {
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
				neighbors.add(getCell(newR, newC));
			}
		}
		return neighbors;
	}
	public List<Cell> getAllNeighbors(int row, int col) {
		List<Cell> neighbors = getDirectNeighbors(row,col);
		int [][] deltas = {{-1,-1}, {-1,1}, {1,1}, {1,-1}}; //W, NW, SW, S, N, E, SE, NE
		for (int i = 0; i < deltas.length; i++) {	
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
				neighbors.add(getCell(newR, newC));
			}
		}
		return neighbors;	
	}
	//make a deep copy of this Grid
	public Grid copyGrid() {
		Grid newCopy = new FiniteGrid(this.getGridRowSize(), this.getGridColSize());
		for(int i = 0; i<this.getGridRowSize(); i++) {
			for (int j = 0; j <this.getGridColSize(); j++) {
				 newCopy.putCell(this.getCell(i,j).copyCell(), i, j); //need to make a copy of the cell not pass the reference
			}
		}
		return newCopy;
	}
}
