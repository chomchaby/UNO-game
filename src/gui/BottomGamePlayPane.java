package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sharedObject.AudioLoader;
import sharedObject.ImageLoader;

public class BottomGamePlayPane extends StackPane{
	
	UserPane userPane;
	MenuPane menuPane;
	Button soundBtn;
	
	public BottomGamePlayPane(UserPane userPane) {
		// set up BottomGamePlayPane
		this.setMaxWidth(1200);
		this.setPrefWidth(1200);
		this.setMaxHeight(300);
		this.setPrefHeight(300);
		
		// create all fields
		// userPane
		this.userPane = userPane;
		// menuPane
		menuPane = new MenuPane();
		// sound setting button
		soundBtn = new Button("",ImageLoader.soundOffImg);
		soundBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// to stop background sound
				if (AudioLoader.gamePlayBGSound.isPlaying()) {
			        soundBtn.setGraphic(ImageLoader.soundOnImg);
			        AudioLoader.gamePlayBGSound.stop();
				}
				// to play background sound
				else {
					soundBtn.setGraphic(ImageLoader.soundOffImg);
			        AudioLoader.gamePlayBGSound.play();
				}
			}
		});
		soundBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				soundBtn.setStyle("-fx-cursor: hand;");
			}
		});
		
		// put all fields in BottomGamePlayPane
		this.getChildren().add(userPane);
		StackPane.setAlignment(userPane, Pos.CENTER);
		this.getChildren().add(menuPane);
		StackPane.setAlignment(menuPane, Pos.BOTTOM_RIGHT);
		this.getChildren().add(soundBtn);
		StackPane.setAlignment(soundBtn, Pos.BOTTOM_LEFT);
		soundBtn.setTranslateY(-15);
	}
	
	
}
