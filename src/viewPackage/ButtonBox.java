package viewPackage;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class ButtonBox {
	private View view;
	private String fileName="";
	private double location;
	private int input;
	private Text speedText;
	private Integer speed = 1;
	private ResourceBundle myResources;
	
	public ButtonBox(View v){
		view=v;
		myResources= v.getResourcePath();
	}
	
	private Button makeButton(String name) {
		Button btn = new Button(myResources.getString(name));
		btn.setScaleX(2);
		btn.setScaleY(2);
		return btn;
	}
	
	private Text addText(String s,int size,int xLocation,int yLocation){
        Text t = new Text(xLocation,yLocation,s);
        t.setFont(Font.font ("Verdana", size));
        t.setCache(true);
        t.setFill(Color.BLACK);
        return t;
	}
	
	protected HBox makeButtonBox() {
		HBox hbox = new HBox(50);
		Button play = makeButton("PlayCommand");
		play.setOnAction(e -> view.setPlayAction());

		Button pause = makeButton("PauseCommand");
		pause.setOnAction(e -> view.setPause());

		Button step = makeButton("StepCommand");
		step.setOnAction(e -> view.setStepSimulation());

		Button load = makeButton("LoadCommand");

		load.setOnAction(e -> loadFileBox());

		speedText = addText(speed.toString(), 20, 0, 0);
		speedText.setScaleY(2);
		speedText.setScaleX(2);

		hbox.setTranslateX(30);
		hbox.setTranslateY(15);

		hbox.getChildren().addAll(play, pause, step, load, speedText);
		hbox.setAlignment(Pos.CENTER);
		return hbox;

	}

	protected HBox makeSlider(){
		
		Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(5);
	
		slider.setLayoutX(location);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		
		slider.setBlockIncrement(1);
		slider.setSnapToTicks(true);
		
		
		updateSpeed((int)slider.getValue());
		speedText.setText(speed.toString());
		HBox hbox=new HBox(10);
		Text t= addText("Adjust Speed:", 20,(int) hbox.getLayoutX(),(int) hbox.getLayoutY());
		t.setX(20);

		TextField text = new TextField();
		
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				updateSpeed((int)slider.getValue());
				speedText.setText(speed.toString());
			}
		    
		});
		
		
		Button setBtn = new Button(myResources.getString("SetSpeedCommand"));

		setBtn.setOnAction(e -> {
			input = (int) text.getText().charAt(0) - 48;
			if ((text.getText().trim() != null && !text.getText().isEmpty() && input >= 1 && input <= 5 && text
					.getText().length() == 1)) {

				updateSpeed(input);
				speedText.setText(speed.toString());
				slider.setValue(input);
			} else {
				view.createErrorWindow(myResources.getString("InvalidSpeed"));

			}

		});

		hbox.getChildren().addAll(t, slider, text, setBtn);
		return hbox;

	}
	private void loadFileBox() {

		GridPane grid = new GridPane();
		HBox hbox1= new HBox();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		TextField textField = new TextField();
		textField.setText("enter your file name");
		
		Button hitBtn = new Button(myResources.getString("GoCommand"));

		hbox1.getChildren().addAll(textField, hitBtn);
		grid.getChildren().addAll( hbox1);
		Stage secondStage = new Stage();
		hitBtn.setOnAction(f -> {

			if ((textField.getText().trim() != null && !textField.getText().isEmpty() && textField.getText()
					.endsWith("xml"))) {
				fileName = textField.getText();
				view.setFileName(fileName);
				secondStage.close();
				try {
					view.tryLoad(fileName);
				} catch (ParserConfigurationException | SAXException | IOException e1) {

					e1.printStackTrace();
				}
			} else {
				view.createErrorWindow(myResources.getString("InvalidFileName"));

			}
		});
		Scene secScene = new Scene(grid, 400, 200);
		secondStage.setTitle("Load File");
		secondStage.setScene(secScene);
		secondStage.show();


	}

	public int getSpeed() {
		return speed;
	}

	private void updateSpeed(int s) {
		speed = s;
	}

}
