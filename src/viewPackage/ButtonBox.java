package viewPackage;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/English";
	private ResourceBundle myResources;
	public ButtonBox(Controller c){
		control=c;
		view=new View(c);
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		
	}
	//private View view=new View(new Controller());
	//private Controller control=view.getControl();
	//private String fileName="";
	
	protected HBox makeButtonBox() {
		HBox hbox = new HBox(50);
		Button play = new Button(myResources.getString("PlayCommand"));
		play.setScaleX(2);
		play.setScaleY(2);

		play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				control.changeSpeed(view.getSpeed());
				control.playSimulation();
			}
		});
	    
		Button pause = new Button(myResources.getString("PauseCommand"));
		pause.setScaleX(2);
		pause.setScaleY(2);

		pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				control.stopSimulation();
			}
		});

		Button step = new Button(myResources.getString("StepCommand"));
		step.setScaleX(2);
		step.setScaleY(2);

		step.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				control.stepSimulation();
			}
		});

		Button load = new Button(myResources.getString("LoadCommand"));
		load.setScaleX(2);
		load.setScaleY(2);

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
							// grid.add(addText("Not a valid name. Make sure it ends in .xml",10,50,500));
							System.out.println("File Name entered is not a valid name");
						}
					}
				});

				secondStage.setTitle("Load File");
				secondStage.setScene(secScene);

				secondStage.show();

			}
		});

      speedText=view.addText(""+view.getSpeed()+"",20,0,0);
      speedText.setScaleY(2);
      speedText.setScaleX(2);
      
      VBox speedButtons = new VBox();
      Button down=new Button(myResources.getString("DownCommand"));
      Button up =new Button(myResources.getString("UpCommand"));
      //up.setLayoutX(down.getLayoutX());
      up.setMaxWidth(down.getMaxWidth());
      //Button down=new Button(myResources.getString("DownCommand"));
      up.setOnAction(new EventHandler<ActionEvent>() {
	    	 
          @Override
          public void handle(ActionEvent event) {
          	view.addToSpeed(view.getSpeed()+1);
  			System.out.println(view.getSpeed());
  			speedText.setText("" + view.getSpeed() + "");
          }
	    });
      down.setOnAction(new EventHandler<ActionEvent>() {
	    	 
          @Override
          public void handle(ActionEvent event) {
  			if (view.getSpeed() > 0)
  				view.addToSpeed(view.getSpeed()-1);
  			System.out.println(view.getSpeed());
  			speedText.setText("" + view.getSpeed() + "");
          }
	    });

      speedButtons.getChildren().addAll(up,down);
      
      speedButtons.setTranslateY(-11);
      
      hbox.setTranslateX(30);
      hbox.setTranslateY(15);
      
      System.out.println(hbox.getAlignment());
      location=speedText.getX()+300;
      System.out.println(location);
      hbox.getChildren().addAll(play,pause,step,speedText,speedButtons,load);
      hbox.setPrefWidth(view.getWindowSize());
      return hbox;
	}
	
	protected HBox makeSlider(){
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
		//return slider;
		HBox hbox=new HBox(10);
		Text t=view.addText("Adjust Speed:", 20,(int) hbox.getLayoutX(),(int) hbox.getLayoutY());
		t.setX(20);
		//t.setOnKeyPressed(ENTER);
		text = new TextField();
		
		//input=(int) text.getText().charAt(0);
//		if ((text.getText().trim() != null && !text.getText().isEmpty() && text.getText().endsWith("xml"))) 
//	        	view.addToSpeed(view.getSpeed()+input);//speed=textField.getText();
		//view.getScene().setOnKeyPressed(e -> handleKeyInput(e));
		
		Button setBtn = new Button(myResources.getString("SetSpeedCommand"));
		
		setBtn.setOnAction(new EventHandler<ActionEvent>() {

          	@Override
          	    public void handle(ActionEvent e) {
          			input=(int) text.getText().charAt(0)-48;
          			if ((text.getText().trim() != null && !text.getText().isEmpty()&&input>=1&&input<=5)) {
          	        	
          	        	System.out.println("input "+input);
                		view.addToSpeed(input);
                		speedText.setText("" + view.getSpeed() + "");
          	        } else {
          	        	System.out.println("Speed entered is not a valid speed. Please Enter a number between 1 and 5");
          	        }
          	     }
          	 });
		
		
		hbox.getChildren().addAll(t,slider,text,setBtn);
		

		return hbox;
		
		
	}

	
}
