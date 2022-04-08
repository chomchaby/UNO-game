package gui;

import entity.card.UnitCard;
import entity.player.Player;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BotCardPane extends StackPane {

	protected UnitCard card;

	public BotCardPane(UnitCard card) {
		this.card = card;
		this.setPrefHeight(110);
		this.setPrefWidth(80);
		initializeCardColor();
		this.draw();

	}

	protected void initializeCardColor() {
		BackgroundFill bgFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

	}

	protected void draw() {
		Text cardText = new Text("Uno");
		cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 35; -fx-font-color: yellow");
		this.getChildren().add(cardText);

	}
}
