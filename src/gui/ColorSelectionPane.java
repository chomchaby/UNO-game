package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObject.AudioLoader;
import sharedObject.ColorLoader;

public class ColorSelectionPane extends HBox {
	
	private Text text;
	private Button redBtn;
	private Button yellowBtn;
	private Button greenBtn;
	private Button blueBtn;
	// This is UI for User only
	
	public ColorSelectionPane() {
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		this.setMaxHeight(50);
		
		text = new Text("Select Color : ");
		text.setStyle("-fx-font-size:18");

		redBtn = new Button();
		redBtn.setPrefWidth(25);
		redBtn.setPrefHeight(25);
		BackgroundFill bgFill = new BackgroundFill(ColorLoader.RED, new CornerRadii(12), Insets.EMPTY);
		BackgroundFill[] bgFillRedA = { bgFill };
		redBtn.setBackground(new Background(bgFillRedA));

		yellowBtn = new Button();
		yellowBtn.setPrefWidth(25);
		yellowBtn.setPrefHeight(25);
		BackgroundFill[] bgFillYellowA = { new BackgroundFill(ColorLoader.YELLOW, new CornerRadii(12), Insets.EMPTY) };
		yellowBtn.setBackground(new Background(bgFillYellowA));

		blueBtn = new Button();
		blueBtn.setPrefWidth(25);
		blueBtn.setPrefHeight(25);
		BackgroundFill[] bgFillBlueA = { new BackgroundFill(ColorLoader.BLUE, new CornerRadii(12), Insets.EMPTY) };
		blueBtn.setBackground(new Background(bgFillBlueA));

		greenBtn = new Button();
		greenBtn.setPrefWidth(25);
		greenBtn.setPrefHeight(25);
		BackgroundFill[] bgFillGreenA = { new BackgroundFill(ColorLoader.GREEN, new CornerRadii(12), Insets.EMPTY) };
		greenBtn.setBackground(new Background(bgFillGreenA));

		this.getChildren().addAll(text, redBtn, yellowBtn, blueBtn, greenBtn);

		redBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AudioLoader.buttonClickSound.play();
				selectColor(ColorLoader.RED);
			}
		});

		redBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.popSound.play();
				redBtn.setStyle("-fx-cursor: hand;");
				redBtn.setPrefWidth(40);
				redBtn.setPrefHeight(40);
			}
		});
		redBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				redBtn.setPrefWidth(25);
				redBtn.setPrefHeight(25);
			}
		});
		
		yellowBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AudioLoader.buttonClickSound.play();
				selectColor(ColorLoader.YELLOW);
			}
		});
		yellowBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.popSound.play();
				yellowBtn.setStyle("-fx-cursor: hand;");
				yellowBtn.setPrefWidth(40);
				yellowBtn.setPrefHeight(40);
			}
		});
		yellowBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				yellowBtn.setPrefWidth(25);
				yellowBtn.setPrefHeight(25);
			}
		});
		
		blueBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AudioLoader.buttonClickSound.play();
				selectColor(ColorLoader.BLUE);
			}
		});
		blueBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.popSound.play();
				blueBtn.setStyle("-fx-cursor: hand;");
				blueBtn.setPrefWidth(40);
				blueBtn.setPrefHeight(40);
			}
		});

		blueBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				blueBtn.setPrefWidth(25);
				blueBtn.setPrefHeight(25);
			}
		});

		greenBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AudioLoader.buttonClickSound.play();
				selectColor(ColorLoader.GREEN);
			}
		});
		greenBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.popSound.play();
				greenBtn.setStyle("-fx-cursor: hand;");
				greenBtn.setPrefWidth(40);
				greenBtn.setPrefHeight(40);
			}
		});
		greenBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				greenBtn.setPrefWidth(25);
				greenBtn.setPrefHeight(25);
			}
		});
		
	}
	
	private void selectColor(Color color) {

		// for challenge 
		if (GameLogic.getInstance().isChallengeState()) {
			// collect data
			GameLogic.getInstance().setChallengeColor(color);
			GameLogic.getInstance().setColorState(color);
			// to close ColorSection Pane, and create result text
			GameLogic.getInstance().setColorSelectionState(false);
			// challenging...
			GameLogic.getInstance().getUser().challenge();

		}
		// for color change
		else {
			// collect data
			GameLogic.getInstance().setColorState(color);
			// the action of color card ends.
			GameLogic.getInstance().getUser().setPlaced(true);
			// to close ColorSection Pane
			GameLogic.getInstance().setColorSelectionState(false);
		}
		
	}
}
