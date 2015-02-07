package viewPackage;

import controller.Controller;
import controller.Packager;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DisplayGrid {


	private final int totalWidthOfGrid= 450;
	private final int totalHeightOfGrid= 450;
	private int numSquareX=0;
	private int numSquareY=0;
	private View view;
	private Shape[][] myGrid;
	public DisplayGrid(View mainView){
		view = mainView;
	}

	public void setGridSize(int sizeX, int sizeY){
		numSquareX = sizeX;
		numSquareY = sizeY;
		myGrid=new Shape[numSquareX][numSquareY];
		
		double x=determineXlength(numSquareX);
		double y=determineYlength(numSquareY);
		displayGrid(525,550,x,y);
	}

	private double determineXlength(int numCols){
		return ((double)totalWidthOfGrid)/numCols;

	}

	private double determineYlength(int numRows){
		return ((double)totalHeightOfGrid)/numRows;
	}
    public void displayGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			Shape rex=new Rectangle(i,j,x,y);
    			rex.setStroke(Color.BLACK);
    			rex.setStrokeWidth(0.5);
    			view.addToRoot(rex);//root.getChildren().add(rex);
    			myGrid[xIndex][yIndex]=rex;
    			yIndex++;
    		}
   
    		xIndex++;
    	}
    }
	
    public void updateRectangle(Packager colorGrid){
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			String color=colorGrid.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			setFill(myGrid[i][j], Color.valueOf(color));
    
    		}
    	}
    	

    }
	protected void setFill(Shape gridView3,Color c) {
		gridView3.setFill(c);
	}
}
