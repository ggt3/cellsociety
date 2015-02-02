package viewPackage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadButton extends AllButton {

	
	
	private void handle(){
		this.setOnAction(new EventHandler<ActionEvent>() {
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
    	        
    	        Button hitBtn = new Button("Go");
    	        hitBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	        grid.add(hitBtn, 1, 4);
    	        
    	        
    	        
    	        Stage secondStage = new Stage();
    	        hitBtn.setOnAction(new EventHandler<ActionEvent>() {
    	        	
    	        	@Override
    	        	    public void handle(ActionEvent e) {
    	        	        if ((textField.getText().trim() != null && !textField.getText().isEmpty() && textField.getText().endsWith("xml"))) {
    	        	        	fileName=textField.getText();
    	        	        	System.out.println(fileName);
    	        	        	secondStage.close();
    	        	            Text texty=view.addText(fileName,20,0,90);
    	        	            root.getChildren().add(texty);
    	        	        } else {
    	        	        	System.out.println("File Name entered is not a valid name");
    	        	        }
    	        	     }
    	        	 });
    	        secondStage.setTitle("Load File");
    	        secondStage.setScene(secScene);
    	         
    	        //Set position of second window, related to primary window.
    	        secondStage.setX(s.getX() + 100);
    	        secondStage.setY(s.getY() + 100);
    	        secondStage.show();
    			}
		});
		
		
	
	}
	
}
