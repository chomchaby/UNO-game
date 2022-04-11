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

public class ColorSelection extends HBox {

	public ColorSelection() {
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		Text text = new Text("Select Color : ");
//		text.setStyle("-fx-font-size:30;");

		Button redBtn = new Button();
		redBtn.setPrefWidth(25);
		redBtn.setPrefHeight(25);
		BackgroundFill bgFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillRedA = { bgFill };
		redBtn.setBackground(new Background(bgFillRedA));

		Button yellowBtn = new Button();
		yellowBtn.setPrefWidth(25);
		yellowBtn.setPrefHeight(25);
		BackgroundFill[] bgFillYellowA = { new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY) };
		yellowBtn.setBackground(new Background(bgFillYellowA));

		Button blueBtn = new Button();
		blueBtn.setPrefWidth(25);
		blueBtn.setPrefHeight(25);
		BackgroundFill[] bgFillBlueA = { new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY) };
		blueBtn.setBackground(new Background(bgFillBlueA));

		Button greenBtn = new Button();
		greenBtn.setPrefWidth(25);
		greenBtn.setPrefHeight(25);
		BackgroundFill[] bgFillGreenA = { new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY) };
		greenBtn.setBackground(new Background(bgFillGreenA));

		this.getChildren().addAll(text, redBtn, yellowBtn, blueBtn, greenBtn);

		redBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GameLogic.getInstance().setColorState(Color.RED);
				System.out.println("REDDD");
				GameLogic.getInstance().setColorSelectionState(false);
			}
		});

		redBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
				GameLogic.getInstance().setColorState(Color.YELLOW);
				GameLogic.getInstance().setColorSelectionState(false);
			}
		});
		yellowBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
				GameLogic.getInstance().setColorState(Color.BLUE);
				GameLogic.getInstance().setColorSelectionState(false);
			}
		});
		blueBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
				GameLogic.getInstance().setColorState(Color.GREEN);
				GameLogic.getInstance().setColorSelectionState(false);
			}
		});
		greenBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
}