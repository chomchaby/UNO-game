package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sharedObject.AudioLoader;
import sharedObject.ColorLoader;

public class MenuPane extends VBox{
	
	private Button scoreBtn;
	private Button homeBtn;
	private Button quitBtn;
	
	public MenuPane() {
		// set up MenuPane
		this.setMaxWidth(120);
		this.setPrefWidth(120);
		this.setMaxHeight(200);
		this.setPrefHeight(200);
		this.setSpacing(20);
		// bottom padding
		this.setPadding(new Insets(0,0,20,0));
		this.setAlignment(Pos.BOTTOM_RIGHT);
		
		// set up Buttons
		// scoreBtn
		scoreBtn = new Button("SCORE");
		scoreBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showScore();
			}
		});
		scoreBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				enterScoreButtonHandler();
			}
		});
		scoreBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resetScoreButtonHandler();
			}
		});
		// homeBtn
		homeBtn = new Button("HOME");
		homeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				returnToStartScene();
			}
		});
		homeBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				enterHomeButtonHandler();
			}
		});
		homeBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resetHomeButtonHandler();
			}
		});
		
		// quitBtn
		quitBtn = new Button("QUIT");
		quitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				quitGame();
			}
		});
		quitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				enterQuitButtonHandler();
			}
		});
		quitBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resetQuitButtonHandler();
			}
		});
		
		// set size buttons
		resetButtonHandler(scoreBtn);
		resetButtonHandler(homeBtn);
		resetButtonHandler(quitBtn);
		
	}
	
	
	private void showScore() {
		AudioLoader.mouseClick1Sound.play();

	}
	private void returnToStartScene() {
		AudioLoader.mouseClick1Sound.play();
	}
	private void quitGame() {
		Platform.exit();
	}
	private void enterScoreButtonHandler() {
		enterButtonHandler(scoreBtn);
	}
	private void enterHomeButtonHandler() {
		enterButtonHandler(homeBtn);
	}
	private void enterQuitButtonHandler() {
		enterButtonHandler(quitBtn);
	}
	private void resetScoreButtonHandler() {
		resetButtonHandler(scoreBtn);
	}
	private void resetHomeButtonHandler() {
		resetButtonHandler(homeBtn);
	}
	private void resetQuitButtonHandler() {
		resetButtonHandler(quitBtn);
	}
	private void enterButtonHandler(Button button) {
		AudioLoader.mouseEnterSound.play();
		button.setPrefWidth(120);
		button.setPrefHeight(30);
		button.setStyle("-fx-cursor: hand;");
	}
	private void resetButtonHandler(Button button) {
		button.setPrefWidth(80);
		button.setPrefHeight(30);
	}
	
}
