package viewPackage;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AllButton extends Button {

	protected View view=new View();
	protected Group root=view.getRoot();
	protected String fileName=view.getFileName();
	protected Stage s=view.getPrimaryStage();
	protected int speed=view.getSpeed();
	protected Text t=view.displayCurrentSpeed();
	
	protected AllButton makeButton(Stage s,double x,int y, String string){
		AllButton btn = new AllButton();
        btn.setText(string);
        btn.setLayoutX(x);
//        btn.setLayoutY(level);
//		if (level==1)
//		{
        makeBigger(btn);
	    btn.setLayoutY(20);
		//}
		
		handle();
		
		return btn;
	}

	private void makeBigger(Button btn) {
		btn.setScaleX(2);
	    btn.setScaleY(2);
	}
	
	private void handle(){
		this.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
    			}
		});
	}
	
	protected void setDirection(int direction){
		
	}
	
}
