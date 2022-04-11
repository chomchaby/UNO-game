package gui;

import entity.card.UnitCard;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameLogic;

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

	}

	private void onClickHandler() {
		if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getUser()) {
			if (card != GameLogic.getInstance().getCardOnTable()) {
				GameLogic.getInstance().getUser().drawCard(card);
			} else {
				GameLogic.getInstance().getUser().pick(1);
			}
		}
	}

	private void draw() {

		BackgroundFill bgFill = new BackgroundFill(Color.BLACK, new CornerRadii(9), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

		Rectangle shape = new Rectangle(70, 100, card.getColor());
		shape.setArcWidth(8);
		shape.setArcHeight(8);
		this.getChildren().add(shape);

		Text cardText = new Text(Integer.toString(card.getNumber()));
		cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 50; -fx-font-color: yellow");
		this.getChildren().add(cardText);

	}

// to checkk
	public UnitCard getCard() {
		return card;
	}
}
