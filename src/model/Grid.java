package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;



/**
 * general grid structure that contains an array of cells
 * @author GA
 *
 */

public abstract class Grid {
	private Cell[][] myCells;
	
	
	public Grid(int row, int column) {
		myCells = new Cell[row][column]; //creates 2D array with dimensions
	}
	protected abstract List<Cell> getAllNeighbors(int row, int col);
	protected abstract List<Cell> getDirectNeighbors(int row, int col);
	
	protected ArrayList<Point> getLocationsWithState(CellState x) {
		ArrayList<Point> stateList = new ArrayList<Point>();
		for (int i = 0; i< myCells.length; i++) {
			for (int j = 0; j<myCells[0].length; j++) {
				if(getCell(i,j).getState().equals(x)) {
					stateList.add(new Point(j,i)); //adding col, row
				}
			}
		}
		return stateList;
	}
	protected List<Point> getDirectLocationsWithType(Point p, CellState c) {
		List<Point> neighbors = new ArrayList<Point>();
		int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; //E S W N
		 for (int i = 0; i < deltas.length; i++) {
			int newR = p.y + deltas[i][0];
			int newC = p.x + deltas[i][1];
			if (inBounds(newR, newC)) {
				if (myCells[newR][newC].getState().equals(c)) {
					neighbors.add(new Point(newC, newR));
				}
			}
		}
		return neighbors;
	
		
	}
	protected List<Cell> getDirectNeighborsWithType(int row, int col, CellState c) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; //E S W N
		 for (int i = 0; i < deltas.length; i++) {
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) {
				if (myCells[newR][newC].getState().equals(c)) { 
					
					neighbors.add(myCells[newR][newC]);
				}
			}
		}
		return neighbors;
	
		
	}
	protected abstract Grid copyGrid();
	
	public Cell getCell(int row, int col) {
		return myCells[row][col];
	}

	public void putCell(Cell target, int row, int col) {
		myCells[row][col] = target;
	}
	
	protected boolean inBounds(int row, int col) {
		return row < myCells.length && row >= 0 && col < myCells[0].length && col >= 0;
	}

	public int getGridRowSize() {
		return myCells.length;
	}
	public int getGridColSize() {
		return myCells[0].length;
	}
	
	public void print() {
		for(int i = 0; i<myCells.length;i++) {
			for(int k = 0; k<myCells[0].length; k++)
			System.out.printf("row %d, %d, %s map: %s\n", i, k, myCells[i][k].toString(), myCells[i][k].getProperties().toString());
		}
	}

	
}
