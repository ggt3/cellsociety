package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Packager {
	private Map<String,Integer> attributeMap; 
	private ArrayList<ArrayList<String>> colorGrid=new ArrayList<ArrayList<String>>();
	private Map<String, String> colorsMap;
	
	public Map<String,Integer> getPropertiesMap() {
		HashMap<String, Integer> m = new HashMap<String,Integer>();
//		System.out.print(attributeMap.toString());
//		m.putAll(attributeMap);
		for (Map.Entry<String,Integer> entry : attributeMap.entrySet()) {
			  m.put(entry.getKey(), entry.getValue());
		}
		return m;

	}
	
	public ArrayList<ArrayList<String>> getColorGrid() {
		return colorGrid;
	}
	
	public Map<String, String> getColorMap() {
		return colorsMap;
	}
	public void setColorMap(Map<String, String> c) {
		colorsMap = c;
	}
	public void setPropertiesMap(Map<String, Integer> s) {
		attributeMap = s;
	}
}
