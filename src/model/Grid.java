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
	//FINITE
	public List<Cell> getAllNeighbors(int row, int col) {
		List<Cell> neighbors = getDirectNeighbors(row,col);
		int [][] deltas = {{-1,-1}, {-1,1}, {1,1}, {1,-1}}; //W, NW, SW, S, N, E, SE, NE
		for (int i = 0; i < deltas.length; i++) {	
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
				neighbors.add(myCells[newR][newC]);
			}
		}
		//System.out.println(neighbors.toString() + " me: " + myCells[row][col].toString());
		return neighbors;	
	}
	//TOROIDAL
//	public List<Cell> getAllNeighbors(int row, int col) {
//		List<Cell> neighbors = getDirectNeighbors(row,col);
//		int [][] deltas = {{-1,-1}, {-1,1}, {1,1}, {1,-1}}; //W, NW, SW, S, N, E, SE, NE
//		for (int i = 0; i < deltas.length; i++) {	
//			int newR = row + deltas[i][0];
//			int newC = col + deltas[i][1];
//			if (inBounds(newR, newC)) { //only add neighbor if in bounds
//				neighbors.add(myCells[newR][newC]);
//			}
//			else if (newR == myCells.length&&newC==myCells[0].length){
//				neighbors.add(myCells[0][0]);
//			}
//			else if (newR < 0 &&newC < 0){
//				neighbors.add(myCells[myCells.length-1][myCells[0].length-1]);
//			}
//			else if (newR == myCells.length&&newC < 0){
//				neighbors.add(myCells[0][myCells[0].length-1]);
//			}
//			else if (newR < 0&&newC==myCells[0].length){
//				neighbors.add(myCells[myCells.length-1][0]);
//			}
//		}
		//System.out.println(neighbors.toString() + " me: " + myCells[row][col].toString());
//		return neighbors;	
//	}
	
	//returns the neighbors that are NESW of the cell
	//FINITE
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
	
	//TOROIDAL
//	public List<Cell> getDirectNeighbors(int row, int col) {
//		List<Cell> neighbors = new ArrayList<Cell>();
//		int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; //E S W N
//		 for (int i = 0; i < deltas.length; i++) {
//			int newR = row + deltas[i][0];
//			int newC = col + deltas[i][1];
//			if (inBounds(newR, newC)) { //only add neighbor if in bounds
//				neighbors.add(myCells[newR][newC]);	
//			}
//			else if (newR == myCells.length){
//				neighbors.add(myCells[0][newC]);
//			}
//			else if (newR < 0){
//				neighbors.add(myCells[myCells.length-1][newC]);
//			}
//			else if (newC == myCells[0].length){
//				neighbors.add(myCells[newR][0]);
//			}
//			else if (newC < 0){
//				neighbors.add(myCells[newR][myCells[0].length-1]);
//			}
//		}
//		return neighbors;
//	}

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
	public List<Point> getDirectNeighborsWithType(Point p, CellState c) {
		List<Point> neighbors = new ArrayList<Point>();
		int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; //E S W N
		 for (int i = 0; i < deltas.length; i++) {
			int newR = p.y + deltas[i][0];
			int newC = p.x + deltas[i][1];
			if (inBounds(newR, newC)) {
				if (myCells[newR][newC].getState().equals(c)) { // only add
																// neighbor if
																// in bounds
					neighbors.add(new Point(newC, newR));
				}
			}
		}
		return neighbors;
	
		
	}
	public List<Cell> getDirectNeighborsWithType(int row, int col, CellState c) {
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
	//make a deep copy of this Grid
	public Grid copyGrid() {
		Grid newCopy = new Grid(this.getRowSize(), this.getColSize());
		for(int i = 0; i<this.getRowSize(); i++) {
			for (int j = 0; j <this.getColSize(); j++) {
				 newCopy.putCell(this.getCell(i,j).copyCell(), i, j); //need to make a copy of the cell not pass the reference
			}
		}
		return newCopy;
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
			System.out.printf("row %d, %d, %s map: %s\n", i, k, myCells[i][k].toString(), myCells[i][k].getProperties().toString());
		}
	}

	
}
