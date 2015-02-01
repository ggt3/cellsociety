package viewPackage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SpeedButton extends AllButton {

	private Integer direction;
	
	private void handle(){
		this.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				s.setTitle("Cell Society");
    			s.show();
    			speed+=direction;
    			System.out.println(speed);
    			t.setText(""+speed+"");
    			}
		});
	}
	
	protected void setDirection(int direct){
		direction=direct;
	}
	
	private void makeBigger(Button btn){
		
	}
	
}
