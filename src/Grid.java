import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * general grid structure
 * @author GA
 *
 */

public class Grid {
	private Cell[][] myCells;
	private HashMap<String, String> colorMapping;
	
	public Grid(int row, int column, HashMap<String, String> colorMap) {
		myCells = new Cell[row][column]; //creates 2D array with dimensions
		colorMapping = colorMap;
	}
	
	//returns 8 neighbors surrounding the cell at (row, col)
	public List<Cell> getAllNeighbors(int row, int col) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int [][] deltas = {{-1,0}, {-1,-1}, {-1,1}, {0,1}, {0,-1}, {1,0}, {1,1}, {1-1}}; //N, NW, NE, E, W, S, SE, SW
		for (int i = 0; i < deltas.length; i++) {
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
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
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
				neighbors.add(myCells[newR][newC]);
			}
		}
		return neighbors;
	}
	
	public List<Cell> getCellsWithState(CellState x) {
		List<Cell> stateList = new ArrayList<Cell>();
		for (int i = 0; i< myCells.length; i++) {
			for (int j = 0; j<myCells[0].length; j++) {
				if(getCell(i,j).getState().equals(x)) {
					stateList.add(getCell(i,j));
				}
			}
		}
		return stateList;
	}
	
	//TODO: public gridToColor()??
	
	public Cell getCell(int row, int col) {
		return myCells[row][col];
	}

	public void addCell(Cell target, int row, int col) {
		myCells[row][col] = target;
	}
	
	private boolean inBounds(int row, int col) {
		return row < myCells.length && row >= 0 && col < myCells[0].length && col >= 0;
	}

	public int getRowSize() {
		return myCells.length;
	}
	public int getColSize() {
		return myCells[0].length;
	}
	
	public String print() {
		return Arrays.toString(myCells);
	}
	
}
