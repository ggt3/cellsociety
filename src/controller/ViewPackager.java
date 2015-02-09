package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * this packager contains information the view needs: generation count, the 
 * grid of colors, the totals of each color
 * @author GA
 *
 */
public class ViewPackager {
	private ArrayList<ArrayList<String>> colorGrid;
	private int generationCount;
	private Map<String, Integer> stateTotals;
	
	public void setColorGrid(ArrayList<ArrayList<String>> color ) {
		colorGrid = color;
	}
	public ArrayList<ArrayList<String>> getColorGrid() {
		return colorGrid;
	}
	public int getGenerationCount() {
		return generationCount;
	}
	public void setGenerationCount(int count) {
		generationCount = count;
	}
	
	public Map<String, Integer> getStateTotals() {
		return Collections.unmodifiableMap(stateTotals);
	}
	public void setStateTotalMap(Map<String, Integer> map) {
		stateTotals = map;
	}
	
}
