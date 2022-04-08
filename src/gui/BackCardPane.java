package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BackCardPane extends StackPane {

	public BackCardPane() {
		this.setPrefHeight(80);
		this.setPrefWidth(58);
		this.initializeCardColor();
		this.draw();

	}

	private void initializeCardColor() {
		BackgroundFill bgFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

	}

	private void draw() {
		Text cardText = new Text("Uno");
		cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 15; -fx-font-color: yellow");
		this.getChildren().add(cardText);

	}
}
