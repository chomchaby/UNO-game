package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import logic.GameLogic;

public class StatusPane extends HBox implements Updatable{
	
	private Image rotationPNG;
	private final String clockwiseURL;
	private final String counterClockwiseURL;
	
	public StatusPane() {
		
		clockwiseURL = ClassLoader.getSystemResource("clockwise.png").toString();
		counterClockwiseURL = ClassLoader.getSystemResource("counterclockwise.png").toString();

		rotationPNG = new Image(clockwiseURL);
		ImageView imageView = new ImageView(rotationPNG);
		imageView.setFitHeight(60);
		imageView.setFitWidth(60);
		BorderPane imagePane = new BorderPane(imageView);
		this.getChildren().add(imagePane);
	}
	
	@Override
	public void updateCardInPane() {
		setRotationPane();
	}
	
	private void setRotationPane() {
		if (GameLogic.getInstance().isClockwise())
			rotationPNG = new Image(clockwiseURL);
		else
			rotationPNG = new Image(counterClockwiseURL);
	}


}
