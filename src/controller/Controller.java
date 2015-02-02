package controller;
import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import viewPackage.View;
import model.*;



public class Controller {
	private Simulation rules = new GameLifeSimulation(stupidMakeGrid(), createMap(stupidMap()));
	private View myView;
	private Timeline myTimeline;
	private String myFile;
	private int NUM_FRAMES_PER_SECOND = 60;
	//TODO: needs to parse in the simulation type
	//rules = new SimulationType	
	public Grid stupidMakeGrid() {
		Grid newG = new Grid(4,4);
		Cell s = new Cell(CellState.ALIVE);
		Cell t=new Cell(CellState.EMPTY);
		for (int i = 0; i < 4; i++) {
			for (int k =0; k<4; k++) {
				if (i==1)
					newG.putCell(s, i, k);
				else
					newG.putCell(t, i, k);
			}
		}
		return newG;
	}
	
	public HashMap<String,String> stupidMap() {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("ALIVE", "RED");
		map.put("EMPTY", "BLACK");
		map.put("TREE", "AQUA");
		return map;
	}
	public Packager createMap(HashMap<String,String> d) {
		Packager p = new Packager();
		p.setColorMap(d);
		return p;
	}
	
	public void giveGridSize(int row) {
		myView.setGridSize(4);
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
		Grid next = getNextGrid();
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
		myFile=fileName;
	}



}
