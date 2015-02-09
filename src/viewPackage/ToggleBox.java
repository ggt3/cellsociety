package viewPackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToggleBox {

	private View view;
	
	public ToggleBox(View v){
		view=v;
	}
	
	private ToggleButton createToggleButton(String name, ToggleGroup group, boolean depressed) {
		ToggleButton tb = new ToggleButton(name);
		tb.setToggleGroup(group);
		tb.setSelected(depressed);
		return tb;
	}

	protected HBox makeToggles(){
		HBox hbox=new HBox(20);
		
		ToggleGroup edgeType = new ToggleGroup();
		ToggleButton tb1 = createToggleButton("Finite", edgeType, true);
		ToggleButton tb2 = createToggleButton("Toroidal", edgeType, false);

		edgeType.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null&&newValue.toString().equals(tb1.toString())){
					view.setEdgeType(false);
				}
				else if (newValue!=null&&newValue.toString().equals(tb2.toString())){
					view.setEdgeType(true);
				}
			}

		});
		
		ToggleGroup cellShape = new ToggleGroup();

		ToggleButton tb11 = createToggleButton("Square", cellShape, true);
		ToggleButton tb12 = createToggleButton("Triangle", cellShape, false);
		
		cellShape.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if((newValue==null&&oldValue.toString().equals(tb11.toString()))||newValue.toString().equals(tb11.toString())){
					view.setShape(false);
				}
				else if (newValue!=null&&newValue.toString().equals(tb12.toString())){
					view.setShape(true);
				}
			}

		});
		
		ToggleGroup outline = new ToggleGroup();
		ToggleButton tb21 = createToggleButton("Stroke", outline, true);
		ToggleButton tb22 = createToggleButton("No Stroke", outline, false);

		
		outline.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null&&newValue.toString().equals(tb21.toString())){
					view.setOutline(false);
				}
				else if (newValue!=null&&newValue.toString().equals(tb22.toString())){
					view.setOutline(true);
				}
			}

		});
		
		
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
