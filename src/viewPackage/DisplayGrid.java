package viewPackage;

import java.util.ArrayList;
import java.util.Random;

import controller.ViewPackager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class DisplayGrid {
	private final int totalWidthOfGrid= 350;
	private final int totalHeightOfGrid= 350;
	private int numSquareX = 0;
	private int numSquareY = 0;
	private View view;
	private Shape[][] myGrid;
	private boolean random=true;
	
	
	public DisplayGrid(View mainView){
		view = mainView;
	}

	private void setGridSize(int sizeX, int sizeY){
		numSquareX = sizeX;
		numSquareY = sizeY;
		myGrid=new Shape[numSquareX][numSquareY];
	}
	
	public void initializeGridView(int sizeX, int sizeY) {
		setGridSize(sizeX, sizeY);
		double x=determineXlength(numSquareX);
		double y=determineYlength(numSquareY);
		initialEmptyGrid(425,450,x,y);
		view.createGraphWindow();
	}
	private double determineXlength(int numCols){
		return ((double)totalWidthOfGrid)/numCols;
	}

	private double determineYlength(int numRows){
		return ((double)totalHeightOfGrid)/numRows;
	}
    
	public abstract void initialEmptyGrid(int xtot, int ytot,double x,double y);


	protected void outline(Shape p) {
		if (!view.getOutline()){ //if true, no outline
			p.setStroke(Color.BLACK);
			p.setStrokeWidth(0.5);
		}
	}
	
    public void updateGrid(ViewPackager viewPackager){
    	System.out.println(viewPackager.getColorGrid().toString());
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			String color=viewPackager.getColorGrid().get(i).get(j).toUpperCase(); //getting the specified color at each grid
    			setFill(myGrid[i][j], Color.valueOf(color));
    		}
    	}
    	if (random) {
    		randomizeGrid(viewPackager);
    		random =false;
    	}
    }
    
    public void setToggleColorChange(Shape p) {
		p.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				
				//change state instead of just color to white
				setFill(p,Color.WHITE);
			}
		}); 
    }
    
    private ArrayList<ArrayList<String>> randomizeGrid(ViewPackager vPackage){
    	Random r=new Random();
    	Object[] color = vPackage.getStateTotals().keySet().toArray();
    	for(int i=0;i < numSquareX;i++){
    		for (int j=0;j < numSquareY;j++){
    			int rand = r.nextInt(vPackage.getStateTotals().keySet().size());
    			setFill(myGrid[i][j], Color.valueOf(color[rand].toString()));
    		}
    	}
    	return shapeToColorList();
    }
    
    private ArrayList<ArrayList<String>> shapeToColorList() {
    	ArrayList<ArrayList<String>> newGrid = new ArrayList<ArrayList<String>>();
    	for(int i=0;i < numSquareX;i++){
    		ArrayList<String> colorRow = new ArrayList<String>();
    		for (int j=0;j < numSquareY;j++){
    			colorRow.add(myGrid[i][j].getFill().toString());
    		}
    		newGrid.add(colorRow);
    	}
    	return newGrid;
    }
    
	protected void setFill(Shape gridView, Color c) {
		gridView.setFill(c);
	}
	
	protected View getView() {
		return view;
	}
	protected void setShape(int row, int col, Shape p) {
		myGrid[row][col] = p;
	}
	
}
