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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import org.controlsfx.dialog.Dialogs;


public class View {
    private Scene myScene;
	private Integer windowSize=600;
	private Integer speed = 1;
	private Text speedText;
	private Group root=new Group();
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600); //size of the window screen
	private ResourceBundle myResources;
	private String fileName="";
	private Controller control;
	private Stage mainStage;
	protected Rectangle[][] gridView;// = new Rectangle[20][20];
	protected Polygon[][] gridView2;
	private final int totalWidthOfGrid= 450;
	private final int totalHeightOfGrid= 450;
	private int currentGeneration=0;
	private int numSquareX, numSquareY;
	private Stage stagenumba2;
	
	
	private ButtonBox buttons;
	private DisplayGrid griddy;//=new DisplayGrid();

    public View(Controller c) {
    	control = c;
    	griddy=new DisplayGrid();
    }
    public View(){
    	
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
        
        //slide.setLayoutY(200);
        root = new Group();
        root.getChildren().addAll(hbox,slide);
//        Rectangle poop=new Rectangle(20,20,300,300);
//        poop.setFill(Color.SPRINGGREEN);
//        poop.setX(300);
//        poop.setY(300);
        //root.getChildren().addAll(griddy.tryMe(20,20,300,300));
        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }
    
    public void drawGrid(Rectangle r){
    	root.getChildren().add(r);
    }
    
    public void setGridSize(int sizeX, int sizeY,boolean shape){
    	 numSquareX = sizeX;
    	 numSquareY = sizeY;
    	 double x=determineXlength(numSquareX);
         double y=determineYlength(numSquareY);
    	 if (shape){
    		 gridView= new Rectangle[numSquareX][numSquareY];
    		 displayGrid(525,550,x,y);
    	 }
    	 else{
    		 gridView2= new Polygon[numSquareX][numSquareY];
    		 displayGrid2(525,550,x,y);
    	 }
    	 //displayGrid(525,550,x,y);
    	//griddy.setGridSize(sizeX, sizeY);
    }
//
//
    public void displayGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			Rectangle rex=new Rectangle(i,j,x,y);
    			rex.setStroke(Color.BLACK);
    			rex.setStrokeWidth(0.5);
    			root.getChildren().add(rex);
    			gridView[xIndex][yIndex] = rex;
    			yIndex++;
    		}
    		xIndex++;
    	}

    }
    public void displayGrid2(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			//Rectangle rex=new Rectangle(i,j,x,y);
    			Polygon p=new Polygon();
    			p.getPoints().addAll(new Double[]{
    				    i+x/2, j+0.0,
    				    i+x+x/2, j+0.0,
    				    i+x, j+y });
    			p.setStroke(Color.BLACK);
    			p.setStrokeWidth(0.5);
    			//rex.setStroke(Color.BLACK);
    			//rex.setStrokeWidth(0.5);
    			//root.getChildren().add(rex);
    			root.getChildren().add(p);
    			//gridView[xIndex][yIndex] = rex;
    			gridView2[xIndex][yIndex] = p;
    			yIndex++;
    		}
    		xIndex++;
    	}

    }
//    
//    //changes color of existing rectangles according to colors
    public void updateRectangle(Packager colorGrid,boolean bool){
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			String color=colorGrid.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			if (bool){
    				setFill(gridView[i][j], Color.valueOf(color)); //converting the string to color
    			}
    			else
    				setFill2(gridView2[i][j], Color.valueOf(color));
    		}
    	}

    }
//    //account for dynamic change of grid
    private double determineXlength(int numCols){
    	return ((double)totalWidthOfGrid)/numCols;
    	
    }
    
    private double determineYlength(int numRows){
    	return ((double)totalHeightOfGrid)/numRows;
    }
    
    protected void giveToView(Rectangle[][] ro){
    	gridView=ro;
    	for (Rectangle[] r: gridView){
			for (Rectangle t: r){
				root.getChildren().add(t);
				//System.out.println(t);
			}
			
		}
    }
    
    
	protected void setFill(Rectangle gridView3,Color c) {
		gridView3.setFill(c);
	}
	protected void setFill2(Polygon gridView3,Color c) {
		gridView3.setFill(c);
	}

	protected void setMyGrid(Rectangle[][] grid){
		gridView=grid;
		for (Rectangle[] r: gridView){
			for (Rectangle t: r){
				//root.getChildren().add(t);
				//System.out.println(t);
			}
			
		}
		//root.getChildren().addAll(gridView);
	}
	
	protected void instantGrid(Rectangle[][] r){
		gridView= r;
	}
	
	protected void addToGrid(int x,int y,Rectangle p){
		//System.out.print
		gridView[x][y]=p;
	}
	
	protected void addToRoot(Node n){
		root.getChildren().addAll(n,addText("POOz",20,30,30));
		addText("POOz",20,400,400);
		root.getChildren().remove(0);
		System.out.println("here ya go poop");
	}
	
	public void callDisplayPLZ(){
		griddy.showmedamoney();
	}
	protected Group getRoot(){
		return root;
	}
	protected String getFileName(){
		return fileName;
	}
	protected Stage getPrimaryStage(){
		return mainStage;
	}
	public int getSpeed(){
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
	protected void setSecondStage(Stage s2){
		stagenumba2=s2;
		stagenumba2.show();
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