package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private int frameCounter;
	private Map<String, String> stateColorMap;
	
	public Controller(Stage primaryStage) {
		myView = new View(this);
		myView.initialize(primaryStage);
	    generateTimeline();
	    frameCounter = 0;
	}
	
	private void generateTimeline (){
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Animation.INDEFINITE);
	}
	
	public void changeSpeed(int frameRate) {
		int totalSpeed = frameRate*4;
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / totalSpeed), e -> playSimulation()); //max fps is 20
		System.out.println(totalSpeed);
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
		frameCounter++; //updating the generation
		rules.updateGrid(next); //sets the next grid as the new grid
		myView.updateGridView(bundleViewPackager(next));
		
	}
	private ViewPackager bundleViewPackager(Grid next) {
		ViewPackager p = new ViewPackager();
		//p.setGenerationCount(frameCounter);
		p.setStateTotalMap(updateStateTotals(next));
		p.setColorGrid(createColorGrid(next));
		return p;
	}
	//class that takes a grid of states and packages the grid of colors
	private ArrayList<ArrayList<String>> createColorGrid(Grid gridToReturn) {
		ArrayList<ArrayList<String>> colorGrid = new ArrayList<ArrayList<String>>();
		for (int r = 0; r< gridToReturn.getRowSize(); r++) {
			ArrayList<String> colorRow = new ArrayList<String>();
			for (int c = 0; c< gridToReturn.getColSize(); c++) {
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
		for (int r = 0; r< gridToReturn.getRowSize(); r++) {
			for (int c = 0; c < gridToReturn.getColSize(); c++) {
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
		String simName = xml.parseSimulationName();
		int[] xySize = xml.parseGridSize();
		stateColorMap = xml.parseColorMap().getColorMap();
		ArrayList<ArrayList<CellState>> initGridArray = xml.parseGrid();
		Grid init = listToGrid(initGridArray, xml.parseGlobalParameters()); //creating initial grid
		setSimulationType(simName, xml.parseGlobalParameters(), init);
		myView.calculateDynamicSize(xySize[0], xySize[1]); //sets grid size and calls display grid
		myView.updateGridView(bundleViewPackager(init));
		
	}
	
	//depending on the string, create a simulation rule with initial states and a initial grid
	private void setSimulationType(String name, Packager p, Grid g) {
			if (name.equals("FIRE")) {
				rules = new FireSimulation(g, p);
				return;
			}
			if(name.equals("LIFE")) {
				rules= new GameLifeSimulation(g,p);
				return;
			}
			if (name.equals("WATOR")) {
				rules = new WatorSimulation(g, p);
				return;
			}
			if (name.equals("SEGREGATION")) {
				rules = new SegregationSimulation(g, p);
				return;
			}
	}
	
	//translating the first grid from a arraylist of arrays into a Grid object to give to simulation
	private Grid listToGrid(ArrayList<ArrayList<CellState>> given, Packager properties) {
		Grid init = new Grid(given.size(), given.get(0).size());
		for (int i = 0; i<given.size();i++) {
			for (int k = 0; k<given.get(0).size(); k++) {
				init.putCell(new Cell(given.get(i).get(k), properties.getPropertiesMap()), i, k);
			}
		}
		return init;
	}


}
