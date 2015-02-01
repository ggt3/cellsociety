package controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import viewPackage.View;
import model.*;



public class Controller {
	private Simulation rules = new GameLifeSimulation(null, null);
	private View myView;
	private Timeline myTimeline;
	private static final int NUM_FRAMES_PER_SECOND = 60;
	//TODO: needs to parse in the simulation type
	//rules = new SimulationType	
	
	public void setView(View v){
		myView = v;
	}
	
	public Grid getNextGrid() { //asks for the next grid of color to give to view
		return rules.makeNextGrid();
	}

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
		Grid next = getNextGrid();
		Packager bundle = rules.createColorGrid(next);
		//myView.displayGrid(bundle);
	}
	
	public void pauseSimulation() {
		myTimeline.pause();
	}
	//used for when loading in another simulation
	public void stopSimulation() {
		myTimeline.stop();
	}




}
