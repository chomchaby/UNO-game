package gui;

import entity.player.Bot;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import sharedObject.AudioLoader;
import sharedObject.ImageLoader;

public class TablePane extends HBox implements Updatable {

	private BackCardPane cardPilePane;
	private FontCardPane cardOnTablePane;
	private VBox statusPane;
	private boolean isClockwise;
	private ImageView reverseImg;

	public TablePane() {
		// setting pane
		this.setSpacing(30);
		this.setAlignment(Pos.CENTER);
		this.setMaxHeight(120);

		// setting card pile
		this.cardPilePane = new BackCardPane();
		this.cardPilePane.setPrefWidth(86);
		this.cardPilePane.setPrefHeight(120);
		this.cardPilePane.draw();
		this.cardPilePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onClickHandler();
			}
		});
		this.cardPilePane.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Pane pane = (Pane) event.getSource();
				pane.setStyle("-fx-cursor: hand;");
			}
		});
		this.getChildren().add(cardPilePane);

		initializeReverseImg();
		update();

	}

	private void onClickHandler() {
		// draw a card from card pile
		// not user turn
		if (GameLogic.getInstance().isRoundEnd()) {
			return;
		}
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			return;
		}
		// not be skipped
		if (!GameLogic.getInstance().getCurrentPlayer().isPlayable()) {
			return;
		}
		// user turn, but is not time to draw
		if (GameLogic.getInstance().getUser().isDrawn() || GameLogic.getInstance().getUser().isPlaced()
				|| GameLogic.getInstance().isColorSelectionState() || GameLogic.getInstance().isChallengeState()) {
			AudioLoader.nopeSound.play();
			return;
		}

		// handler for placing card
		AudioLoader.mouseClick2Sound.play();
		GameLogic.getInstance().getUser().drawCard(1);
	}

	private void initializeReverseImg() {
		isClockwise = GameLogic.getInstance().isClockwise();
		if (isClockwise) {
			reverseImg = new ImageView(ImageLoader.clockwiseImg);
		} else {
			reverseImg = new ImageView(ImageLoader.counterClockwiseImg);
		}
		reverseImg.setFitHeight(50);
		reverseImg.setFitWidth(50);
	}

	@Override
	public void update() {
		initializeCardOnTablePane();
		initializeStatusPane();
	}

	private void initializeCardOnTablePane() {
		if (GameLogic.getInstance().isRoundEnd()) {

		} else {
			this.getChildren().remove(cardOnTablePane);
			this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
			this.cardOnTablePane.setPrefWidth(86);
			this.cardOnTablePane.setPrefHeight(120);
			this.cardOnTablePane.draw();
			this.getChildren().add(cardOnTablePane);
		}

	}

	private void initializeStatusPane() {

		if (GameLogic.getInstance().isRoundEnd()) {
			// clear
			this.getChildren().remove(statusPane);

		} else {
			// clear
			this.getChildren().remove(statusPane);

			// setting color bar
			Color color = GameLogic.getInstance().getColorState();
			Rectangle colorRec = new Rectangle(30, 30, color);
			colorRec.setArcWidth(20);
			colorRec.setArcHeight(20);

			// setting reverse sign
			if (isClockwise != GameLogic.getInstance().isClockwise()) {
				initializeReverseImg();
			} else {
				if (isClockwise) {
					reverseImg.setRotate(reverseImg.getRotate() + 30);
				} else {
					reverseImg.setRotate(reverseImg.getRotate() - 30);
				}

			}

			// combine color bar and rotation sign
			statusPane = new VBox();
			statusPane.setAlignment(Pos.CENTER);
			statusPane.setSpacing(25);
			statusPane.getChildren().addAll(colorRec, reverseImg);

			this.getChildren().add(statusPane);
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
