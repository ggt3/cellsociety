package viewPackage;

import controller.Controller;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CellShape {
	
	private View myView=new View(new Controller());
	
	public void displayGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			if (xIndex<5&&yIndex<5){
    			Rectangle rex=new Rectangle(i,j,x,y);
    			//Shape shap=new Shape(50,50,50);
    			Polygon poly = new Polygon();
    			poly.getPoints().addAll(new Double[]{
    			    (i+x/2), j+0.0,
    			    i+0.0, j+y,
    			    i+x, j+y });
    			poly.setFill(Color.AQUAMARINE);
    			poly.setStroke(Color.BLACK);
    			//yIndex++;
    			Polygon poly2 = new Polygon();
    			poly2.getPoints().addAll(new Double[]{
    			    i+x/2, j+0.0,
    			    i+x+x/2, j+0.0,
    			    i+x, j+y });
    			poly2.setFill(Color.DARKORCHID);
    			poly2.setStroke(Color.BLACK);
    			rex.setStroke(Color.BLACK);
    			rex.setStrokeWidth(0.5);
    			myView.addToRoot(poly);
    			myView.addToRoot(poly2);
    			//root.getChildren().addAll(rex,poly,poly2);
    			//gridView[xIndex][yIndex] = poly;
    			yIndex++;
    			//if (yIndex<5)
    				//gridView[xIndex][yIndex] = poly2;
    			}
    		}
    		xIndex++;
    	}

    }
	
	
	
}
