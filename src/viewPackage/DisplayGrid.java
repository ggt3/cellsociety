package viewPackage;

import java.util.Random;

import controller.ViewPackager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DisplayGrid {


	private final int totalWidthOfGrid= 350;
	private final int totalHeightOfGrid= 350;
	private int numSquareX=0;
	private int numSquareY=0;
	private View view;
	private Shape[][] myGrid;
	private boolean isTriangle;
	private boolean random=true;
	
	
	public DisplayGrid(View mainView){
		view = mainView;
		//shape=mainView.getShape();
	}

	public void setGridSize(int sizeX, int sizeY){
		numSquareX = sizeX;
		numSquareY = sizeY;
		myGrid=new Shape[numSquareX][numSquareY];
		double x=determineXlength(numSquareX);
		double y=determineYlength(numSquareY);
		displayGrid(425,450,x,y);
	}

	private double determineXlength(int numCols){
		return ((double)totalWidthOfGrid)/numCols;

	}

	private double determineYlength(int numRows){
		return ((double)totalHeightOfGrid)/numRows;
	}
    public void displayGrid(int xtot, int ytot,double x,double y){
    	boolean even=true;//=false;
    	boolean start=even;
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		even=start;
    		for (int j=100;j<=ytot-y;j+=y){
    			Shape p;
    			isTriangle=view.getShape();
    			if (!isTriangle){
    				p=new Rectangle(i,j,x,y);
    			}
    			else{
    				p=new Polygon();
        			if (even)
        				drawEvenTriangle(x, y, i, j, p);
        			else
        				drawOddTriangle(x, y, i, j, p);
    			}
    			p.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						
						//change state instead of just color to white
						setFill(p,Color.WHITE);
					}
				}); 
    			outline(p);
    			view.addToRoot(p);
    			myGrid[xIndex][yIndex]=p;
    			yIndex++;
    			even=!even;
    		}
    		start=!start;
    		xIndex++;
    	}
    	System.out.printf("edge %b outline %b shape %b", view.getEdgeType(), view.getOutline(), view.getShape());
    	view.createGraphWindow();
    }

	private void drawEvenTriangle(double x, double y, int i, int j, Shape p) {
		((Polygon) p).getPoints().addAll(new Double[]{
				i+0.0, j+0.0,
				i+x+x, j+0.0,
				i+x, j+y });
	}

	private void drawOddTriangle(double x, double y, int i, int j, Shape p) {
		((Polygon) p).getPoints().addAll(new Double[]{
				i+x, j+0.0,
				i+0.0, j+y,
				i+x+x, j+y });
	}

	private void outline(Shape p) {
		if (!view.getOutline()){ //if true, no outline
			p.setStroke(Color.BLACK);
			p.setStrokeWidth(0.5);
		}
	}
	
    public void updateGrid(ViewPackager viewPackager){
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			String color=viewPackager.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			setFill(myGrid[i][j], Color.valueOf(color));
    
    		}
    	}
    	//view.createGraphWindow();
    	if (random)
    		randomizeGrid();
    }
    public void randomizeGrid(){
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			Random r=new Random();
    			int p=r.nextInt(3);
    			//int poo= (int) Math.random();
    			if (p==0)
    				setFill(myGrid[i][j], Color.VIOLET);
    			else if (p==1)
    				setFill(myGrid[i][j], Color.TURQUOISE);
    			else
    				setFill(myGrid[i][j], Color.BROWN);
    		}
    	}
    	random=false;
    }
    
	protected void setFill(Shape gridView3,Color c) {
		gridView3.setFill(c);
	}
	
	
	
}
