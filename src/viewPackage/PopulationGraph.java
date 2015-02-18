package viewPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controller.ViewPackager;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class PopulationGraph {
	
	private View view;
	private Object[] keys;
	private List<XYChart.Series<Number, Number>> listOfSpecies;
	final NumberAxis xAxis, yAxis;
	final LineChart<Number,Number> lineChart;
	
	public PopulationGraph(View v){
		view = v;
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		lineChart=new LineChart<Number,Number>(xAxis,yAxis);

	}
	
	public Scene makeGraph(){
        xAxis.setLabel("Generation");      
		lineChart.setTitle("Populations");
		lineChart.setCreateSymbols(false);
		listOfSpecies = new ArrayList<XYChart.Series<Number,Number>>();

		Scene scene = new Scene(lineChart, 0, 0);
		return scene;
	}
	protected void updateGraph(ViewPackager colorGrid){
		Set<String> keySet=colorGrid.getStateTotals().keySet();
		keys = keySet.toArray();

		if (listOfSpecies.size()==0)
			addToList(keys.length);
		int generation=view.getGeneration();
		for (int i=0;i<keys.length;i++){
			Series<Number, Number> species=listOfSpecies.get(i);
			int x=colorGrid.getStateTotals().get(keys[i]);
			species.getData().add(new Data<Number, Number>(generation,x));
			Path p=(Path) species.nodeProperty().getValue();
			p.setStroke(Color.valueOf(keys[i].toString()));
		}
	}
	private void addToList(int l){
		int i=0;
		while (i<l){
			Series<Number, Number> species1= new XYChart.Series<Number, Number>();
	        listOfSpecies.add(species1);
	        lineChart.getData().add(species1);
	        i++;
		}
	}
}
