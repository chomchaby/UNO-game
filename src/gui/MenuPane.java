package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logic.GameLogic;
import main.Main;
import sharedObject.AudioLoader;

public class MenuPane extends VBox {

	private Button scoreBtn;
	private Button homeBtn;
	private Button quitBtn;

	public MenuPane() {
		// set up MenuPane //
		this.setMaxWidth(150);
		this.setPrefWidth(150);
		this.setMaxHeight(200);
		this.setPrefHeight(200);
		this.setSpacing(20);
		// bottom padding
		this.setPadding(new Insets(0, 0, 20, 0));
		this.setAlignment(Pos.BOTTOM_RIGHT);

		// set up Buttons //
		// scoreBtn
		scoreBtn = new Button("SCORE");
		scoreBtn.setStyle("-fx-font-size:15; -fx-font-weight: bold;");
		scoreBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				toggleScore();
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
		homeBtn.setStyle("-fx-font-size:15; -fx-font-weight: bold;");
		homeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				returnToHomeScene();
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
		quitBtn.setStyle("-fx-font-size:15; -fx-font-weight: bold;");
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

		// add all buttons to MenuPane
		this.getChildren().add(scoreBtn);
		this.getChildren().add(homeBtn);
		this.getChildren().add(quitBtn);

	}

	private void toggleScore() {
		AudioLoader.popSound.play();
		// set visibility at any time except at the end of the round or the game.
		if (!GameLogic.getInstance().isRoundEnd())
			GameLogic.getInstance().setScoreShown(!GameLogic.getInstance().isScoreShown());
	}

	private void returnToHomeScene() {
		AudioLoader.popSound.play();
		Main.getRidOfGameRun();
		Main.initializeHomeScene();
	}

	private void quitGame() {
		Platform.exit();
		System.exit(0); 
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
		button.setPrefWidth(150);
		button.setPrefHeight(40);
		button.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-cursor: hand;");
	}

	private void resetButtonHandler(Button button) {
		button.setPrefWidth(80);
		button.setPrefHeight(40);
		button.setStyle("-fx-font-size:15; -fx-font-weight: bold;");
	}

}
