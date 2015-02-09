package viewPackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangularGridView extends DisplayGrid {

	public RectangularGridView(View mainView) {
		super(mainView);
	
	}
	public void initialEmptyGrid(int xtot, int ytot,double x,double y){
    	int xIndex=0;
    	for(int i=75;i<=xtot-x;i+=x){
    		int yIndex=0;
    		for (int j=100;j<=ytot-y;j+=y){
    			Shape p = new Rectangle(i,j,x,y);

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
    			
    		}
    		xIndex++;
    	}
    }
	

}
