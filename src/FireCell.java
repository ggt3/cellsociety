/**
 * This cell has the attributes of the spreading fire simulation
 * @author GA
 *
 */
public class FireCell extends Cell {

	private CellState state;

	
	public FireCell(CellState given) {
		state = given;

	}
//	public void setThresholdLevel(double percent) {
//		threshHold = percent;
//	}
	public CellState returnStatus() {
		return state;
	}
	
	
	public CellState getState() {
		return state;
	}
}
