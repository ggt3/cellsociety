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
	private Text t;
	private Group root;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600);
	private ResourceBundle myResources;
	private String fileName="";
	private Controller control;
	private Stage mainStage;
	private Rectangle[][] yo;// = new Rectangle[2][2];
	private int totalWidthOfGrid=450;
	private int totalHeightOfGrid=450;
	

    public View(Controller c) {
    	control = c;
    }
    
	public Button makeButton(Stage s,double x,int level, String string)
	{
		
		
		Button btn = new Button();
        btn.setText(string);
        btn.setLayoutX(x);
        btn.setLayoutY(level);
		if (level==1)
		{
	        btn.setScaleX(2);
	        btn.setScaleY(2);
	        btn.setLayoutY(20);
		}

        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
    			
    			if(string.equals("   UP   "))
    			{
    				speed++;
    				System.out.println(speed);
    				speedText.setText(""+speed+"");
    			}
    			if(string.equals("DOWN"))
    			{
    				if (speed>0)
    					speed--;
    				System.out.println(speed);
    				speedText.setText(""+speed+"");
    			}
    			if(string.equals("    Load    "))
    			{
    				
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
                    secondStage.setTitle("Load File");
                    secondStage.setScene(secScene);
                     
                    //Set position of second window, related to primary window.
                    secondStage.setX(s.getX() + 100);
                    secondStage.setY(s.getY() + 100);
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
	


	public Text addText(String s,int size,int xLoc,int yLoc)
	{
        Text t = new Text(xLoc,yLoc,s);
        t.setFont(Font.font ("Verdana", size));
        t.setCache(true);
        t.setFill(Color.BLACK);
        return t;
	}

	//@Override
    public void initialize(Stage primaryStage) {
        primaryStage.setTitle("Cell Society");
        mainStage=primaryStage;
        double currWidth=primaryStage.getWidth();
        //AllButton btn=new PlayButton();
        Button play=makeButton(primaryStage,20,1,"Play");
        Button pause=makeButton(primaryStage,(windowSize/6)+10,1,"Pause");
        Button load=makeButton(primaryStage,(windowSize/12)*9,1,"    Load    ");
        Button step=makeButton(primaryStage,(windowSize/6)*2+20,1,"Step");
        Button speedUp=makeButton(primaryStage,(windowSize/24)*13,8,"   UP   ");
        Button speedDown=makeButton(primaryStage,(windowSize/24)*13,32,"DOWN");
        speedText=addText(""+speed+"",40,(windowSize/24)*12,50);
        t=addText("ERROR",20,0,windowSize-20);
        
        
        root = new Group();

        //instead of arbitrary 20, use a getMethod from controller to figure out how many rows and columns
        double x=determineXlength(10);
        double y=determineYlength(10);
        
        yo=new Rectangle[10][10];
        
        displayGrid(500,550,x,y);
        root.getChildren().addAll(play,pause,load,speedUp,speedDown,speedText,step,t);

        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }

    private void updateSprites () {
    	//btn6.setText(""+speed+"");
    }

    private void fix(Text t)
    {
    	root.getChildren().remove(t);
    	t.setText(""+speed+"");
    	root.getChildren().add(t);
    	
    }
    //TODO: THIS METHOD SHOULD TAKE IN A PACKAGE
    private Rectangle[][] displayGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	//int yIndex=0;
    	for(int i=50;i<xtot;i+=x){
    		int yIndex=0;
    		for (int j=100;j<ytot;j+=y){
    			Rectangle rex=new Rectangle(i,j,x,y);
    			//use encapsulated info to determine color like example commented below
    			//rex.setFill(infoFromController[i][j]);
    			rex.setFill(Color.WHITE);
    			rex.setStroke(Color.BLACK);
    			rex.setStrokeWidth(0.5);
    			root.getChildren().add(rex);
    			//yo[xIndex][yIndex]=rex;
    			yIndex++;
    		}
    		xIndex++;
    	}
    	return yo;
    }
    
    public void updateGrid(Packager colorGrid){
    	for(int i=0;i<10;i++){
    		for (int j=0;j<10;j++){
    			Rectangle rex2=yo[i][j];
    			String color=colorGrid.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			setFill(rex2, Color.valueOf(color)); //converting the string to color
    		}
    	}
    	//return yo;
    }
    private double determineXlength(int numCols){
    	double xsize=totalWidthOfGrid/numCols;
    	return xsize;
    }
    
    private double determineYlength(int numRows){
    	double ysize=totalHeightOfGrid/numRows;
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