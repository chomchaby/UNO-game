package screen;

import java.util.ArrayList;

import gui.BotPane;
import gui.BottomGamePlayPane;
import gui.CenterPane;
import gui.UserPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.Updatable;
import logic.UpdatableHolder;

public class GamePlayScene extends BorderPane{
	
	public GamePlayScene() {
		
		this.setPadding(new Insets(10));
		this.setPrefHeight(750);
		this.setPrefWidth(1250);
		BackgroundFill bgFill = new BackgroundFill(Color.CORNSILK,CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		
		// create bot pane
		BotPane botJesicaPane = new BotPane(GameLogic.getInstance().getBotJesica());
		BotPane botMagaretPane = new BotPane(GameLogic.getInstance().getBotMagaret());
		BotPane botVandaPane = new BotPane(GameLogic.getInstance().getBotVanda());

		// put bot pane into gamePlayScene
		this.setLeft(botJesicaPane);
		BorderPane.setAlignment(botJesicaPane, Pos.CENTER);
		this.setTop(botMagaretPane);
		BorderPane.setAlignment(botMagaretPane, Pos.CENTER);
		this.setRight(botVandaPane);
		BorderPane.setAlignment(botVandaPane, Pos.CENTER);
		
		// set rotation
		botJesicaPane.setRotate(90);
		botMagaretPane.setRotate(180);
		botVandaPane.setRotate(270);
		
		// set translation
		botJesicaPane.setTranslateX(-45);
		botVandaPane.setTranslateX(45);

		// set up userPane 
		UserPane userPane = new UserPane(GameLogic.getInstance().getUser());

		// set up bottomGamePlayPane
		BottomGamePlayPane bottomGamePlayPane = new BottomGamePlayPane(userPane);
		this.setBottom(bottomGamePlayPane );
		BorderPane.setAlignment(bottomGamePlayPane , Pos.CENTER);
		
		// set up centerPane
		CenterPane centerPane = new CenterPane();
		this.setCenter(centerPane);
		BorderPane.setAlignment(centerPane, Pos.CENTER);


		// set up UpdatableHolder
		ArrayList<Updatable> updatableItems = new ArrayList<Updatable>();
		updatableItems.add(userPane);
		updatableItems.add(botJesicaPane);
		updatableItems.add(botMagaretPane);
		updatableItems.add(botVandaPane);
		updatableItems.add(centerPane);
		
		UpdatableHolder.getInstance().setEntities(updatableItems);

	}
	
}
