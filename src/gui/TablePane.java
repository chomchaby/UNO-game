package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;

public class TablePane extends HBox implements Updatable {

	private BackCardPane cardPilePane;
	private FontCardPane cardOnTablePane;
	private Color color;
	private Image rotationPNG;
	private final String clockwiseURL;
	private final String counterClockwiseURL;

	public TablePane() {
		// setting pane
		this.setSpacing(30);
		this.setAlignment(Pos.CENTER);

		// setting card pile
		this.cardPilePane = new BackCardPane();
		this.cardPilePane.setPrefWidth(80);
		this.cardPilePane.setMaxHeight(110);
		this.cardPilePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				cardPilePaneOnClickHandler();
			}
		});
		this.getChildren().add(cardPilePane);

		// setting card on table
		this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
		this.cardOnTablePane.setMaxHeight(110);
		this.getChildren().add(cardOnTablePane);

		// setting color bar
		color = GameLogic.getInstance().getColorState();
		Rectangle colorRec = new Rectangle(25, 25, color);
		colorRec.setArcWidth(15);
		colorRec.setArcHeight(15);

		// setting rotation sign
		clockwiseURL = ClassLoader.getSystemResource("clockwise.png").toString();
		counterClockwiseURL = ClassLoader.getSystemResource("counterclockwise.png").toString();
		rotationPNG = new Image(clockwiseURL);
		ImageView imageView = new ImageView(rotationPNG);
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);

		// combine color bar and rotation sign
		VBox imagePane = new VBox();
		imagePane.setAlignment(Pos.CENTER);
		imagePane.setSpacing(15);
		imagePane.getChildren().add(colorRec);
		imagePane.getChildren().add(imageView);

		this.getChildren().add(imagePane);

	}

	@Override
	public void update() {
		setCardOnTablePane();
		color = GameLogic.getInstance().getColorState();
		setRotationImage();
	}

	private void setCardOnTablePane() {
		this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
		this.cardOnTablePane.setMaxHeight(110);
	}

	private void setRotationImage() {
		if (GameLogic.getInstance().isClockwise())
			rotationPNG = new Image(clockwiseURL);
		else
			rotationPNG = new Image(counterClockwiseURL);
	}

	private void cardPilePaneOnClickHandler() {
		if (GameLogic.getInstance().getCurrentPlayer() != GameLogic.getInstance().getUser())
			return;
		else {
			GameLogic.getInstance().getUser().pick(1);
		}
	}

}
