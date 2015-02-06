package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.CellState;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;



public class XMLParser {
	private Element root;

	public void parseXMLFile(String file) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse(file);
		dom.normalize();
		root = dom.getDocumentElement();
		clean(root);

	}
	
	public ArrayList<ArrayList<CellState>> parseGrid(){
		//Root
		ArrayList<ArrayList<CellState>>colorGrid = new ArrayList<ArrayList<CellState>>();
		NodeList grid = root.getElementsByTagName("grid").item(0).getChildNodes();
		for (int i = 0; i < grid.getLength(); i++) {
			NodeList row = grid.item(i).getChildNodes();
			ArrayList<CellState> rowgrid = new ArrayList<CellState>();
			for(int j=0; j<row.getLength(); j++){
				rowgrid.add(CellState.valueOf(row.item(j).getTextContent()));
				
			}
			colorGrid.add(rowgrid);
		}
		System.out.println(colorGrid.toString());
		System.out.printf("colorgrid %d %d", colorGrid.size(), colorGrid.get(1).size());
		return colorGrid;
	}
	public int[] parseGridSize() {
		int[] size = new int[2];
		size[0] = Integer.parseInt(root.getElementsByTagName("xsize").item(0).getTextContent());
		size[1] = Integer.parseInt(root.getElementsByTagName("ysize").item(0).getTextContent());
		return size;
	}
//	public HashMap<String, Integer> parseGlobalParameters(){
//		HashMap<String, Integer> map = new HashMap<String, Integer>();
//		NodeList params = root.getElementsByTagName("settings").item(0).getChildNodes();
//		for(int i=0; i<params.getLength(); i++){
//			String name = params.item(i).getTextContent().split(",")[0];
//			Integer value = Integer.parseInt(params.item(i).getTextContent().split(",")[1]);
//			map.put(name, value);
//		}
//		return map;
//	}
	public HashMap<String, String> parseColorMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		NodeList params = root.getElementsByTagName("map").item(0).getChildNodes();
		for(int i=0; i<params.getLength(); i++){
			String state = params.item(i).getTextContent().split(",")[0];
			String color = params.item(i).getTextContent().split(",")[1];
			map.put(state, color);
		}
		return map;
	}
	public String parseSimulationName() {
		return root.getElementsByTagName("name").item(0).getTextContent();
	}

	public String parseSimulationTitle() {
		return root.getElementsByTagName("title").item(0).getTextContent();
	}
	public String parseSimulationAuthor() {
		return root.getElementsByTagName("author").item(0).getTextContent();
	}
	private void clean(Node node)
	{
		NodeList childNodes = node.getChildNodes();
		for (int n = 0; n < childNodes.getLength(); n++) {
			Node child = childNodes.item(n);
			short nodeType = child.getNodeType();
			if (nodeType == Node.ELEMENT_NODE)
				clean(child);
			else if (nodeType == Node.TEXT_NODE) {
				String trimmedNodeVal = child.getNodeValue().trim();
				if (trimmedNodeVal.length() == 0)
					node.removeChild(child);
				else
					child.setNodeValue(trimmedNodeVal);

			}
		}
	}

}
