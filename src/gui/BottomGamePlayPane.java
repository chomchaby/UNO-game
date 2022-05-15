package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

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
		this.userPane = userPane;
		menuPane = new MenuPane();
		soundBtn = new Button("Sound Off");
		soundBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setSound();
			}
		});
		
		// put all fields in BottomGamePlayPane
		this.getChildren().add(userPane);
		StackPane.setAlignment(userPane, Pos.CENTER);
		this.getChildren().add(menuPane);
		StackPane.setAlignment(menuPane, Pos.BOTTOM_RIGHT);
		this.getChildren().add(soundBtn);
		StackPane.setAlignment(soundBtn, Pos.BOTTOM_RIGHT);
		soundBtn.setTranslateY(-35);
	}
	
	private void setSound() {
		
	}
	
}
