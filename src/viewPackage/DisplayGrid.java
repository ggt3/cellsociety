package viewPackage;

import java.util.ResourceBundle;

import controller.Controller;
import controller.Packager;
import controller.ViewPackager;
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
	
	
	public DisplayGrid(View mainView){
		view = mainView;
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
//        public void displayGrid(int xtot, int ytot,double x,double y){
//        	boolean even=true;//=false;
//        	boolean start=even;
//        	int xIndex=0;
//        	for(int i=75;i<=xtot-x;i+=x){
//        		int yIndex=0;
//        		even=start;
//        		for (int j=100;j<=ytot-y;j+=y){
//        			//Rectangle rex=new Rectangle(i,j,x,y);
//        			Polygon p=new Polygon();
//        			if (even){
//        				p.getPoints().addAll(new Double[]{
//            					i+0.0, j+0.0,
//            					i+x+x, j+0.0,
//            					i+x, j+y });
//        			}
//        			else{
//        			p.getPoints().addAll(new Double[]{
//        					i+x, j+0.0,
//        					i+0.0, j+y,
//        					i+x+x, j+y });
//        			}
//        			p.setStroke(Color.BLACK);
//        			p.setStrokeWidth(0.5);
//        			view.addToRoot(p);//root.getChildren().add(rex);
//        			myGrid[xIndex][yIndex]=p;
//        			yIndex++;
//        			even=!even;
//        		}
//        		start=!start;
//        		xIndex++;
//        	}
//    }
	
    public void updateGrid(ViewPackager viewPackager){
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			String color=viewPackager.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			setFill(myGrid[i][j], Color.valueOf(color));
    
    		}
    	}
    	

    }
	protected void setFill(Shape gridView3,Color c) {
		gridView3.setFill(c);
	}
}
