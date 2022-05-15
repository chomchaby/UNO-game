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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameAction;
import logic.GameLogic;
import sharedObject.AudioLoader;
import sharedObject.ColorLoader;
import sharedObject.ImageLoader;

public class FontCardPane extends StackPane {
	private UnitCard card;

	public FontCardPane(UnitCard card) {
		this.card = card;
		this.setPrefHeight(100);
		this.setPrefWidth(72);
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
		// place card
		// not user turn
		if (GameLogic.getInstance().isGameEnd()) {
			return;
		} 
		if (GameLogic.getInstance().getCurrentPlayer() != GameLogic.getInstance().getUser()) {
			return;
		} 
		// not a card in userDeckPane
		if (GameLogic.getInstance().getCardOnTable() == card) {
			return;
		} 
		// user turn, but is not time to place
		if (GameLogic.getInstance().getUser().isPlaced() || GameLogic.getInstance().isColorSelectionState() || GameLogic.getInstance().isChallengeState()) {
			AudioLoader.nopeSound.play();
			return;
		}

		// handler for placing card
		if (GameLogic.getInstance().getUser().getPlaceableCardList().contains(card)) {
			AudioLoader.mouseClick1Sound.play();
			GameLogic.getInstance().getUser().placeCard(card);
		}
		else {
			AudioLoader.nopeSound.play();
		}
	}

	private void onMouseEnteredHandler() {
		if (GameLogic.getInstance().isGameEnd()) {
			return;
		} 
		if (GameLogic.getInstance().getCurrentPlayer() != GameLogic.getInstance().getUser()) {
			return;
		} 
		if (GameLogic.getInstance().getUser().isPlaced() == true) {
			return;
		}
		if (GameLogic.getInstance().isColorSelectionState()) {
			return;
		}
		if (GameLogic.getInstance().isChallengeState()) {
			return;
		}
		// handler
		if (this.card != GameLogic.getInstance().getCardOnTable()) {
			this.setStyle("-fx-cursor: hand;");
		}
	}

	private void onMouseExitedHandler() {
		this.setStyle("-fx-cursor: default;");
	}

	private void draw() {

		BackgroundFill bgFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(9), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

		Rectangle shape = new Rectangle(58, 86);
		shape.setArcWidth(9);
		shape.setArcHeight(9);

		if (card.getAction() == GameAction.NONE) {
			shape.setFill(card.getColor());
			this.getChildren().add(shape);

			Text cardText = new Text(Integer.toString(card.getNumber()));
			cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 50;");
			this.getChildren().add(cardText);
		}

		else {
			if (card.getAction() == GameAction.CHANGECOLOR) {
				shape.setFill(new ImagePattern(new Image(ImageLoader.colorCardURL)));
				this.getChildren().add(shape);

			} else if (card.getAction() == GameAction.CHALLENGE) {
				shape.setFill(new ImagePattern(new Image(ImageLoader.challengeCardURL)));
				this.getChildren().add(shape);

			} else if (card.getAction() == GameAction.STOP) {
				if (card.getColor() == ColorLoader.RED) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.stopRedCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.YELLOW) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.stopYellowCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.GREEN) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.stopGreenCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.BLUE) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.stopBlueCardURL)));
					this.getChildren().add(shape);
				}
			} else if (card.getAction() == GameAction.ROTATE) {
				if (card.getColor() == ColorLoader.RED) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.rotateRedCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.YELLOW) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.rotateYellowCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.GREEN) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.rotateGreenCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.BLUE) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.rotateBlueCardURL)));
					this.getChildren().add(shape);
				}
			} else if (card.getAction() == GameAction.PICK) {
				if (card.getColor() == ColorLoader.RED) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.pickRedCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.YELLOW) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.pickYellowCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.GREEN) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.pickGreenCardURL)));
					this.getChildren().add(shape);
				} else if (card.getColor() == ColorLoader.BLUE) {
					shape.setFill(new ImagePattern(new Image(ImageLoader.pickBlueCardURL)));
					this.getChildren().add(shape);
				}
			}

		}

	}

}
