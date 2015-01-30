package model;
import java.util.HashMap;
import java.util.Map;


public class Cell {
	private CellState state;
	private Map<String, Object> properties;
	
	public Cell(CellState setState) {
		state = setState;
		properties =new HashMap<String,Object>();
	} 

	public CellState getState() {
		return state;
	}
	
	//for if cell needs extra traits
	public void addProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	@Override
	public String toString() {
		return this.state.toString();
	}
	
	
}
