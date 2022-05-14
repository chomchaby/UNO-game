package screen;

import java.util.ArrayList;

import gui.BotDeckPane;
import gui.StatusPane;
import gui.Updatable;
import gui.UserDeckPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import logic.GameLogic;

public class GamePlayScene extends BorderPane{

	private ArrayList<Updatable> updatableItems;
	
	public GamePlayScene() {
		
		this.setPadding(new Insets(10));
		this.setPrefHeight(750);
		this.setPrefWidth(1250);

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
		this.setCenter(statusPane);
		BorderPane.setAlignment(statusPane, Pos.CENTER);
		this.setBottom(userPane);
		BorderPane.setAlignment(userPane, Pos.CENTER);
		this.setLeft(botJesicaPane);
		BorderPane.setAlignment(botJesicaPane, Pos.CENTER);
		this.setTop(botMagaretPane);
		BorderPane.setAlignment(botMagaretPane, Pos.CENTER);
		this.setRight(botVandaPane);
		BorderPane.setAlignment(botVandaPane, Pos.CENTER);

		// set up updatableItems
		updatableItems = new ArrayList<Updatable>();
		updatableItems.add(userPane);
		updatableItems.add(botJesicaPane);
		updatableItems.add(botMagaretPane);
		updatableItems.add(botVandaPane);
		updatableItems.add(statusPane);

	}

	public ArrayList<Updatable> getUpdatableItems() {
		return updatableItems;
	}
	
}
