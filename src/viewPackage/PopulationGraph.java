package viewPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controller.ViewPackager;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class PopulationGraph {
	
	private View view;
	private Object[] keys;
	private List<XYChart.Series> listOfSpecies;
	final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
	final LineChart<Number,Number> lineChart=new LineChart<Number,Number>(xAxis,yAxis);

	public PopulationGraph(View v){
		view=v;
	}
	
	public Scene makeGraph(){
        xAxis.setLabel("Generation");      
        lineChart.setTitle("Populations");
        listOfSpecies=new ArrayList<XYChart.Series>();
        
        Scene scene  = new Scene(lineChart,0,0);
       return scene;
	}
	protected void updateGraph(ViewPackager colorGrid){
		Set<String> keySet=colorGrid.getStateTotals().keySet();
		keys= keySet.toArray();

		if (listOfSpecies.size()==0)
			addToList(keys.length);
		int generation=view.getGeneration();
		for (int i=0;i<keys.length;i++){
			XYChart.Series species=listOfSpecies.get(i);
			int x=colorGrid.getStateTotals().get(keys[i]);
			System.out.println("x: "+x + "generation: "+generation+"keys.length"+keys.length);
			species.getData().add(new XYChart.Data(generation,x));
			Path p=(Path) species.nodeProperty().getValue();
			p.setStroke(Color.valueOf(keys[i].toString()));
		}
	}
	private void addToList(int l){
		int i=0;
		while (i<l){
			XYChart.Series species1= new XYChart.Series();
	        listOfSpecies.add(species1);
	        lineChart.getData().add(species1);
	        i++;
		}
	}
}
