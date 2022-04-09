package main;

import java.util.Scanner;

import gui.BotDeckPane;
import gui.StatusPane;
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
		// ask name
	    Scanner myObj = new Scanner(System.in); 
	    System.out.println("Enter your name: ");
	    String userName = myObj.nextLine();  
	    System.out.println("Username is: " + userName); 
	    // create game
		GameLogic.getInstance().setUserName(userName);
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		root.setPrefHeight(750);
		root.setPrefWidth(1250);

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

		GameLogic.getInstance().setCurrentPlayer(GameLogic.getInstance().getUser());
		StatusPane statusPane = new StatusPane();
		TablePane tablePane = new TablePane();
		statusPane.getChildren().add(tablePane);
		
		root.setCenter(statusPane);
		BorderPane.setAlignment(statusPane, Pos.CENTER);
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
