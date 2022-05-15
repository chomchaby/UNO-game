package gui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.ImageLoader;

public class BackCardPane extends StackPane {

	public BackCardPane() {
		this.setPrefHeight(80);
		this.setPrefWidth(58);
		this.draw();

	}

	public void draw() {
		
		BackgroundFill bgFill = new BackgroundFill(Color.WHITE, new CornerRadii(this.getPrefWidth()*0.125), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

//		Text cardText = new Text("Uno");
//		cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 15; -fx-font-color: yellow");
//		this.getChildren().add(cardText);
		
		Rectangle shape = new Rectangle(this.getPrefWidth()*0.8,this.getPrefHeight()*0.85);
		shape.setArcWidth(this.getPrefWidth()*0.125);
		shape.setArcHeight(this.getPrefWidth()*0.125);
		shape.setFill(Color.BLACK);
		this.getChildren().add(shape);

		ImageView imageView = new ImageView( new Image(ImageLoader.logoURL));
		imageView.setFitWidth(this.getPrefWidth()*0.75);
		imageView.setPreserveRatio(true);
		this.getChildren().add(imageView);


	}
}
