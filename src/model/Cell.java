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
		properties = attributes.getMap(); 
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
	//if you want to print out a cell, it is the state
	public String toString() {
		return this.state.toString();
	}
	
	
	//returning the values in the map for thresholds
	public double getKeyToDouble(String key) {
		return (double) properties.get(key);
	}
	
	//returning the values in the map for energy
	public int getKeyToInt(String key) {
		return (int) properties.get(key);
	}
	
}
