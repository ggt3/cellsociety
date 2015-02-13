// This entire file is part of my masterpiece.
// COSETTE GOLDSTEIN

package viewPackage;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.Controller;
import controller.ViewPackager;
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



public class View {
	private static Integer windowSize=600;
	private Group root;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/English";
    
    private static final int TOGGLEBAR_START_LOCATION_X=windowSize/6;
    private static final int TOGGLEBAR_START_LOCATION_Y=windowSize-100; 
    private static final int SPEEDBAR_START_LOCATION_Y=windowSize-40;
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
    
	public Text addText(String s,int size,int xLocation,int yLocation) {
        Text t = new Text(xLocation,yLocation,s);
        t.setFont(Font.font ("Verdana", size));
        t.setCache(true);
        t.setFill(Color.BLACK);
        return t;
	}

	//beginning screen with no grid
    public void initialize(Stage primaryStage) {
        buttons = new ButtonBox(control,this);
        toggles = new ToggleBox();
        
        HBox topButtonBox=buttons.makeButtonBox();
        HBox speedSlider=buttons.makeSlider();
        speedSlider.setLayoutY(SPEEDBAR_START_LOCATION_Y);
        
        HBox toggle=toggles.makeToggles();
        toggle.setLayoutY(TOGGLEBAR_START_LOCATION_Y);
        toggle.setLayoutX(TOGGLEBAR_START_LOCATION_X);
        root = new Group();
        root.getChildren().addAll(topButtonBox,speedSlider,toggle);
    	primaryStage.setResizable(false);
    	setAndShowStage(primaryStage,new Scene(root, windowSize, windowSize, Color.WHITE),"Cell Society");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if (additionalStage!=null)
					additionalStage.close();
			}
		});    }

    public void createDisplayView(int x, int y) {
		root.getChildren().remove(3, root.getChildren().size());
		if(additionalStage!=null) //close Graph Window when new simulation is being loaded
			additionalStage.close();
    	generation=0;
		if (toggles.getShape()) { //if its triangles
    		myGridDisplayed = new TriangularGridView(this);
    	} 
		else {
    		myGridDisplayed = new RectangularGridView(this);
    	}
    	myGridDisplayed.initializeGridView(x, y);
    }
    
    //changes color of existing grid shapes according to colors
    public void updateGridView(ViewPackager colorGrid) {
    	myGridDisplayed.updateGrid(colorGrid);
    	myGraph.updateGraph(colorGrid);
    	generation++;
    }
    
    private void setAndShowStage(Stage stage, Scene scene, String title) {
    	stage.setScene(scene);
    	stage.setTitle(title);
    	stage.show();
    }
    
    protected void createGraphWindow() {
    	myGraph=new PopulationGraph(this);
    	Scene graphScene=myGraph.makeGraph();
    	additionalStage=new Stage();
    	setAndShowStage(additionalStage,graphScene,"Population Graph");
    }
    
    public void createErrorWindow(String st) {
    	ErrorDisplay showError=new ErrorDisplay();
    	Scene errorScene = showError.display(st);
    	Stage newStage = new Stage();
    	setAndShowStage(newStage,errorScene,"Error");
    }
    
    protected boolean getOutline() {
    	return toggles.getOutline();
    }

	public boolean getEdgeType() {
		return toggles.getEdgeType();
	}
	protected void addToRoot(Node n) {
		root.getChildren().addAll(n);
	}
	public int getSpeed() {
		return buttons.getSpeed();
	}
	protected void setFileName(String file) {
		fileName=file;
	}
	public ResourceBundle getResourceBundle() {
		return myResources;
	}
	protected int getGeneration() {
		return generation;
	}
	public void tryLoad(String file) throws ParserConfigurationException, SAXException, IOException {
		control.loadFile(file);
	}

}