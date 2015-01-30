import java.util.Map;
/**
 * this abstract class will define a general structure for any type of simulation
 * @author GA
 *
 */

public abstract class Simulation {
	private Grid currentGrid, nextGrid;
	private Map<String, Object> constraints; //different depending on what simulation
	
	public Simulation(Grid aGrid, Map<String,Object> bundle) {
		this(aGrid);
		constraints = bundle;
	}
	
	public Simulation(Grid aGrid) {
		currentGrid = aGrid;
	}
	
	public Grid getCurrent() {
		return currentGrid;
	}
	
	//returning the next grid states
	public Grid makeNextGrid() {
		for (int r = 0; r< currentGrid.getRowSize(); r++) {
			for (int c = 0; c< currentGrid.getColSize(); c++) {
				CellState newState = calculateNewCellState(r,c);
				Cell target = new Cell(newState);
				nextGrid.addCell(target, r, c);
			}
		}
		currentGrid = nextGrid; //updating the grid stored in the simulation
		return nextGrid;
	}
	
	//for thresholds
	public double getKeyToDouble(String key) {
		return (double) constraints.get(key);
	}
	
	//for energy
	public int getKeyToInt(String key) {
		return (int) constraints.get(key);
	}
	
	//rules logic implemented here
	public abstract CellState calculateNewCellState(int row, int col);
	

	
}
