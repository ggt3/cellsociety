package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Packager {
	private HashMap<String,Object> attributeMap; 
	private ArrayList<ArrayList<String>> colorGrid;
	private Map<String, String> colorsMap;
	
	public HashMap<String,Object> getMap() {
		return attributeMap;
	}
	
	public ArrayList<ArrayList<String>> getColorGrid() {
		return colorGrid;
	}
	
	public Map<String, String> getColorMap() {
		return colorsMap;
	}
}
