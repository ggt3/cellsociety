package viewPackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangularGridView extends DisplayGrid {
	

	public TriangularGridView(View mainView) {
		super(mainView);
		
	}

	public void initialEmptyGrid(int xtot, int ytot,double x,double y){
    	boolean even =true; //sets to be down triangle
    	boolean start=even;
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		even=start;
    		for (int j=100;j<=ytot-y;j+=y){
    			Shape p = new Polygon();
        			if (even) {
        				drawEvenTriangle(x, y, i, j, p);
        			}
        			else {
        				drawOddTriangle(x, y, i, j, p);
        			}
    			p.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						
						//change state instead of just color to white
						setFill(p,Color.WHITE);
					}
				}); 
    			super.outline(p);
    			getView().addToRoot(p);
    			setShape(xIndex, yIndex, p);
    			yIndex++;
    			even=!even;
    		}
    		start=!start;
    		xIndex++;
    	}
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
}
