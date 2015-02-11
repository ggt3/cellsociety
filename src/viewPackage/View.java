package viewPackage;


import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.Controller;
import controller.ViewPackager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//import org.controlsfx.dialog.Dialogs;


public class View {
	private Integer windowSize=600;
	private Group root;
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600); //size of the window screen
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/English";
	private ResourceBundle myResources;
    private String fileName="";
	private Controller control;
	private ButtonBox buttons;
	private ToggleBox toggles;
	private DisplayGrid myGridDisplayed;
	private PopulationGraph myGraph;
	private int generation;
	private Stage additionalStage;
	
    public View(Controller c) {
    	control = c;
    	myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		

    }
    

	//beginning screen with no grid
    public void initialize(Stage primaryStage) {
    	primaryStage.setResizable(false);
        primaryStage.setTitle("Cell Society");
      
        buttons = new ButtonBox(control,this);
        toggles = new ToggleBox();
        
        HBox topButtonBox=buttons.makeButtonBox();
        HBox speedSlider=buttons.makeSlider();
        speedSlider.setLayoutY(windowSize-40);
        
        HBox toggle=toggles.makeToggles();
        toggle.setLayoutY(windowSize-100);
        toggle.setLayoutX(windowSize/9);
		root = new Group();
		root.getChildren().addAll(topButtonBox, speedSlider, toggle);
		primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			if (additionalStage != null) {
				additionalStage.close();
			}
		});
	}

	public void createDisplayView(int x, int y) {
		root.getChildren().remove(3, root.getChildren().size());
		if(additionalStage!=null)
			additionalStage.close();
    	generation=0;
		if (toggles.getShape() ==true) { //if its triangles
    		myGridDisplayed = new TriangularGridView(this);
    	} else {
    		myGridDisplayed = new RectangularGridView(this);
    	}
    	myGridDisplayed.initializeGridView(x, y);
    }
    
    //changes color of existing rectangles according to colors
    public void updateGridView(ViewPackager colorGrid){
    	myGridDisplayed.updateGrid(colorGrid);
    	myGraph.updateGraph(colorGrid);
    	generation++;
    }
    
    protected void createGraphWindow(){
    	myGraph=new PopulationGraph(this);
    	Scene graphScene=myGraph.makeGraph();
    	additionalStage=new Stage();
    	additionalStage.setScene(graphScene);
    	additionalStage.show();
    }
    
    public void createErrorWindow(String st){
    	ErrorDisplay showError=new ErrorDisplay();
    	Scene s = showError.display(st);
    	Stage newStage=new Stage();
    	newStage.setScene(s);
    	newStage.show();
    }
    
    protected boolean getOutline(){
    	return toggles.getOutline();
    }

	public boolean getEdgeType() {
		return toggles.getEdgeType();
	}
	protected void addToRoot(Node n){
		root.getChildren().addAll(n);
	}

	protected String getFileName(){
		return fileName;
	}
	public int getSpeed(){
		return buttons.getSpeed();
	}

	protected void setFileName(String file){
		fileName=file;
	}

	public ResourceBundle getResourceBundle(){
		return myResources;
	}
	protected int getGeneration(){
		return generation;
	}
	public void tryLoad(String file) throws ParserConfigurationException, SAXException, IOException {
		control.loadFile(file);
	}

}