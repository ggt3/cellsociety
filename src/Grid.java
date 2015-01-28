import java.util.ArrayList;
import java.util.List;
/**
 * 
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
		neighbors.add(myCells[row - 1][col]); //N cell
		neighbors.add(myCells[row - 1][col - 1]); //NW cell
		neighbors.add(myCells[row - 1][col + 1]); //NE cell
		neighbors.add(myCells[row][col + 1]); //E cell
		neighbors.add(myCells[row][col - 1]); //W cell
		neighbors.add(myCells[row + 1][col]); //S cell
		neighbors.add(myCells[row + 1][col + 1]); //SE cell
		neighbors.add(myCells[row + 1][col - 1]); //SW cell
		return neighbors;
		
	}
	
	//returns the neighbors that are NESW of the cell
	public List<Cell> getDirectNeighbors(int row, int col) {
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(myCells[row - 1][col]); //N cell
		neighbors.add(myCells[row][col + 1]); //E cell
		neighbors.add(myCells[row][col - 1]); //W cell
		neighbors.add(myCells[row + 1][col]); //S cell
		return neighbors;
	}
	
	public void addCell(Cell target, int row, int col) {
		myCells[row][col] = target;
	}
	
}
