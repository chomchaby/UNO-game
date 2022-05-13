package gui;

import entity.player.User;
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
	private VBox currentColorPane;

	private boolean hasCardPilePane;

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
				onClickHandler();
			}
		});

		clockwiseURL = ClassLoader.getSystemResource("image/clockwise.png").toString();
		counterClockwiseURL = ClassLoader.getSystemResource("image/counterclockwise.png").toString();

		update();

	}

	@Override
	public void update() {
		initializeCardOnTablePane();
		initializeCurrentColorPane();
	}
	
	private void onClickHandler() {
		// pick a card from card pile
		if (GameLogic.getInstance().getCurrentPlayer() instanceof User) {
			if (!(GameLogic.getInstance().getUser().isPicked() || GameLogic.getInstance().isColorSelectionState())) {
				GameLogic.getInstance().getUser().pick(1);
			}
		}

	}

	private void initializeCardOnTablePane() {
		if (GameLogic.getInstance().isGameEnd()) {
			this.getChildren().remove(cardPilePane);
			this.getChildren().remove(cardOnTablePane);
			hasCardPilePane = false;

		} else {
			if (!hasCardPilePane) {
				this.getChildren().add(cardPilePane);
				hasCardPilePane = true;
			}
			this.getChildren().remove(cardOnTablePane);
			this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
			this.getChildren().add(cardOnTablePane);
		}

	}

	private void initializeCurrentColorPane() {

		if (GameLogic.getInstance().isGameEnd()) {
			this.getChildren().remove(currentColorPane);

		} else {

			this.getChildren().remove(currentColorPane);
			// setting color bar
			Color color = GameLogic.getInstance().getColorState();
			Rectangle colorRec = new Rectangle(30, 30, color);
			colorRec.setArcWidth(20);
			colorRec.setArcHeight(20);

			// setting rotation sign
			Image rotationPNG;
			if (GameLogic.getInstance().isClockwise())
				rotationPNG = new Image(clockwiseURL);
			else
				rotationPNG = new Image(counterClockwiseURL);

			ImageView imageView = new ImageView(rotationPNG);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);

			// combine color bar and rotation sign
			currentColorPane = new VBox();
			currentColorPane.setAlignment(Pos.CENTER);
			currentColorPane.setSpacing(15);
			currentColorPane.getChildren().add(colorRec);
			currentColorPane.getChildren().add(imageView);

			this.getChildren().add(currentColorPane);

		}

	}
	
//	private void initializeGameEndText() {
//
//		if (GameLogic.getInstance().isGameEnd() && !hasGameEndText) {
//		
//
//			String endPicURL;
//			String gameResult;
//
//			if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
//				endPicURL = ClassLoader.getSystemResource("lose.png").toString();
//				gameResult = "lose";
//			} else {
//				endPicURL = ClassLoader.getSystemResource("win.jpg").toString();
//				gameResult = "win";
//			}
//			this.getChildren().add(new Text("You " + gameResult + "!"));
//
//			ImageView imageView = new ImageView(new Image(endPicURL));
//			imageView.setFitWidth(200);
//			imageView.setPreserveRatio(true);
//			this.getChildren().add(imageView);
//
//			this.getChildren().add(new Text("The winner is " + GameLogic.getInstance().getCurrentPlayer().getName()));
//			hasGameEndText = true;
//
//		} else if (!GameLogic.getInstance().isGameEnd() && hasGameEndText) {
//			this.getChildren().clear();
//			hasGameEndText = false;
//			hasCardPilePane = false;
//		}
//	}

}
