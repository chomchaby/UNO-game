package gui;

import entity.card.UnitCard;
import entity.player.User;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameAction;
import logic.GameLogic;

public class FontCardPane extends StackPane {
	private UnitCard card;

	private static final String colorCardURL = "color.png";
	private static final String challengeCardURL = "challenge.png";
	private static final String stopRedCardURL = "stop_red.png";
	private static final String stopYellowCardURL = "stop_yellow.png";
	private static final String stopGreenCardURL = "stop_green.png";
	private static final String stopBlueCardURL = "stop_blue.png";
	private static final String rotateRedCardURL = "rotate_red.png";
	private static final String rotateYellowCardURL = "rotate_yellow.png";
	private static final String rotateGreenCardURL = "rotate_green.png";
	private static final String rotateBlueCardURL = "rotate_blue.png";
	private static final String pickRedCardURL = "plus_red.png";
	private static final String pickYellowCardURL = "plus_yellow.png";
	private static final String pickGreenCardURL = "plus_green.png";
	private static final String pickBlueCardURL = "plus_blue.png";

	public FontCardPane(UnitCard card) {
		this.card = card;
		this.setPrefHeight(110);
		this.setPrefWidth(80);
		this.draw();
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onClickHandler();
			}
		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onMouseEnteredHandler();
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onMouseExitedHandler();
			}
		});

	}

	private void onClickHandler() {
		// draw card
		if (GameLogic.getInstance().isGameEnd()) {
			return;
		} 
		else if (GameLogic.getInstance().getCurrentPlayer() != GameLogic.getInstance().getUser()) {
			return;
		} 
		else if (GameLogic.getInstance().getUser().isDrawn() == true) {
			return;
		}
		
		if (GameLogic.getInstance().getUser().getDrawableCardList().contains(card)) {
			GameLogic.getInstance().getUser().drawCard(card);
		}
	}

	private void onMouseEnteredHandler() {
		if (this.card != GameLogic.getInstance().getCardOnTable()) {
//			this.setPrefWidth(90);
		}
	}

	private void onMouseExitedHandler() {

	}

	private void draw() {

		BackgroundFill bgFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(9), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

		Rectangle shape = new Rectangle(64, 94);
		shape.setArcWidth(8);
		shape.setArcHeight(8);

		if (card.getAction() == GameAction.NONE) {
			shape.setFill(card.getColor());
			this.getChildren().add(shape);

			Text cardText = new Text(Integer.toString(card.getNumber()));
			cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 50;");
			this.getChildren().add(cardText);
		}

		else {
			if (card.getAction() == GameAction.CHANGECOLOR) {
				shape.setFill(new ImagePattern(new Image(colorCardURL)));
				this.getChildren().add(shape);

			} else if (card.getAction() == GameAction.CHALLENGE) {
				shape.setFill(new ImagePattern(new Image(challengeCardURL)));
				this.getChildren().add(shape);

			} else if (card.getAction() == GameAction.STOP) {
				if (card.getColor() == Color.RED) {
					shape.setFill(new ImagePattern(new Image(stopRedCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.YELLOW) {
					shape.setFill(new ImagePattern(new Image(stopYellowCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.GREEN) {
					shape.setFill(new ImagePattern(new Image(stopGreenCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.BLUE) {
					shape.setFill(new ImagePattern(new Image(stopBlueCardURL)));
					this.getChildren().add(shape);
				}
			} else if (card.getAction() == GameAction.ROTATE) {
				if (card.getColor() == Color.RED) {
					shape.setFill(new ImagePattern(new Image(rotateRedCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.YELLOW) {
					shape.setFill(new ImagePattern(new Image(rotateYellowCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.GREEN) {
					shape.setFill(new ImagePattern(new Image(rotateGreenCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.BLUE) {
					shape.setFill(new ImagePattern(new Image(rotateBlueCardURL)));
					this.getChildren().add(shape);
				}
			} else if (card.getAction() == GameAction.PICK) {
				if (card.getColor() == Color.RED) {
					shape.setFill(new ImagePattern(new Image(pickRedCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.YELLOW) {
					shape.setFill(new ImagePattern(new Image(pickYellowCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.GREEN) {
					shape.setFill(new ImagePattern(new Image(pickGreenCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == Color.BLUE) {
					shape.setFill(new ImagePattern(new Image(pickBlueCardURL)));
					this.getChildren().add(shape);
				}
			}

		}

	}

}
