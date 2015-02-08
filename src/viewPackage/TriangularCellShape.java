package viewPackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TriangularCellShape extends CellShape {
    public void displayGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			Shape rex=new Rectangle(i,j,x,y);
    			rex.setStroke(Color.BLACK);
    			rex.setStrokeWidth(0.5);
    			//view.addToRoot(rex);//root.getChildren().add(rex);
    			//myGrid[xIndex][yIndex]=rex;
    			yIndex++;
    		}
   
    		xIndex++;
    	}
    }
    
    public void displayGrid2(int xtot, int ytot,double x,double y){
    	boolean even;//=false;
    	boolean start=true;
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		even=!start;
    		for (int j=100;j<=ytot-y;j+=y){
    			//Rectangle rex=new Rectangle(i,j,x,y);
    			Polygon p=new Polygon();
    			if (even){
    				p.getPoints().addAll(new Double[]{
        					i+x/2, j+0.0,
        					i+x+x/2, j+0.0,
        					i+x, j+y });
    			}
    			else{
    			p.getPoints().addAll(new Double[]{
    					i+x+x/2, j+y,
    					i+x+x/2, j+0.0,
    					i+x, j+y });
    			}
    			p.setStroke(Color.BLACK);
    			p.setStrokeWidth(0.5);
    			root.getChildren().add(p);
    			gridView2[xIndex][yIndex] = p;
    			yIndex++;
    			even=!even;
    		}
    		xIndex++;
    	}
    }
}
