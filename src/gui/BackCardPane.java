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
		this.draw();

	}

	private void draw() {
		
		BackgroundFill bgFill = new BackgroundFill(Color.RED, new CornerRadii(9), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		
		Text cardText = new Text("Uno");
		cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 15; -fx-font-color: yellow");
		this.getChildren().add(cardText);

	}
}
