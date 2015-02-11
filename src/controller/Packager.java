package controller;


import java.util.HashMap;
import java.util.Map;


public class Packager {
	private Map<String,Integer> attributeMap; 
	private Map<String, String> colorsMap;
	
	public Map<String,Integer> getPropertiesMap() {
		HashMap<String, Integer> m = new HashMap<String,Integer>();
		for (Map.Entry<String,Integer> entry : attributeMap.entrySet()) {
			  m.put(entry.getKey(), entry.getValue());
		}
		return m;

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
