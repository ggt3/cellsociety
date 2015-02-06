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
import javafx.util.Duration;
import viewPackage.View;
import model.*;



public class Controller {
	private Simulation rules;
	private View myView;
	private Timeline myTimeline;


	public void testGrid() {
		TestSimulations t = new TestSimulations();
		rules = new SegregationSimulation(t.stupidMakeSegGrid(10), t.createMap(t.stupidMap()));
		myView.setGridSize(10,10);
		myView.updateRectangle(rules.createColorGrid(t.stupidMakeSegGrid(10)));
	} 
	
	public void setView(View v){
		myView = v;
	}
	
	public Grid getNextGrid() { //asks for the next grid of color to give to view
		return rules.makeNextGrid();
	}
	
	public void generateTimeline (int frameRate){
	    KeyFrame frame = new KeyFrame(Duration.millis(1000 / frameRate*2), e -> playSimulation()); //max frames is 20fps
	    myTimeline = new Timeline();
	    myTimeline.setCycleCount(Animation.INDEFINITE);
	    myTimeline.getKeyFrames().add(frame);
	    myTimeline.play();
	}
	
	public void playSimulation() {
		generateTimeline(myView.getSpeed());
		stepSimulation();
		
	}
	
	public void stepSimulation() {
		Grid next = rules.makeNextGrid();
		rules.updateGrid(next); //sets the next grid as the new grid
		Packager bundle = rules.createColorGrid(next);
		myView.updateRectangle(bundle);
	}
	
	public void pauseSimulation() {
		myTimeline.pause();
	}
	//used for when loading in another simulation
	public void stopSimulation() {
		myTimeline.stop();
	}

	//parse xml, give view size and init grid
	public void loadFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
		XMLParser xml = new XMLParser();
		xml.parseXMLFile(fileName);
		String simName = xml.parseSimulationName();
		Packager p = new Packager();
		p.setColorMap(xml.parseColorMap());

		int[] xySize = xml.parseGridSize();
		
		ArrayList<ArrayList<CellState>> initGridArray = xml.parseGrid();
		Grid init = listToGrid(initGridArray, xml.parseGlobalParameters()); //creating initial grid
		setSimulationType(simName, p, init);
		System.out.println("SIZE: "+xySize[0]);
		System.out.println(xySize[0]);
		System.out.println("SIZE: "+xySize[1]);
		myView.setGridSize(xySize[0], xySize[1]); //sets grid size and calls display grid
		myView.updateRectangle(rules.createColorGrid(init));
		

	}
	
	private void setSimulationType(String name, Packager p, Grid g) {
			rules = new FireSimulation(g,p);
		
	}
	
	private Grid listToGrid(ArrayList<ArrayList<CellState>> given, Map<String, Integer> properties) {
		Grid init = new Grid(given.size(), given.get(0).size());
		for (int i = 0; i<given.size();i++) {
			for (int k = 0; k<given.get(0).size(); k++) {
				
				Packager p = new Packager();
				p.setPropertiesMap(properties);
				init.putCell(new Cell(given.get(i).get(k), p), i, k);
			}
		}
		return init;
	}


}
