package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.Packager;

public class WatorSimulation extends Simulation {
	private ArrayList<Point> allSharks, allFish;
	private Random myRand = new Random(1234);
	private Packager properties;
	
	public WatorSimulation(Grid aGrid, Packager attributes) {
		super(aGrid, attributes);
		properties = attributes;
		
	}

	public CellState calculateNewCellState(int row, int col) {
		return null;
	}
	
	@Override
	public Grid makeNextGrid() {
		setNextGrid(getCurrentGrid().copyGrid());
		getCurrentGrid().print();
		allSharks = getCurrentGrid().getCellsWithState(CellState.SHARK); //keeps reference to the current grid
		allFish = getCurrentGrid().getCellsWithState(CellState.FISH);
		sharkAction();
		fishAction();
		return getNextGrid();
	}
	private void sharkAction() {
		for (Point loc : allSharks) {
			System.out.println("cell " + loc.toString());
			if (getCurrentGrid().getCell(loc.y, loc.x).getKey("ENERGY") == 0) {
				removeCreature(allSharks, loc);
			} else {
				List<Point> fishNeighbors = getCurrentGrid().getNeighborsWithType(loc, CellState.FISH);
				if (fishNeighbors.size() >= 1) {
					Point fish = fishNeighbors.get(myRand.nextInt(fishNeighbors.size())); // eat fish and move shark to place
					removeCreature(allFish, loc);
					allFish.remove(loc);
					boolean reproduce = canReproduce(loc);
					moveCreature(loc.y, loc.x, fish.y, fish.x);
					if (reproduce) {
						makeBaby(CellState.SHARK, loc); // babysharkput in
														// original location
						resetBreedTime(fish.y, fish.x);
					} else {
						increaseBreedingTime(fish.y, fish.x);
					}

				} else {
					List<Point> emptyNeighbors = getCurrentGrid().getNeighborsWithType(loc, CellState.EMPTY);
					decreaseEnergy(loc.y, loc.x);
					if (!emptyNeighbors.isEmpty()) { // want to move to empty
														// spot
						Point empty = emptyNeighbors.get(myRand.nextInt(emptyNeighbors.size()));

						boolean reproduce = canReproduce(loc);
						moveCreature(loc.y, loc.x, empty.y, empty.x);
						if (reproduce) {
							makeBaby(CellState.SHARK, loc); // babysharkput in
															// original location
							resetBreedTime(empty.y, empty.x);

						} else {

							increaseBreedingTime(empty.y, empty.x);
						}

					} else { // no places to move

						increaseBreedingTime(loc.y, loc.x);
					}
				}
			}
		}
	}


	private void fishAction() {
		for (Point loc : allFish) {
			List<Point> emptyNeighbors = getCurrentGrid().getNeighborsWithType(loc, CellState.EMPTY);
			if (!emptyNeighbors.isEmpty()) { //you have empty neighbors
				Point empty = emptyNeighbors.get(myRand.nextInt(emptyNeighbors.size()));
				boolean reproduce = canReproduce(loc);
				moveCreature(loc.y, loc.x, empty.y, empty.x);
				if (reproduce) {
					makeBaby(CellState.FISH, loc); // babysharkput in
													// original location
					resetBreedTime(empty.y, empty.x);
				} else {
					increaseBreedingTime(empty.y, empty.x);
				}
			} else {
				increaseBreedingTime(loc.y, loc.x);
			}
		}
	}
	private void increaseBreedingTime(int row, int col) {
		getNextGrid().getCell(row, col).getProperties().put("BREEDCYCLE", getNextGrid().getCell(row, col).getKey("BREEDCYCLE") + 1);
		
	}

	private void moveCreature(int y1, int x1, int y2, int x2) {
		getNextGrid().putCell(getNextGrid().getCell(y1, x1), y2, x2);
		getNextGrid().putCell(new Cell(CellState.EMPTY, properties), y1, x1);
		
		
	}

	private void decreaseEnergy(int row, int col) {
		getNextGrid().getCell(row, col).getProperties().put("ENERGY", getNextGrid().getCell(row, col).getKey("ENERGY") - 1);
		
	}

	private void removeCreature(List<Point> l, Point p) {
		getNextGrid().putCell(new Cell(CellState.EMPTY, properties), p.y, p.x);
		
		
	}
	private void resetBreedTime(int row, int col) {
		getNextGrid().getCell(row, col).getProperties().put("BREEDCYCLE", 0);
	}
	
	//creates baby at location and resents parent cycle
	private void makeBaby(CellState c, Point loc) {
		getNextGrid().putCell(new Cell(c, properties), loc.y, loc.x);
		
	}
	private boolean canReproduce(Point creature) {
		Cell c= getCurrentGrid().getCell(creature.y, creature.x);
		return c.getKey("BREEDTHRESHOLD") <= c.getKey("BREEDCYCLE");
	}

}
