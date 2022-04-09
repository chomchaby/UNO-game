package main;

import gui.BotDeckPane;
import gui.TablePane;
import gui.UserDeckPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import logic.GameLogic;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameLogic.getInstance();

		UserDeckPane userDeckPane = new UserDeckPane(GameLogic.getInstance().getUser());

		BotDeckPane botDeckPane1 = new BotDeckPane(GameLogic.getInstance().getUser());
		botDeckPane1.getTransforms().add(new Rotate(90, Rotate.Z_AXIS));

		BotDeckPane botDeckPane2 = new BotDeckPane(GameLogic.getInstance().getUser());
		botDeckPane2.getTransforms().add(new Rotate(180, Rotate.Z_AXIS));

		BotDeckPane botDeckPane3 = new BotDeckPane(GameLogic.getInstance().getUser());
		botDeckPane3.getTransforms().add(new Rotate(270, Rotate.Z_AXIS));

		TablePane tablePane = new TablePane();

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(8, 8, 8, 8));
		root.setPrefWidth(1200);
		root.setPrefHeight(750);

		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color:white");
		root.setBorder(new Border(
				new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.add(botDeckPane1, 0, 0);
//		root.add(botDeckPane2,0,0);
//		root.add(botDeckPane3,0,0);
//		root.add(tablePane);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Unooo");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
