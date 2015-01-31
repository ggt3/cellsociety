package controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewPackage.View;
import model.*;



public class Controller {
	private Simulation rules = new GameLifeSimulation(null, null);
	private View myView;
	//TODO: needs to parse in the simulation type
	//rules = new SimulationType	
	
	public Controller(View v) {
		myView = v;
	}
	
	public Grid getNextFrame() { //asks for the next grid of color to give to view
		return rules.makeNextGrid();
	}

	public void giveViewGrid(Package colorGrid) {
		myView.updateGrid(colorGrid);
	}



//		
//    KeyFrame frame = start(60);
//    Timeline animation = new Timeline();
//    animation.setCycleCount(Animation.INDEFINITE);
//    animation.getKeyFrames().add(frame);
//    animation.play();

}
