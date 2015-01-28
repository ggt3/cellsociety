import java.util.List;
import java.util.Random;


public class FireRule {
	private Grid currentGrid;
	private static double THRESHOLD =  0.4;
	private Random myGenerator = new Random(1234);
	
	public FireRule(Grid aGrid) {
		currentGrid = aGrid;
	}
	

	public CellState calculateNewState(int row, int col) {
		List<Cell> neighbors = currentGrid.getAllNeighbors(row, col);
		double myProb;
		if (currentGrid.getCell(row, col).getState() == CellState.TREE) {
			for (Cell aCell : neighbors) {
				if (aCell.getState() == CellState.BURNING) {
					myProb = myGenerator.nextDouble();
					if (myProb >= THRESHOLD) {
						// change state to burning
						return CellState.BURNING;
					}
				}
			}
		}
		return CellState.EMPTY;
			
	} 
		
	
}
