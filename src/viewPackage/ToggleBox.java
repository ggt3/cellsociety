package viewPackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToggleBox {

	private ToggleGroup edgeType, cellShape, outline,randomized;
	private boolean isTriangleShape;
	private boolean isToroidalEdgeType;
	private boolean isNoStroke;
	private boolean isRandom;
	
	
	
	private ToggleButton createToggleButton(String name, ToggleGroup group, boolean depressed) {
		ToggleButton tb = new ToggleButton(name);
		tb.setToggleGroup(group);
		tb.setSelected(depressed);
		return tb;
	}
	private VBox createToggleGroupVBox(ToggleGroup group, String btn1, String btn2) {
		VBox vbox=new VBox();
		group = new ToggleGroup();
		ToggleButton tb1 = createToggleButton(btn1, group, true);
		ToggleButton tb2 = createToggleButton(btn2, group, false);
		setToggleProperty(group, tb1, tb2);
		vbox.getChildren().addAll(tb1,tb2);
		return vbox;
	}

	protected HBox makeToggles(){
		HBox hbox=new HBox(50);
//		VBox vbox1 = createToggleGroupVBox(edgeType, "Finite", "Toroidal");
//		VBox vbox2 = createToggleGroupVBox(cellShape, "Square", "Triangle");
//		VBox vbox3 = createToggleGroupVBox(outline, "Stroke", "No Stroke");
		
		edgeType = new ToggleGroup();
		ToggleButton tb1 = createToggleButton("Finite", edgeType, true);
		ToggleButton tb2 = createToggleButton("Toroidal", edgeType, false);
		setToggleProperty(edgeType, tb1, tb2);
		
		cellShape = new ToggleGroup();
		ToggleButton tb11 = createToggleButton("Square", cellShape, true);
		ToggleButton tb12 = createToggleButton("Triangle", cellShape, false);
		setToggleProperty(cellShape, tb11, tb12);
		
		outline = new ToggleGroup();
		ToggleButton tb21 = createToggleButton("Stroke", outline, true);
		ToggleButton tb22 = createToggleButton("No Stroke", outline, false);
		setToggleProperty(outline, tb21, tb22);
		
		randomized = new ToggleGroup();
		ToggleButton tb31 = createToggleButton("Not Random", randomized, true);
		ToggleButton tb32 = createToggleButton("Random", randomized, false);
		setToggleProperty(randomized, tb31, tb32);
		
		
		VBox vbox1=assignToVBox(tb1,tb2);
		VBox vbox2=assignToVBox(tb11,tb12);
		VBox vbox3=assignToVBox(tb21,tb22);
		VBox vbox4=assignToVBox(tb31,tb32);
//		VBox vbox1=new VBox();
//		vbox1.getChildren().addAll(tb1,tb2);
//		vbox1.setAlignment(Pos.CENTER);
//		
//		VBox vbox2=new VBox();
//		vbox2.getChildren().addAll(tb11,tb12);
//		vbox2.setAlignment(Pos.CENTER);
//		
//		VBox vbox3=new VBox();
//		vbox3.getChildren().addAll(tb21,tb22);
//		vbox3.setAlignment(Pos.CENTER);
//		VBox vbox4=new VBox();
//		vbox4.getChildren().addAll(tb31,tb32);
//		vbox4.setAlignment(Pos.CENTER);
		
	
		hbox.getChildren().addAll(vbox1,vbox2,vbox3,vbox4);
		return hbox;
	}

	private VBox assignToVBox(ToggleButton tb1,ToggleButton tb2){
		VBox vbox=new VBox();
		vbox.getChildren().addAll(tb1,tb2);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}
	
	private void setToggleProperty(ToggleGroup group, ToggleButton tb1, ToggleButton tb2) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if ((newValue == null && oldValue.toString().equals(tb1.toString()))
						|| (newValue != null && newValue.toString().equals(tb1.toString()))) {
					setToggleChangeMethod(group, false);
				} else if (newValue != null && newValue.toString().equals(tb2.toString())) {

					setToggleChangeMethod(group, true);
				}
			}

		});
	}
	
	private void setToggleChangeMethod(ToggleGroup groupName, boolean change){
		if(groupName.equals(outline)) {
			isNoStroke = change;
		}
		if(groupName.equals(edgeType)) {
			isToroidalEdgeType = change;
		}
		if (groupName.equals(cellShape)) {
			isTriangleShape = change;
		}
		if (groupName.equals(randomized)){
			isRandom = change;
		}
	}

	protected boolean getEdgeType() {
		return isToroidalEdgeType;
	}

	protected boolean getOutline() {
		return isNoStroke;
	}

	protected boolean getShape() {
		return isTriangleShape;
	}
	protected boolean getRandomized(){
		return isRandom;
	}
}
