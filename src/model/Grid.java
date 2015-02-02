package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;



/**
 * general grid structure that contains an array of cells
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
		int [][] deltas = {{-1,0}, {-1,-1}, {-1,1}, {0,1}, {0,-1}, {1,0}, {1,1}, {1,-1}}; //W, NW, SW, S, N, E, SE, NE
		for (int i = 0; i < deltas.length; i++) {	
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
				neighbors.add(myCells[newR][newC]);
			}
		}
		System.out.println(neighbors.toString() + " me: " + myCells[row][col].toString());
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
	
	public ArrayList<Point> getLocationsWithState(CellState x) {
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
	

	
	public Cell getCell(int row, int col) {
		return myCells[row][col];
	}

	public void putCell(Cell target, int row, int col) {
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
	
	public void print() {
		for(int i = 0; i<myCells.length;i++) {
			for(int k = 0; k<myCells[0].length; k++)
			System.out.printf("row %d, %d, %s\n", i, k, myCells[i][k].toString());
		}
	}

	
}
