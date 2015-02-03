package controller;

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
	private String myFile;
	private int NUM_FRAMES_PER_SECOND = 60;
	//TODO: needs to parse in the simulation type

	public void testGrid() {
		TestSimulations t = new TestSimulations();
		rules = new FireSimulation(t.stupidMakeFireGrid(), t.createMap(t.stupidMap()));
		giveGridSize(4);
	
		
	} 
	
	public void giveGridSize(int size) {
		myView.setGridSize(size);
	}
	public void setView(View v){
		myView = v;
	}
	
	public Grid getNextGrid() { //asks for the next grid of color to give to view
		return rules.makeNextGrid();
	}

	//TODO: view.setInitialGrid //given by xml file
	
	public void giveViewGrid(Packager colorGrid) {
		myView.updateGrid(colorGrid);
	}
	public void generateTimeline (int frameRate){
	    KeyFrame frame = new KeyFrame(Duration.millis(1000 / frameRate), e -> playSimulation(myView.getSpeed()));
	    myTimeline = new Timeline();
	    myTimeline.setCycleCount(Animation.INDEFINITE);
	    myTimeline.getKeyFrames().add(frame);
	    myTimeline.play();
	}
	public void playSimulation(int speed) {
		
		stepSimulation();
		
	}
	public void setSpeed(int speed) {
		NUM_FRAMES_PER_SECOND = speed;
	}
	
	public void stepSimulation() {
		Grid next = rules.makeNextGrid();
		rules.updateGrid(next); //sets the next grid as the new grid
		Packager bundle = rules.createColorGrid(next);
		giveViewGrid(bundle);
	}
	
	public void pauseSimulation() {
		myTimeline.pause();
	}
	//used for when loading in another simulation
	public void stopSimulation() {
		myTimeline.stop();
	}

	
	public void setFilePath(String fileName){
		myFile = fileName;
	}



}
