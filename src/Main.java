import viewPackage.View;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	private Controller myControl;
	private View myView;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage primaryStage) throws Exception {
		myControl = new Controller();
		myView = new View(myControl);
		myControl.setView(myView);
		//TODO: myView.initialize(primaryStage); - shows the default load screen

		
	}
	
}
