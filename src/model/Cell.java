package model;

import java.util.Map;

import controller.Packager;

/**
 * general cell class 
 * @author GA
 * 
 */

public class Cell {
	private CellState state;
	private Map<String, Object> properties;
	
	//only importing attributes if a cell has other traits
	public Cell(CellState setState, Packager attributes) {
		this(setState);
		properties =attributes.getMap(); 
	}
	
	public Cell(CellState setState) {
		state = setState;
		
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
	
	
	//for thresholds
	public double getKeyToDouble(String key) {
		return (double) properties.get(key);
	}
	
	//for energy
	public int getKeyToInt(String key) {
		return (int) properties.get(key);
	}
	
}
