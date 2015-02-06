package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.Packager;


public class SegregationSimulation extends Simulation {
	private ArrayList<Cell> cellsToMove;
	private ArrayList<Point> emptyPlaces;
	
	public SegregationSimulation(Grid aGrid, Packager attributes) {
		super(aGrid, attributes);
		cellsToMove = new ArrayList<Cell>();
		emptyPlaces = new ArrayList<Point>();
	}
	
	//returns the new state of the cell at (row, col)
	public CellState calculateNewCellState(int row, int col) {
		List<Cell> neighbors = super.getCurrentGrid().getAllNeighbors(row, col);
		Cell theCell = super.getCurrentGrid().getCell(row, col);
		float count = 0;
		for (Cell aCell : neighbors) {
			if (aCell.getState() == super.getCurrentGrid().getCell(row, col).getState()) { //only add if same type
				count ++;
			}
		}
		
		if (count/neighbors.size() > theCell.getKeyToDouble("THRESHOLD") || theCell.getState() == CellState.EMPTY) { 
			return theCell.getState();
		} else {
			cellsToMove.add(theCell);
		}
		return CellState.EMPTY;
	}
	
	
	@Override 
	//cells need to physically move
	public Grid makeNextGrid() {
		Grid next = super.makeNextGrid(); //at this point cells that will move are empty state
		//adding a move cell method
		emptyPlaces.addAll(getCurrentGrid().getLocationsWithState(CellState.EMPTY));
		System.out.print(cellsToMove.toString());
		for (Cell move: cellsToMove) {
	//		if(emptyPlaces.size()> 0)
		//	{
			System.out.println(emptyPlaces.size());
			Point newLoc = emptyPlaces.get(myGenerator.nextInt(emptyPlaces.size())); // getting a random empty place
			next.putCell(move, newLoc.y, newLoc.x); //move the person to the new place
			emptyPlaces.remove(newLoc);
			//}
		}
		emptyPlaces.clear();
		cellsToMove.clear();
		return next;
	}

	
	
	
	
	
}
