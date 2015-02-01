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

	public void giveViewGrid(Package colorGrid) {
		myView.updateGrid(colorGrid);
	}
	public void generateTimeline (int frameRate){
		
	    KeyFrame frame = new KeyFrame(Duration.millis(1000 / frameRate), e -> updateSprites());
	    myTimeline = new Timeline();
	    myTimeline.setCycleCount(Animation.INDEFINITE);
	    myTimeline.getKeyFrames().add(frame);
	    myTimeline.play();
	}
	public void playSimulation(KeyFrame k)
	





}
