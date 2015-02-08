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

	public Controller(Stage primaryStage) {
		myView = new View(this);
		myView.initialize(primaryStage);
	    generateTimeline();
	}
	
	public Grid getNextGrid() { //asks for the next grid of color to give to view
		return rules.makeNextGrid();
	}
	
	private void generateTimeline (){
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Animation.INDEFINITE);
	}
	
	public void changeSpeed(int frameRate) {
		System.out.println(myTimeline.getKeyFrames());
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
		rules.updateGrid(next); //sets the next grid as the new grid
		Packager bundle = rules.createColorGrid(next);
		myView.updateRectangle(bundle,false);
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
		
		ArrayList<ArrayList<CellState>> initGridArray = xml.parseGrid();
		Grid init = listToGrid(initGridArray, xml.parseGlobalParameters()); //creating initial grid
		
		setSimulationType(simName, xml.parseColorMap(), init);
		myView.calculateDynamicSize(xySize[0], xySize[1],false); //sets grid size and calls display grid
		myView.updateRectangle(rules.createColorGrid(init),false);
	}
	
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
				init.putCell(new Cell(given.get(i).get(k), properties), i, k);
			}
		}
		return init;
	}


}
