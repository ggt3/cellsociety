
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage primaryStage) throws Exception {
		Controller myControl = new Controller(primaryStage);
		
		
		//myControl.testGrid(); //setting predefined grid for TEST
		
	}
	
    
}
