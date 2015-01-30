import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class SegregationSimulation extends Simulation {
	//threshold of the number of people around is same
	private Random myGenerator = new Random(1234);
	
	public SegregationSimulation(Grid aGrid, HashMap<String,Object> param) {
		super(aGrid, param);
	}
	
	//returns the new state of the cell at (row, col)
	public CellState calculateNewCellState(int row, int col) {
		List<Cell> neighbors = super.getCurrent().getAllNeighbors(row, col);
		
		float count = 0;
		for (Cell aCell : neighbors) {
			if (aCell.getState() == super.getCurrent().getCell(row, col).getState()) {
				count ++;
			}
		}
		if (count/neighbors.size() < super.getKeyToDouble("THRESHOLD")) {
			Cell theCell = super.getCurrent().getCell(row, col);
			return theCell.getState();
			
		} 
		//TODO: ACCOUNT FOR MOVING THE CELL TO NEW LOCATION
		
		return CellState.EMPTY;
	}
	
	
	
	
	
	
}
