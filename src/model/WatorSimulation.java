package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.Packager;

public class WatorSimulation extends Simulation {
	private ArrayList<Point> allSharks, allFish;
	private Packager attributes;
	
	//if ENERGY == 0, shark is dead
	//if you reach the breed cycle number, you make a baby
	
	public WatorSimulation(Grid aGrid, Packager settings) {
		super(aGrid, settings);
		attributes = settings; //colormap
	}

	public CellState calculateNewCellState(int row, int col) {
		return null;
	}
	
	@Override
	public Grid makeNextGrid() {
		setNextGrid(getCurrentGrid().copyGrid()); //making the next grid a deep copy of original
		getCurrentGrid().print();
		allSharks = getCurrentGrid().getLocationsWithState(CellState.SHARK); //keeps reference to the current grid
		allFish = getCurrentGrid().getLocationsWithState(CellState.FISH);
		sharkAction();
		fishAction();
		getNextGrid().print();
		allSharks.clear();
		allFish.clear();
		return getNextGrid();
	}
	
	private void sharkAction() {
		Iterator<Point> it = allSharks.iterator();
        while(it.hasNext()){
            Point loc = it.next();
			System.out.printf("cell before %d, %d, %s %s\n", loc.y, loc.x, getCurrentGrid().getCell(loc.y, loc.x).toString(),getCurrentGrid().getCell(loc.y, loc.x).getProperties().toString());
			if 
			(getCurrentGrid().getCell(loc.y, loc.x).getKey("ENERGY") == 0) {
				removeCreature(allSharks, loc);
			} else { //shark did not die, want to find all the fish
				List<Point> fishNeighbors = getCurrentGrid().getDirectNeighborsWithType(loc, CellState.FISH); 
				if (fishNeighbors.size() >= 1) { //if there is at least one fish, eat it
					Point fish = fishNeighbors.get(myGenerator.nextInt(fishNeighbors.size()));
					removeCreature(allFish, fish); 
					allFish.remove(fish);
					moveAndCheckBaby(loc, fish, CellState.SHARK);
				} else { //shark loses a energy
					decreaseEnergy(loc.y, loc.x); //decrease the energy of that cell
					moveIfEmptyAndCheckBaby(loc, CellState.SHARK);
				}
			}
			System.out.printf("cell after %d, %d, %s %s\n", loc.y, loc.x, getNextGrid().getCell(loc.y, loc.x).toString(),getNextGrid().getCell(loc.y, loc.x).getProperties().toString());
		
        }
        
	}
	
	private void moveIfEmptyAndCheckBaby(Point loc, CellState babyType) {
		List<Point> emptyNeighbors = getNextGrid().getDirectNeighborsWithType(loc, CellState.EMPTY);
		if (!emptyNeighbors.isEmpty()) { // want to move to empty spot
			Point empty = emptyNeighbors.get(myGenerator.nextInt(emptyNeighbors.size()));
			moveAndCheckBaby(loc, empty, babyType);
		}
		else {
			increaseBreedingTime(loc.y, loc.x);
		}
	}

	//moves cell to new location and checks if can reproduce
	private void moveAndCheckBaby(Point loc, Point newLoc, CellState babyType) {
		boolean reproduce = canReproduce(loc);
		System.out.printf("moving cell %s %d, %d to cell %s %d %d\n", getNextGrid().getCell(loc.y, loc.x), loc.y, loc.x,getNextGrid().getCell(newLoc.y, newLoc.x), newLoc.y, newLoc.x );
		moveCreature(loc.y, loc.x, newLoc.y, newLoc.x);
		if (reproduce) {
			makeBaby(babyType, loc); // baby put in original location
			resetBreedTime(newLoc.y, newLoc.x); //reset breeding time of the parent
			return;
		}
		increaseBreedingTime(newLoc.y, newLoc.x);
	}
	

	private void fishAction() {
		for (Point loc : allFish) {
			System.out.printf("cell before %d, %d, %s %s\n", loc.y, loc.x, getCurrentGrid().getCell(loc.y, loc.x).toString(),getCurrentGrid().getCell(loc.y, loc.x).getProperties().toString());
			moveIfEmptyAndCheckBaby(loc, CellState.FISH);
			System.out.printf("cell after %d, %d, %s %s\n", loc.y, loc.x, getNextGrid().getCell(loc.y, loc.x).toString(),getNextGrid().getCell(loc.y, loc.x).getProperties().toString());
		}
	}
	//changing the property in the cell
	private void increaseBreedingTime(int row, int col) {
		getNextGrid().getCell(row, col).getProperties().put("BREEDCYCLE", getNextGrid().getCell(row, col).getProperties().get("BREEDCYCLE") + 1);
		
	}
	//move the cell from location y1, x1 to location y2, x2
	private void moveCreature(int y1, int x1, int y2, int x2) {
		getNextGrid().putCell(getCurrentGrid().getCell(y1, x1), y2, x2);
		getNextGrid().getCell(y1, x1).changeToEmptyCell();
		
	}

	private void decreaseEnergy(int row, int col) {
		getCurrentGrid().getCell(row, col).getProperties().put("ENERGY", getNextGrid().getCell(row, col).getProperties().get("ENERGY") - 1);
		System.out.printf("cell state of decreasing: %s\n",getNextGrid().getCell(row, col).getState().toString());
	}
	//put empty cell on the grid
	private void removeCreature(List<Point> list, Point p) {
//		Iterator<Point> it = list.iterator();
//        while(it.hasNext()){
//            if (it.next().equals(p)) {
//            	it.remove();
//            }
//        }
		getNextGrid().getCell(p.y, p.x).changeToEmptyCell();
		//list.remove(p);
		System.out.printf("removing %d %d\n", p.y, p.x);
		System.out.print(list.toString()+"\n");
	}
	
	private void resetBreedTime(int row, int col) {
		getNextGrid().getCell(row, col).getProperties().put("BREEDCYCLE", 0);
	}
	
	//creates baby at location and resents parent cycle
	private void makeBaby(CellState c, Point loc) {
		getNextGrid().putCell(new Cell(c, getCurrentGrid().getCell(loc.y, loc.x).getProperties()), loc.y, loc.x);
		
	}
	//returns true if this cell can reproduce
	private boolean canReproduce(Point creature) {
		Cell c = getCurrentGrid().getCell(creature.y, creature.x);
		return c.getKey("BREEDTHRESHOLD") <= c.getKey("BREEDCYCLE");
	}

}
