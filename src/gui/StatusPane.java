package gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.GameLogic;

public class StatusPane extends VBox implements Updatable{
	
	private final String logoURL;
	private Text currentPlayer;
	
	public StatusPane() {
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		// setting logo
		logoURL = ClassLoader.getSystemResource("java.png").toString();
		Image logoPNG = new Image(logoURL);
		ImageView imageView = new ImageView(logoPNG);
		imageView.setFitHeight(60);
		imageView.setFitWidth(60);
		
		// setting player turn and status
		String name = GameLogic.getInstance().getCurrentPlayer().getName();
		currentPlayer = new Text("Current Player: " + name);
		
		this.getChildren().add(imageView);
		this.getChildren().add(currentPlayer);
	}
	
	@Override
	public void updateCardInPane() {
		
	}

}
