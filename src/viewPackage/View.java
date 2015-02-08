package viewPackage;


import java.awt.Dimension;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.Controller;
import controller.Packager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import org.controlsfx.dialog.Dialogs;


public class View {
    private Scene myScene;
	private Integer windowSize=600;
	private Integer speed = 1;
	private Text speedText;
	private Group root;//=new Group();
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600); //size of the window screen
	private ResourceBundle myResources;
	private String fileName="";
	private Controller control;
	private Stage mainStage;

	private int currentGeneration=0;
	private ButtonBox buttons;
	private DisplayGrid griddy;//=new DisplayGrid();

    public View(Controller c) {
    	control = c;
    	griddy=new DisplayGrid(this);
    }



    public void showError (String message) {
        //Object myResources;
		//Dialogs.create().title(myResources.getString("ErrorTitle")).message(message).showError();
    }
    
	public Text addText(String s,int size,int xLoc,int yLoc){
        Text t = new Text(xLoc,yLoc,s);
        t.setFont(Font.font ("Verdana", size));
        t.setCache(true);
        t.setFill(Color.BLACK);
        return t;
	}

	//beginning screen with no grid
    public void initialize(Stage primaryStage) {
    	primaryStage.setResizable(false);
        primaryStage.setTitle("Cell Society");
        mainStage=primaryStage;
        buttons = new ButtonBox(control);
        HBox hbox=buttons.makeButtonBox();
        HBox slide=buttons.makeSlider();
        slide.setLayoutY(windowSize-40);
        
        
        root = new Group();
        root.getChildren().addAll(hbox,slide);

        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }


    public void calculateDynamicSize(int sizeX, int sizeY,boolean shape){
    	griddy.setGridSize(sizeX, sizeY);
    }
    
    //changes color of existing rectangles according to colors
    public void updateRectangle(Packager colorGrid,boolean bool){
    	griddy.updateRectangle(colorGrid);

    }
    
	protected void addToRoot(Node n){
		root.getChildren().addAll(n);
	}

	protected String getFileName(){
		return fileName;
	}
	protected Stage getPrimaryStage(){
		return mainStage;
	}
	public int getSpeed(){
		System.out.println("SPEED IS HERE:"+speed);
		return speed;
	}
	protected void addToSpeed(int s){
		speed=s;
		//speedText.setText(""+s+"");
	}
	protected Text displayCurrentSpeed(){
		return speedText;
	}
	
	protected void setFileName(String file){
		fileName=file;
	}
	protected int getWindowSize(){
		return windowSize;
	}

	protected double popUpLocation(){
		return mainStage.getX()+100;
	}
	
	protected Controller getControl(){
		return control;
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
	protected DisplayGrid callToGridMethod(){
		return griddy;
	}
	
	protected Scene getScene(){
		return myScene;
	}
}