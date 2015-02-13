package viewPackage;



import java.io.IOException;
import java.util.ResourceBundle;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import controller.Controller;
import controller.ViewPackager;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class View {
	private Integer DEFAULT_WINDOW_SIZE = 600;
	private Group root;
	private ResourceBundle myResources;
    private String fileName="";
	private Controller control;
	private ButtonBox buttons;
	private ToggleBox toggles;
	private DisplayGrid myGridDisplayed;
	private PopulationGraph myGraph;
	private int generation;
	private Stage additionalStage;
	
    public View(Controller c, ResourceBundle resources) {
    	control = c;
    	myResources = resources;

    }
    

	//beginning screen with no grid
    public void initialize(Stage primaryStage) {
    	primaryStage.setResizable(false);
        primaryStage.setTitle("Cell Society");
      
        buttons = new ButtonBox(this);
        toggles = new ToggleBox();
        
        HBox topButtonBox=buttons.makeButtonBox();
        HBox speedSlider=buttons.makeSlider();
        speedSlider.setLayoutY(DEFAULT_WINDOW_SIZE-40);
        
        HBox toggle=toggles.makeToggles();
        toggle.setLayoutY(DEFAULT_WINDOW_SIZE-100);
        toggle.setLayoutX(DEFAULT_WINDOW_SIZE/9);
		root = new Group();
		root.getChildren().addAll(topButtonBox, speedSlider, toggle);
		primaryStage.setScene(new Scene(root, DEFAULT_WINDOW_SIZE, DEFAULT_WINDOW_SIZE, Color.WHITE));
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			if (additionalStage != null) {
				additionalStage.close();
			}
		});
	}

	public void createDisplayView(int x, int y) {
		root.getChildren().remove(3, root.getChildren().size()); //remove what used to be on the screen
		if(additionalStage!=null)
			additionalStage.close();
    	generation = 0;
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
	protected void setPlayAction() {
		control.changeSpeed(buttons.getSpeed());
		control.playSimulation();
	}
	protected void setPause() {
		control.stopSimulation();
	}
	protected void setStepSimulation() {
		control.stepSimulation();
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
	protected ResourceBundle getResourcePath() {
		return myResources;
	}
	protected int getGeneration(){
		return generation;
	}
	public void tryLoad(String file) throws ParserConfigurationException, SAXException, IOException {
		control.loadFile(file);
	}

}