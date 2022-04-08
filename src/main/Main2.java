package main;

import gui.BotDeckPane;
import gui.TablePane;
import gui.UserDeckPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.GameLogic;

public class Main2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(10));
		root.setPrefHeight(750);
		root.setPrefWidth(1200);
		root.setAlignment(Pos.CENTER);
		GameLogic.getInstance();
		UserDeckPane userDeckPane = new UserDeckPane(GameLogic.getInstance().getUser());
		BotDeckPane botDeckPane = new BotDeckPane(GameLogic.getInstance().getUser());
		TablePane tablePane = new TablePane();
//		root.add(userDeckPane,0,0);
//		root.add(botDeckPane,0,0);
		root.add(tablePane, 0, 0);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Unooo");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
