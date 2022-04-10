package main;

import java.util.Scanner;

import gui.BotDeckPane;
import gui.StatusPane;
import gui.Updatable;
import gui.UserDeckPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.UpdatableHolder;

public class Main2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// ask name
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter your name: ");
		String userName = kb.nextLine();
		System.out.println("Username is: " + userName);

		// create game
		GameLogic.getInstance().setUserName(userName);

		// set root pane
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		root.setPrefHeight(750);
		root.setPrefWidth(1250);

		// set other player pane & status pane
		UserDeckPane userPane = new UserDeckPane(GameLogic.getInstance().getUser());
		BotDeckPane botJesicaPane = new BotDeckPane(GameLogic.getInstance().getBotJesica());
		Rotate rotation90 = new Rotate(90, Rotate.Z_AXIS);
		rotation90.setPivotX(195);
		rotation90.setPivotY(50);
		botJesicaPane.getTransforms().add(rotation90);

		BotDeckPane botMagaretPane = new BotDeckPane(GameLogic.getInstance().getBotMagaret());
		Rotate rotation180 = new Rotate(180, Rotate.Z_AXIS);
		rotation180.setPivotX(180);
		rotation180.setPivotY(60);
		botMagaretPane.getTransforms().add(rotation180);

		BotDeckPane botVandaPane = new BotDeckPane(GameLogic.getInstance().getBotVanda());
		Rotate rotation270 = new Rotate(270, Rotate.Z_AXIS);
		rotation270.setPivotX(165);
		rotation270.setPivotY(50);
		botVandaPane.getTransforms().add(rotation270);

		StatusPane statusPane = new StatusPane();

		// set Pane in root
		root.setCenter(statusPane);
		BorderPane.setAlignment(statusPane, Pos.CENTER);
		root.setBottom(userPane);
		BorderPane.setAlignment(userPane, Pos.CENTER);
		root.setLeft(botJesicaPane);
		BorderPane.setAlignment(botJesicaPane, Pos.CENTER);
		root.setTop(botMagaretPane);
		BorderPane.setAlignment(botMagaretPane, Pos.CENTER);
		root.setRight(botVandaPane);
		BorderPane.setAlignment(botVandaPane, Pos.CENTER);

		// create UpdatableHolder
		//userPane, botJesicaPane, botMagaretPane, botVandaPane, statusPane 
//		Updatable[] updatableArray = {botJesicaPane, botMagaretPane, botVandaPane};
//		UpdatableHolder.createInstance(updatableArray);

		// set scene
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("I just wanna pen fan you dai bor?");
		primaryStage.show();
		
//		AnimationTimer animation = new AnimationTimer() {
//			public void handle(long now) {
//				botJesicaPane.update();
//				statusPane.update();
//			}
//		};
//		animation.start();
		
//		Thread showTurn = new Thread(() -> {
//			try {
//				while (true) {
//					Thread.sleep(2000);
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							UpdatableHolder.getInstance().updateScreen();
//						}
//					});
//				}
//	
//			}
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		});
//		showTurn.start();
//		Thread.sleep(20000);
//		showTurn.interrupt();
		
//		Thread.sleep(1000);
//		UpdatableHolder.getInstance().updateScreen();
//		GameLogic.getInstance().getBotVanda().pick(1);
//		Thread.sleep(1000);
//		UpdatableHolder.getInstance().updateScreen();
//		GameLogic.getInstance().getBotVanda().pick(1);
//		Thread.sleep(1000);
//		UpdatableHolder.getInstance().updateScreen();



	}

	public static void main(String[] args) {
		launch(args);
	}

}
