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
		if (GameLogic.getInstance().getCurrentPlayer() != GameLogic.getInstance().getUser()) {
			return;
		} 
		if (GameLogic.getInstance().getUser().isDrawn() == true) {
			return;
		}
		if (GameLogic.getInstance().isColorSelectionState()) {
			return;
		}
		if (GameLogic.getInstance().isChallengeState()) {
			return;
		}
		// handler
		if (GameLogic.getInstance().getUser().getDrawableCardList().contains(card)) {
			AudioLoader.mouseClick1Sound.play();
			GameLogic.getInstance().getUser().drawCard(card);
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
		if (GameLogic.getInstance().getUser().isDrawn() == true) {
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
