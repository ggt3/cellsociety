package viewPackage;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class View extends Application{
	
	private Integer frameRate=100;
	private Integer windowSize=600;
	private Integer speed=1;
	private Integer[][] yo = new Integer[2][2];
	private Text btn6;
	
    public static void main(String[] args) {
        launch(args);
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
    			}
    				
    			
    			
    	        KeyFrame frame = start(60);
    	        Timeline animation = new Timeline();
    	        animation.setCycleCount(Animation.INDEFINITE);
    	        animation.getKeyFrames().add(frame);
    	        animation.play();
            }
        });
        return btn;
	}
	
	protected KeyFrame start(int i) {
		// TODO Auto-generated method stub
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> updateSprites());
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
        Button btn=makeButton(primaryStage,30,1,"Play");
        Button btn2=makeButton(primaryStage,(windowSize/6)+30,1,"Pause");
        Button btn3=makeButton(primaryStage,(windowSize/12)*9,1,"    Load    ");
        
        Button btn4=makeButton(primaryStage,(windowSize/24)*13,8,"   UP   ");
        Button btn5=makeButton(primaryStage,(windowSize/24)*13,32,"DOWN");
        //Button btn6=makeButton(primaryStage,(windowSize/24)*11,1,""+speed+"");
        Text btn6=addText(""+speed+"",40,(windowSize/24)*11,50);
        Text t=addText("ERROR",20,0,windowSize-20);
        
        
        Group root = new Group();
        //drawGrid(root);
        displayGrid(root,550,550,10,10);
        root.getChildren().addAll(btn,btn2,btn3,btn4,btn5,btn6,t);
        primaryStage.setScene(new Scene(root, windowSize, windowSize, Color.WHITE));
        primaryStage.show();
    }
    private void updateSprites (Stage s,Button btn) {
    	btn.setText(""+speed+"");
    }
    private void updateSprites () {
    	//btn6.setText(""+speed+"");
    }
    
    private void drawGrid(Group root){
    	int x=10;
    	while (x<(windowSize-30))
    	{
        	Path path = new Path();
            MoveTo moveTo = new MoveTo();
            moveTo.setX(x);
            moveTo.setY(100);
           
            LineTo lineTo = new LineTo();
            lineTo.setX(x);
            lineTo.setY(540);
           
            path.getElements().add(moveTo);
            path.getElements().add(lineTo);
            path.setStrokeWidth(1);
            path.setStroke(Color.BLACK);
            root.getChildren().add(path);
            x+=20;
    	}
    	int y=100;
    	while (y<550)
    	{
        	Path path = new Path();
            MoveTo moveTo = new MoveTo();
            moveTo.setX(10);
            moveTo.setY(y);
           
            LineTo lineTo = new LineTo();
            lineTo.setX(550);
            lineTo.setY(y);
           
            path.getElements().add(moveTo);
            path.getElements().add(lineTo);
            path.setStrokeWidth(1);
            path.setStroke(Color.BLACK);
            root.getChildren().add(path);
            y+=20;
    	}
       
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
