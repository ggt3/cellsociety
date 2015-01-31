package viewPackage;


import java.awt.Dimension;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.util.Duration;

//import org.controlsfx.dialog.Dialogs;


public class View {
    private Scene myScene;
	private Integer frameRate=100;
	private Integer windowSize=600;
	private Integer speed=1;
	private Integer[][] yo = new Integer[2][2];
	private Text btn6;
	private Text t;
	private Group root;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 600);
	private ResourceBundle myResources;
	private String fileName="";
	private Controller control;

    public View() {
    	
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
            	s.setTitle("Cell Society");
    			s.show();
    			
    			if(string.equals("   UP   "))
    			{
    				speed++;
    				System.out.println(speed);
    				btn6.setText(""+speed+"");
    			}
    			if(string.equals("DOWN"))
    			{
    				if (speed>0)
    					speed--;
    				System.out.println(speed);
    				btn6.setText(""+speed+"");
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

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cell Society");
        
        double currWidth=primaryStage.getWidth();
        Button btn=makeButton(primaryStage,20,1,"Play");
        Button btn2=makeButton(primaryStage,(windowSize/6)+10,1,"Pause");
        Button btn3=makeButton(primaryStage,(windowSize/12)*9,1,"    Load    ");
        Button btn7=makeButton(primaryStage,(windowSize/6)*2+20,1,"Step");
        Button btn4=makeButton(primaryStage,(windowSize/24)*13,8,"   UP   ");
        Button btn5=makeButton(primaryStage,(windowSize/24)*13,32,"DOWN");
        //Button btn6=makeButton(primaryStage,(windowSize/24)*11,1,""+speed+"");
        btn6=addText(""+speed+"",40,(windowSize/24)*12,50);
        t=addText("ERROR",20,0,windowSize-20);
        
        
        root = new Group();

        displayGrid(root,550,550,10,10);
        root.getChildren().addAll(btn,btn2,btn3,btn4,btn5,btn6,t,btn7);

        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }
    private void updateSprites (Stage s,Button btn) {
    	btn.setText(""+speed+"");
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
    
    private void displayGrid(Group root,int xtot, int ytot,int x,int y){
    	for(int i=50;i<xtot;i+=x+1)
    	{
    		for (int j=100;j<ytot;j+=y+1)
    		{
    			Rectangle rex=new Rectangle(i,j,x,y);
    			rex.setFill(Color.BLUE);
    			root.getChildren().add(rex);
    		}
    			//System.out.println();
    		
    	}
    }

	private void setFill(Rectangle rex,Color blue) {
		// TODO Auto-generated method stub
		rex.setFill(Color.BLUE);
		//return null;
	}
}