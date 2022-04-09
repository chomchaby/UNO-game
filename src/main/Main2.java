package main;

import gui.BotDeckPane;
import gui.TablePane;
import gui.UserDeckPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import logic.GameLogic;

public class Main2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		root.setPrefHeight(750);
		root.setPrefWidth(1250);
		GameLogic.getInstance();
		UserDeckPane userDeckPane = new UserDeckPane(GameLogic.getInstance().getUser());
		BotDeckPane botDeckPane = new BotDeckPane(GameLogic.getInstance().getUser());

		BotDeckPane botDeckPane1 = new BotDeckPane(GameLogic.getInstance().getUser());
		Rotate rotationLeft = new Rotate(90, Rotate.Z_AXIS);
		rotationLeft.setPivotX(195);
		rotationLeft.setPivotY(50);
		botDeckPane1.getTransforms().add(rotationLeft);

		BotDeckPane botDeckPane2 = new BotDeckPane(GameLogic.getInstance().getUser());
		Rotate rotationTop = new Rotate(180, Rotate.Z_AXIS);
		rotationTop.setPivotX(180);
		rotationTop.setPivotY(60);
		botDeckPane2.getTransforms().add(rotationTop);

		BotDeckPane botDeckPane3 = new BotDeckPane(GameLogic.getInstance().getUser());
		Rotate rotationRight = new Rotate(270, Rotate.Z_AXIS);
		rotationRight.setPivotX(165);
		rotationRight.setPivotY(50);
		botDeckPane3.getTransforms().add(rotationRight);

		TablePane tablePane = new TablePane();

		root.setCenter(tablePane);
		BorderPane.setAlignment(tablePane, Pos.CENTER);
		root.setBottom(userDeckPane);
		BorderPane.setAlignment(userDeckPane, Pos.CENTER);
		root.setLeft(botDeckPane1);
		BorderPane.setAlignment(botDeckPane1, Pos.CENTER);
		root.setTop(botDeckPane2);
		BorderPane.setAlignment(botDeckPane2, Pos.CENTER);
		root.setRight(botDeckPane3);
		BorderPane.setAlignment(botDeckPane3, Pos.CENTER);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Unooo");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
