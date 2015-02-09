package viewPackage;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToggleBox {

	private Controller control;//=view.getControl();
	private View view;
	
	public ToggleBox(Controller c,View v){
		control=c;
		view=v;
	}
	
	
	protected HBox makeToggles(){
		HBox hbox=new HBox(20);
		

		ToggleGroup edgeType = new ToggleGroup();

		ToggleButton tb1 = new ToggleButton("Finite");
		tb1.setToggleGroup(edgeType);
		tb1.setSelected(true);

		
		
		ToggleButton tb2 = new ToggleButton("Toroidal");
		tb2.setToggleGroup(edgeType);
		
		//tb2.getLabelPadding()
		edgeType.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(newValue.toString().equals(tb2.toString())){
					view.setEdgeType(true);
				}
				else if (newValue.toString().equals(tb1.toString())){
					view.setEdgeType(false);
				}
			}

		});
		
		ToggleGroup cellShape = new ToggleGroup();

		ToggleButton tb11 = new ToggleButton("Square");
		tb11.setToggleGroup(cellShape);
		tb11.setSelected(true);
		ToggleButton tb12 = new ToggleButton("Triangle");
		tb12.setToggleGroup(cellShape);
		
		cellShape.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(newValue.toString().equals(tb11.toString())){
					view.setShape(true);
				}
				else if (newValue.toString().equals(tb12.toString())){
					view.setShape(false);
				}
			}

		});
		
		ToggleGroup outline = new ToggleGroup();
		
		ToggleButton tb21 = new ToggleButton("Stroke");
		tb21.setToggleGroup(outline);
		tb21.setSelected(true);
		ToggleButton tb22 = new ToggleButton("No Stroke");
		tb22.setToggleGroup(outline);
		
		outline.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(newValue.toString().equals(tb21.toString())){
					view.setOutline(true);
				}
				else if (newValue.toString().equals(tb22.toString())){
					view.setOutline(false);
				}
			}

		});
		
		
		tb1.setStyle("-fx-base: lightgreen;");
		tb2.setStyle("-fx-base: lightblue;");
		tb11.setStyle("-fx-base: salmon;");
		
		VBox vbox1=new VBox();
		vbox1.getChildren().addAll(tb1,tb2);
		VBox vbox2=new VBox();
		vbox2.getChildren().addAll(tb11,tb12);
		VBox vbox3=new VBox();
		vbox3.getChildren().addAll(tb21,tb22);
		
		//ToggleButton tb1 = new ToggleButton();
		hbox.getChildren().addAll(vbox1,vbox2,vbox3);//tb1,tb2,tb11,tb12,tb21,tb22);
		return hbox;
	}
}
