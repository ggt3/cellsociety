package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.Grid;
//this entire file is part of my masterpiece
//Georgia

/**
 * this packager contains information the view needs: generation count, the 
 * grid of colors, the totals of each color
 * @author GA
 *
 */
public class ViewPackager {
	private Map<String, String> stateColorMap;
	private ArrayList<ArrayList<String>> colorGrid;
	private Map<String, Integer> stateTotals;
	
	public ViewPackager(Map<String, String> colorMap) {
		stateColorMap = colorMap;
	}
	public void update(Grid next) {
		updateStateTotals(next);
		createColorGrid(next);
	}

	public ArrayList<ArrayList<String>> getColorGrid() {
		return colorGrid;
	}
	
	public Map<String, Integer> getStateTotals() {
		return Collections.unmodifiableMap(stateTotals);
	}

	//calculate the total number of each type of cell
	private void updateStateTotals(Grid gridToReturn) {
		stateTotals = new HashMap<String,Integer>();
		for (int r = 0; r< gridToReturn.getGridRowSize(); r++) {
			for (int c = 0; c < gridToReturn.getGridColSize(); c++) {
				String state = gridToReturn.getCell(c, r).toString();
				String color = stateColorMap.get(state);
				if (!stateTotals.containsKey(color)) {
					stateTotals.put(color, 1);
				}
				stateTotals.put(color, stateTotals.get(color) + 1);
			}
		}
		
		
	}
	
	//class that takes a grid of states and packages the grid of colors
	private void createColorGrid(Grid gridToReturn) {
		colorGrid = new ArrayList<ArrayList<String>>();
		for (int r = 0; r< gridToReturn.getGridRowSize(); r++) {
			ArrayList<String> colorRow = new ArrayList<String>();
			for (int c = 0; c< gridToReturn.getGridColSize(); c++) {
				String state = gridToReturn.getCell(c, r).toString();
				String color = stateColorMap.get(state);
				colorRow.add(color); //gets the color
			}
			colorGrid.add(colorRow); //add to double array list
		}
	}
	
}
