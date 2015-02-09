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
	private boolean isTriangleShape;
	private boolean isToroidalEdgeType;
	private boolean isNoStroke;
	private ButtonBox buttons;
	private ToggleBox toggles;
	private DisplayGrid myGridDisplayed;
	private PopulationGraph myGraph;
	
    public View(Controller c) {
    	control = c;
    	myGridDisplayed=new DisplayGrid(this);
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
    	System.out.println("isTriangle: "+isTriangleShape);
    	primaryStage.setResizable(false);
        primaryStage.setTitle("Cell Society");
        //mainStage=primaryStage;
        buttons = new ButtonBox(control,this);
        toggles = new ToggleBox(this);
        
        HBox topButtonBox=buttons.makeButtonBox();
        HBox speedSlider=buttons.makeSlider();
        speedSlider.setLayoutY(windowSize-40);
        
        HBox toggle=toggles.makeToggles();
        toggle.setLayoutY(windowSize-100);
        toggle.setLayoutX(windowSize/3);
        
        root = new Group();
        root.getChildren().addAll(topButtonBox,speedSlider,toggle);
        System.out.printf("edge %b outline %b shape %b", getEdgeType(), getOutline(), getShape());
        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }


    public void calculateDynamicSize(int sizeX, int sizeY){
    	myGridDisplayed.setGridSize(sizeX, sizeY);
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
    
    //protected void updateGraph(String species, int percentage, int generation){
    	//graphy.addToSeries(species,percentage,generation);
    //}
    
    protected void setEdgeType(boolean s){
    	isToroidalEdgeType=s;
    }
    protected boolean getEdgeType(){
    	return isToroidalEdgeType;
    }
    
    protected void setOutline(boolean s){
    	isNoStroke=s;
    }
    protected boolean getOutline(){
    	return isNoStroke;
    }
    
    protected void setShape(boolean s){
    	isTriangleShape=s;
    }
    protected boolean getShape(){
    	return isTriangleShape;
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
	protected int getWindowSize(){
		return windowSize;
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