package viewPackage;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.Controller;


public class ButtonBox {

	//private Controller control=new Controller();
	private Controller control;//=view.getControl();
	private View view;
	private String fileName="";
	private double location;
	private TextField text;
	private int input;
	private Text speedText;
	private Integer speed = 1;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/English";
	private ResourceBundle myResources;
	public ButtonBox(Controller c,View v){
		control=c;
		view=v;
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		
	}
	private Button makeButton(String name) {
		Button btn = new Button(myResources.getString(name));
		btn.setScaleX(2);
		btn.setScaleY(2);
		return btn;
	}
	
	protected HBox makeButtonBox() {
		HBox hbox = new HBox(50);
		Button play = makeButton("PlayCommand");
		play.setOnAction(e -> { 
				control.changeSpeed(view.getSpeed());
				control.playSimulation();
				});
	    
		Button pause = makeButton("PauseCommand");
		pause.setOnAction(e-> control.stopSimulation()); 
		
		Button step = makeButton("StepCommand");
		step.setOnAction(e-> control.stepSimulation());

		Button load = makeButton("LoadCommand");
		
		load.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Label secondLabel = new Label("Please Enter Your File Name:");

				GridPane grid = new GridPane();
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10);
				grid.setVgap(10);
				grid.setPadding(new Insets(25, 25, 25, 25));

				TextField textField = new TextField();
				grid.add(textField, 0, 1);
				grid.getChildren().add(secondLabel);

				Scene secScene = new Scene(grid, 400, 400);

				Button hitBtn = new Button(myResources.getString("GoCommand"));
				hitBtn.setAlignment(Pos.BOTTOM_RIGHT);
				grid.add(hitBtn, 1, 4);

				Stage secondStage = new Stage();
				hitBtn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						if ((textField.getText().trim() != null && !textField.getText().isEmpty() && textField
								.getText().endsWith("xml"))) {
							fileName = textField.getText();
							view.setFileName(fileName);

							secondStage.close();

							view.tryLoad(fileName);
						} else {
							// grid.add(addText("Not a valid name. Make sure it ends in .xml",10,50,500))
							view.createErrorWindow(myResources.getString("InvalidFileName"));
						}
					}
				});

				secondStage.setTitle("Load File");
				secondStage.setScene(secScene);

				secondStage.show();

			}
		});

      speedText=view.addText(speed.toString(),20,0,0);
      speedText.setScaleY(2);
      speedText.setScaleX(2);
 
      hbox.setTranslateX(30);
      hbox.setTranslateY(15);

      hbox.getChildren().addAll(play,pause,step,load,speedText);
      //hbox.setPrefWidth(view.getWindowSize());
      hbox.setAlignment(Pos.CENTER);
      return hbox;
	}
	
	protected HBox makeSlider(){
		System.out.println("WHOA DER CHECK DIS OUT: "+view.getShape());
		Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(5);
		System.out.println(location);
		slider.setLayoutX(location);
		//slider.setValue(1);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		//slider.setMinorTickCount(3);
		slider.setBlockIncrement(1);
		//slider.getOnDragDetected();
		slider.setSnapToTicks(true);
		
		
		updateSpeed((int)slider.getValue());
		speedText.setText("" + view.getSpeed() + "");
		HBox hbox=new HBox(10);
		Text t=view.addText("Adjust Speed:", 20,(int) hbox.getLayoutX(),(int) hbox.getLayoutY());
		t.setX(20);
		//t.setOnKeyPressed(ENTER);
		text = new TextField();
		
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				updateSpeed((int)slider.getValue());
				speedText.setText(speed.toString());
			}
		    
		});
		
		
		Button setBtn = new Button(myResources.getString("SetSpeedCommand"));
		
		setBtn.setOnAction(new EventHandler<ActionEvent>() {

          	@Override
          	    public void handle(ActionEvent e) {
          			input=(int) text.getText().charAt(0)-48;
          			//System.out.println("PRACTICE: "+);
          			if ((text.getText().trim() != null && !text.getText().isEmpty()&&input>=1&&input<=5&& text.getText().length()==1)) {
          	        	
          	        	System.out.println("input "+input);
                		updateSpeed(input);
                		speedText.setText(speed.toString());
                		slider.setValue(input);
          	        } else {
          	        	view.createErrorWindow(myResources.getString("InvalidSpeed"));
          	        	
          	        }
          	     }
          	 });
		
		
		hbox.getChildren().addAll(t,slider,text,setBtn);
		

		return hbox;
		
		
	}
	public int getSpeed(){
		return speed;
	}
	private void updateSpeed(int s){
		speed=s;
	}
	
}
