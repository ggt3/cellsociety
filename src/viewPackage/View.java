package viewPackage;


import java.awt.Dimension;
import java.util.ResourceBundle;

import controller.Controller;
import controller.Packager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private Group root;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600); //size of the window screen
	private ResourceBundle myResources;
	private String fileName="";
	private Controller control;
	private Stage mainStage;
	private Rectangle[][] gridView;// = new Rectangle[20][20];
	private final int totalWidthOfGrid= 450;
	private final int totalHeightOfGrid= 450;
	private int currentGeneration=0;
	private int numSquareX, numSquareY;
	


    public View(Controller c) {
    	control = c;
    }
    
    public Button makeButton(String string)
	//public Button makeButton(Stage s,double x,int level, String string)
	{
		Button btn = new Button();
        btn.setText(string);
	    if (!string.contains("UP")&&!string.contains("DOWN"))
	    {
	        btn.setScaleX(2);
		    btn.setScaleY(2);
	    }

        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {

				if (string.equals("Play")) {
					control.playSimulation(speed);
				}
				if (string.equals("Pause")) {
					control.pauseSimulation();
				}
				if (string.equals("Step")) {
					control.stepSimulation();
				}
				if (string.equals("   UP   ")) {
					speed++;
					System.out.println(speed);
					speedText.setText("" + speed + "");
					// send new speed to controller to update frames per second
				}
				if (string.equals("DOWN")) {
					if (speed > 0)
						speed--;
					System.out.println(speed);
					speedText.setText("" + speed + "");
				}
				if (string.equals("Load")) {
    				
                    Label secondLabel = new Label("Please Enter Your File Name:");
                    
                    GridPane grid = new GridPane();
                    grid.setAlignment(Pos.CENTER);
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(25, 25, 25, 25));
                    
                    TextField textField = new TextField();
                    grid.add(textField, 0, 1);
                    grid.getChildren().add(secondLabel);

                    Scene secScene = new Scene(grid, 400, 400);
                    
                    Button hitBtn = new Button("Go");
                    hitBtn.setAlignment(Pos.BOTTOM_RIGHT);
                    grid.add(hitBtn, 1, 4);
                    
                    
                    
                    Stage secondStage = new Stage();
                    hitBtn.setOnAction(new EventHandler<ActionEvent>() {

                    	@Override
                    	    public void handle(ActionEvent e) {
                    	        if ((textField.getText().trim() != null && !textField.getText().isEmpty() && textField.getText().endsWith("xml"))) {
                    	        	fileName=textField.getText();
                    	        	System.out.println(fileName);
                    	        	secondStage.close();
                    	            Text texty=addText(fileName,20,0,90);
                    	            root.getChildren().add(texty);
                    	        } else {
                    	        	//grid.add(addText("Not a valid name. Make sure it ends in .xml",10,50,500));
                    	        	System.out.println("File Name entered is not a valid name");
                    	        }
                    	     }
                    	 });
                    control.loadFile(fileName);
                    secondStage.setTitle("Load File");
                    secondStage.setScene(secScene);
                     
                    //Set position of second window, related to primary window.
                    secondStage.setX(mainStage.getX() + 100);
                    secondStage.setY(mainStage.getY() + 100);
                    secondStage.show();
    				
    			}
            }
        });
        return btn;
	}
	
    public void showError (String message) {
        //Object myResources;
		//Dialogs.create().title(myResources.getString("ErrorTitle")).message(message).showError();
    }
	

    public Text addText(String s,int size)
	{
        Text t = new Text(s);
        t.setFont(Font.font ("Verdana", size));
        t.setCache(true);
        t.setFill(Color.BLACK);
        return t;
	}
    
    
	public Text addText(String s,int size,int xLoc,int yLoc)
	{
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
        HBox hbox=new HBox(50);
        Button play=makeButton("Play");
        Button pause=makeButton("Pause");
        Button step=makeButton("Step");
        Button load=makeButton("Load");
        
        speedText=addText(""+speed+"",20);
        speedText.setScaleY(2);
        speedText.setScaleX(2);
        
        VBox speedButtons = new VBox();
        Button up =makeButton("   UP   ");
        Button down=makeButton("DOWN");
        speedButtons.getChildren().addAll(up,down);
        
        speedButtons.setTranslateY(-11);
        
        hbox.setTranslateX(30);
        hbox.setTranslateY(15);
        
        hbox.getChildren().addAll(play,pause,step,speedText,speedButtons,load);
        hbox.setPrefWidth(windowSize);
        
        
        root = new Group();

        //instead of arbitrary 20, use a getMethod from controller to figure out how many rows and columns
        double x=determineXlength(numSquareX);
        double y=determineYlength(numSquareY);
        
        gridView=new Rectangle[numSquareX][numSquareY];
        
        displayGrid(525,550,x,y); //offset in the screen
        root.getChildren().add(hbox);
        //root.getChildren().addAll(play,pause,load,speedUp,speedDown,speedText,step);

        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }
    
    public void setGridSize(int sizeX, int sizeY){
    	 numSquareX = sizeX;
    	 numSquareY = sizeY;
    	 double x=determineXlength(numSquareX);
         double y=determineYlength(numSquareY);
    	 displayGrid(525,550,x,y);
    }
    
    //public 

    private void displayGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	//int yIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			Rectangle rex=new Rectangle(i,j,x,y);
    			//use encapsulated info to determine color like example commented below
    			//rex.setFill(infoFromController[i][j]);
    			rex.setFill(Color.SPRINGGREEN);
    			rex.setStroke(Color.BLACK);
    			rex.setStrokeWidth(0.5);
    			root.getChildren().add(rex);
    			gridView[xIndex][yIndex]=rex;
    			yIndex++;
    		}
    		xIndex++;
    	}
    	//root.getChildren().addAll(yo);
    	//return gridView;
    	
    }
    
    //changes color of existing rectangles according to colors
    public void updateRectangle(Packager colorGrid){
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			//Rectangle rex2 = gridView[i][j];
    			String color=colorGrid.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			setFill(gridView[i][j], Color.valueOf(color)); //converting the string to color
    		}
    	}

    }
    //account for dynamic change of grid
    private double determineXlength(int numCols){
    	double xsize=((double)totalWidthOfGrid)/numCols;
    	return xsize;
    }
    
    private double determineYlength(int numRows){
    	double ysize=((double)totalHeightOfGrid)/numRows;
    	return ysize;
    }
    
	private void setFill(Rectangle rex,Color c) {
		rex.setFill(c);
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
	protected Text displayCurrentSpeed(){
		return speedText;
	}

}