package model;

import java.util.HashMap;
import java.util.Map;


/**
 * general cell class 
 * @author GA
 * 
 */

public class Cell {
	private CellState state;
	private Map<String, Integer> properties;
	
	//only importing attributes if a cell has other traits
	public Cell(CellState setState, Map<String, Integer> attributes) {
		state = setState;
		properties = attributes;
	}
	
	public Cell copyCell() {
		Map<String, Integer> newM = new HashMap<String,Integer>(properties);
		return new Cell(this.getState(), newM); //for copying all properties of the cell
	}
	public void changeToEmptyCell(){
		this.state = CellState.EMPTY;
	}
	
	public Map<String, Integer> getProperties() {
		return properties;
	}
	public CellState getState() {
		return state;
	}
	public void setState(CellState cs) {
		state = cs;
	}

	@Override
	//if you want to print out a cell, it is the state
	public String toString() {
		return this.state.toString();
	}

	//returning the values in the map for energy
	public Integer getKey(String key) {
		return properties.get(key);
	}
	
}
