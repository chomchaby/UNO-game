package gui;

import entity.card.ChallengeCard;
import entity.card.ColorCard;
import entity.card.DrawCard;
import entity.card.NormalCard;
import entity.card.ReverseCard;
import entity.card.SkipCard;
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
		if (GameLogic.getInstance().isRoundEnd()) {
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
		if (GameLogic.getInstance().getUser().isPlaced() || GameLogic.getInstance().isColorSelectionState()
				|| GameLogic.getInstance().isChallengeState()) {
			AudioLoader.nopeSound.play();
			return;
		}

		// handler for placing card
		if (GameLogic.getInstance().getUser().getPlaceableCardList().contains(card)) {
			GameLogic.getInstance().getUser().placeCard(card);
			AudioLoader.mouseClick1Sound.play();
		} else {
			AudioLoader.nopeSound.play();
		}
	}

	private void onMouseEnteredHandler() {
		if (GameLogic.getInstance().isRoundEnd()) {
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

	public void draw() {

		BackgroundFill bgFill = new BackgroundFill(Color.WHITE, new CornerRadii(this.getPrefWidth() * 0.125),
				Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

		Rectangle shape = new Rectangle(this.getPrefWidth() * 0.8, this.getPrefHeight() * 0.85);
		shape.setArcWidth(this.getPrefWidth() * 0.125);
		shape.setArcHeight(this.getPrefWidth() * 0.125);

		if (card instanceof NormalCard) {
			shape.setFill(card.getColor());
			this.getChildren().add(shape);

			Text cardText = new Text(Integer.toString(card.getNumber()));
			cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 50;");
			this.getChildren().add(cardText);
		}

		else {
			if (card instanceof ColorCard) {
				shape.setFill(new ImagePattern(new Image(ImageLoader.colorCardURL)));
				this.getChildren().add(shape);

			} else if (card instanceof ChallengeCard) {
				shape.setFill(new ImagePattern(new Image(ImageLoader.challengeCardURL)));
				this.getChildren().add(shape);

			} else if (card instanceof SkipCard) {
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
			} else if (card instanceof ReverseCard) {
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
			} else if (card instanceof DrawCard) {
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
