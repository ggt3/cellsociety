import java.util.ArrayList;
import java.util.List;
/**
 * pretend game of life
 * @author GA
 *
 */

public class Grid {
	private Cell[][] myCells;
	
	public Grid(int row, int column) {
		myCells = new Cell[row][column]; //creates 2D array with dimensions
	}
	
	//returns 8 neighbors surrounding the cell at (row, col)
	public List<Cell> getAllNeighbors(int row, int col) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int [][] deltas = { {-1,0}, {-1,-1}, {-1,1}, {0,1}, {0,-1}, {1,0}, {1,1}, {1-1}}; //N, NW, NE, E, W, S, SE, SW
		for (int i = 0; i < deltas.length; i++) {
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (newR < myCells.length || newR >= 0 || newC < myCells[0].length || newC >= 0) { //only add neighbor if in bounds
				neighbors.add(myCells[newR][newC]);
			}
		}
		return neighbors;
		
	}
	
	//returns the neighbors that are NESW of the cell
	public List<Cell> getDirectNeighbors(int row, int col) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; //E S W N
		for (int i = 0; i < deltas.length; i++) {
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (newR < myCells.length || newR >= 0 || newC < myCells[0].length || newC >= 0) { //only add neighbor if in bounds
				neighbors.add(myCells[newR][newC]);
			}
		}
		return neighbors;
	}
	
	public void printGrid() {
		
	}
	public Cell getCell(int row, int col) {
		return myCells[row][col];
	}

	public void addCell(Cell target, int row, int col) {
		myCells[row][col] = target;
	}
//	public boolean inBounds(int row, int col) {
//		
//	}
	
}
