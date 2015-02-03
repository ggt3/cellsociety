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
	private Map<String, Integer> properties;
	
	//only importing attributes if a cell has other traits
	public Cell(CellState setState, Packager attributes) {
		state = setState;
		properties = attributes.getPropertiesMap(); 
	}
	
	public Cell copyCell() {
		Packager p = new Packager();
		p.setPropertiesMap(this.getProperties());
		return new Cell(this.getState(), p); //TODO: NEED TO COPY PROPERTIES IF TherE IS ANY
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

	//returning the values in the map for thresholds
	public double getKeyToDouble(String key) {
		return (double) properties.get(key);
	}
	
	//returning the values in the map for energy
	public Integer getKey(String key) {
		return properties.get(key);
	}
	
}
