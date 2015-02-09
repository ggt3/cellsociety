package viewPackage;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class PopulationGraph {
	
	private View view;
	private List<XYChart.Series> listOfSpecies;
	//private XYChart.Series species1;
	//private XYChart.Series species2;
	public PopulationGraph(View v){
		view=v;
	}
	
	public Scene makeGraph(){
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Generation");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Populations");
        //defining a series
        listOfSpecies=new ArrayList<XYChart.Series>();
        XYChart.Series species1= new XYChart.Series();
        XYChart.Series species2= new XYChart.Series();
        listOfSpecies.add(species1);
        listOfSpecies.add(species2);
        //species1.setName("Percentage of Different Populations");
        //populating the series with data
        
        Scene scene  = new Scene(lineChart,0,0);
        lineChart.getData().addAll(species1,species2);
        addToSeries(species1,20,1);
        addToSeries(species2,40,1);
        addToSeries(species1,30,2);
        addToSeries(species2,10,2);
       return scene;
	}
	
	protected void addToSeries(XYChart.Series species, int percentage, int generation){
		XYChart.Series correctSpecies=new XYChart.Series();//=listOfSpecies.indexOf(species);
		
		for (XYChart.Series s: listOfSpecies){
			System.out.println(s.toString());
			if (species.equals(s.toString())){
				correctSpecies=s;
				break;
			}	
		}
		//listOfSpecies[0].getData().add(new XYChart.Data(generation, percentage));
		species.getData().add(new XYChart.Data(generation, percentage));

		//correctSpecies.getData().add(new XYChart.Data(generation, percentage));
	}
}
