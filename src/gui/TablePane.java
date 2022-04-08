package gui;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import logic.GameLogic;

public class TablePane extends HBox implements Updatable{

	private BackCardPane cardPilePane;
	private FontCardPane cardOnTablePane;
	private Image rotationPNG;
	private final String clockwiseURL;
	private final String counterClockwiseURL;

	public TablePane() {

		clockwiseURL = ClassLoader.getSystemResource("clockwise.png").toString();
		counterClockwiseURL = ClassLoader.getSystemResource("counterclockwise.png").toString();

		this.cardPilePane = new BackCardPane();
		this.cardPilePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				cardPilePaneOnClickHandler();
			}
		});
		this.getChildren().add(cardPilePane);

		this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
		this.getChildren().add(cardOnTablePane);

		rotationPNG = new Image(clockwiseURL);
		ImageView imageView = new ImageView(rotationPNG);
		imageView.setFitHeight(60);
		imageView.setFitWidth(60);
		BorderPane imagePane = new BorderPane(imageView);
		this.getChildren().add(imagePane);

	}
	@Override
	public void updateCardInPane() {
		setCardOnTablePane();
		setRotationPane();
	}

	private void cardPilePaneOnClickHandler() {

	}

	private void setRotationPane() {
		if (GameLogic.getInstance().isClockwise())
			rotationPNG = new Image(clockwiseURL);
		else
			rotationPNG = new Image(counterClockwiseURL);
	}

	private void setCardOnTablePane() {
		this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
	}

}
