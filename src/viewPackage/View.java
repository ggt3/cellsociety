package viewPackage;


import java.awt.Dimension;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import org.controlsfx.dialog.Dialogs;


public class View {
	private Integer windowSize=600;
	private Group root;
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600); //size of the window screen
	private String fileName="";
	private Controller control;
	private ButtonBox buttons;
	private ToggleBox toggles;
	private DisplayGrid myGridDisplayed;
	private PopulationGraph myGraph;
	
    public View(Controller c) {
    	control = c;
    	
    }
    
	public Text addText(String s,int size,int xLocation,int yLocation){
        Text t = new Text(xLocation,yLocation,s);
        t.setFont(Font.font ("Verdana", size));
        t.setCache(true);
        t.setFill(Color.BLACK);
        return t;
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
        toggle.setLayoutX(windowSize/3);
        root = new Group();
        root.getChildren().addAll(topButtonBox,speedSlider,toggle);
        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }

    public void createDisplayView(int x, int y) {
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
    }
    
    protected void createGraphWindow(){
    	myGraph=new PopulationGraph(this);
    	Scene graphScene=myGraph.makeGraph();
    	Stage additionalStage=new Stage();
    	additionalStage.setScene(graphScene);
    	additionalStage.show();
    }
    
    protected void createErrorWindow(String st){
    	ErrorDisplay showError=new ErrorDisplay();
    	Scene s=showError.display(st);
    	Stage newStage=new Stage();
    	newStage.setScene(s);
    	newStage.show();
    }
    
    protected boolean getOutline(){
    	return toggles.getOutline();
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

	
	protected void tryLoad(String s){
		try {
			control.loadFile(s);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}