package viewPackage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ErrorDisplay {
	
	public Scene display(String s){
		//Scene s=new Scene();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Label secondLabel = new Label(s);

		grid.getChildren().add(secondLabel);

		Scene secScene = new Scene(grid, 350, 150);
		return secScene;
	}
	
}
