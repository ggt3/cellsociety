package model;

import java.util.ArrayList;
import java.util.List;

public class ToroidalGrid extends Grid{

	public ToroidalGrid(int row, int column) {
		super(row, column);
	}
	public List<Cell> getAllNeighbors(int row, int col) {
		List<Cell> neighbors = getDirectNeighbors(row,col);
		int [][] deltas = {{-1,-1}, {-1,1}, {1,1}, {1,-1}}; //W, NW, SW, S, N, E, SE, NE
		for (int i = 0; i < deltas.length; i++) {	
			int newR = row + deltas[i][0];
			int newC = col + deltas[i][1];
			if (inBounds(newR, newC)) { //only add neighbor if in bounds
				neighbors.add(getCell(newR,newC));
			}
			else if (newR == getGridRowSize() &&newC==getGridColSize()){
				neighbors.add(getCell(0,0));
			}
			else if (newR < 0 &&newC < 0){
				neighbors.add(getCell(getGridRowSize()-1, getGridColSize()-1));
			}
			else if (newR<0 && newC< getGridRowSize() && newC>=0) {
				neighbors.add(getCell(getGridRowSize() -1, newC));
			}
			else if (newC<0 && newR< getGridRowSize() && newR>=0) {
				neighbors.add(getCell(newR, getGridColSize() -1));
			}
			else if (newR == getGridRowSize() &&newC < 0){
				neighbors.add(getCell(0, getGridColSize()-1));
			}
			else if (newR < 0 &&newC==getGridColSize()){
				neighbors.add(getCell(getGridRowSize()-1, 0));
			}
			else if (newR ==getGridRowSize() && newC< getGridColSize() && newC>=0) {
				neighbors.add(getCell(0,newC));
			}
			else if (newC ==getGridColSize() && newR< getGridRowSize() && newR>=0) {
				neighbors.add(getCell(newR,0));
			}
			
		}
		return neighbors;	
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
			else if (newR == getGridRowSize()){
				neighbors.add(getCell(0, newC));
			}
			else if (newR < 0){
				neighbors.add(getCell(getGridRowSize()-1, newC));
			}
			else if (newC == getGridColSize()){
				neighbors.add(getCell(newR,0));
			}
			else if (newC < 0){
				neighbors.add(getCell(newR, getGridColSize()-1));
			}
		}
		
		return neighbors;
	}
	//make a deep copy of this Grid
	public Grid copyGrid() {
		Grid newCopy = new ToroidalGrid(this.getGridRowSize(), this.getGridColSize());
		for(int i = 0; i<this.getGridRowSize(); i++) {
			for (int j = 0; j <this.getGridColSize(); j++) {
				 newCopy.putCell(this.getCell(i,j).copyCell(), i, j); //need to make a copy of the cell not pass the reference
			}
		}
		return newCopy;
	}

}
