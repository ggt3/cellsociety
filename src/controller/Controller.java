package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import viewPackage.View;
import model.*;

public class Controller {
	private Simulation rules;
	private View myView;
	private Timeline myTimeline;
	private Map<String, String> stateColorMap;
	private static final int FRAME_MULTIPLIER = 4;
	private ResourceBundle myResources;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	public Controller(Stage primaryStage) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE +"English");
		myView = new View(this, myResources);
		myView.initialize(primaryStage);
		generateTimeline();
		
	}

	
	private void generateTimeline(){
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Animation.INDEFINITE);
	}

	public void changeSpeed(int frameRate) {
		int totalSpeed = frameRate*FRAME_MULTIPLIER;
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / totalSpeed), e -> playSimulation()); //max fps is 20
		if (!myTimeline.getKeyFrames().isEmpty()) {
			myTimeline.getKeyFrames().remove(0);
		}
		myTimeline.getKeyFrames().add(frame);
		myTimeline.play();
	}

	public void playSimulation() {
		stepSimulation();
	}

	public void stepSimulation() {
		Grid next = rules.makeNextGrid();
		rules.updateGrid(next); //sets the next grid as the new grid
		myView.updateGridView(bundleViewPackager(next));
	}
	
	//information for the graph, colorgrid are here
	private ViewPackager bundleViewPackager(Grid next) {
		ViewPackager p = new ViewPackager();
		p.setStateTotalMap(updateStateTotals(next));
		p.setColorGrid(createColorGrid(next));
		return p;
	}
	//class that takes a grid of states and packages the grid of colors
	private ArrayList<ArrayList<String>> createColorGrid(Grid gridToReturn) {
		ArrayList<ArrayList<String>> colorGrid = new ArrayList<ArrayList<String>>();
		for (int r = 0; r< gridToReturn.getGridRowSize(); r++) {
			ArrayList<String> colorRow = new ArrayList<String>();
			for (int c = 0; c< gridToReturn.getGridColSize(); c++) {
				String state = gridToReturn.getCell(c, r).toString();
				String color = stateColorMap.get(state);
				colorRow.add(color); //gets the color
			}
			colorGrid.add(colorRow); //add to double array list
		}
		return colorGrid;
	}
	//calculate the total number of 
	private Map<String, Integer> updateStateTotals(Grid gridToReturn) {
		Map<String,Integer> mapTotal = new HashMap<String,Integer>();
		for (int r = 0; r< gridToReturn.getGridRowSize(); r++) {
			for (int c = 0; c < gridToReturn.getGridColSize(); c++) {
				String state = gridToReturn.getCell(c, r).toString();
				String color = stateColorMap.get(state);
				if (!mapTotal.containsKey(color)) {
					mapTotal.put(color, 1);
				}
				mapTotal.put(color, mapTotal.get(color) + 1);
			}
		}
		return mapTotal;

	}

	//used for when loading in another simulation and pause
	public void stopSimulation() {
		myTimeline.stop();

	}

	//parse xml, give view size and init grid
	public void loadFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
		stopSimulation();
		XMLParser xml = new XMLParser();
		xml.parseXMLFile(fileName);
		try{
			String simName = xml.parseSimulationName();
			int[] xySize = xml.parseGridSize();
			stateColorMap = xml.parseColorMap().getColorMap();
			try{
				ArrayList<ArrayList<CellState>> initGridArray = xml.parseGrid(xySize[0], xySize[1]);
				Packager params = xml.parseGlobalParameters();
				validateParameters(simName, params);
				Grid init = listToGrid(initGridArray, params);
				setSimulationType(simName, params, init);
				myView.createDisplayView(xySize[0], xySize[1]);
				myView.updateGridView(bundleViewPackager(init));

			}
			catch(IllegalArgumentException e){
				myView.createErrorWindow(myResources.getString("InvalidCellStates"));
			}
			catch(IndexOutOfBoundsException e){
				myView.createErrorWindow(myResources.getString("CellLocationsOutOfBounds"));
			}

		}
		catch(NullPointerException e){
			myView.createErrorWindow(myResources.getString("InvalidSimulation"));
		}
	}
	private void setDefaultParameters(String source, Packager Pack) {
		ResourceBundle defs = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + source);
		for(String key:defs.keySet()){
			if (!Pack.getPropertiesMap().containsKey(key)){
				Pack.getPropertiesMap().put(key, Integer.parseInt(defs.getString(key)));
			}
		}
	}

	private Packager validateParameters(String simName, Packager pack) {
		if (simName.equals("FIRE")) {
			setDefaultParameters("FireDefaults", pack);
		}
		if (simName.equals("WATOR")) {
			setDefaultParameters("WatorDefaults", pack);
		}
		if (simName.equals("SEGREGATION")) {
			setDefaultParameters("SegregationDefaults", pack);
		}
		return pack;
	}
	//depending on the string, create a simulation rule with initial states and a initial grid
	private void setSimulationType(String name, Packager p, Grid g) {
		if (name.equals("FIRE")) {
			rules = new FireSimulation(g, p);
		}
		if(name.equals("LIFE")) {
			rules= new GameLifeSimulation(g,p);	
		}
		if (name.equals("WATOR")) {	
			rules = new WatorSimulation(g, p);	
		}
		if (name.equals("SEGREGATION")) {			
			rules = new SegregationSimulation(g, p);
		
		}
	}

	//translating the first grid from a arraylist of arrays into a Grid object to give to simulation
	private Grid listToGrid(ArrayList<ArrayList<CellState>> given, Packager properties) {
		Grid init;
		
		if (myView.getEdgeType() == true) { // grid is toroidial
			init = new ToroidalGrid(given.size(), given.get(0).size());
		} else {
			init = new FiniteGrid(given.size(), given.get(0).size());
		}
			
		for (int i = 0; i<given.size();i++) {
			for (int k = 0; k<given.get(0).size(); k++) {
				init.putCell(new Cell(given.get(i).get(k), properties.getPropertiesMap()), i, k);
			}
		}
		return init;
	}


}
