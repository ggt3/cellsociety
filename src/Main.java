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
		//myControl = new Controller(new View());
		myView=new View();
		
	}
	
}
