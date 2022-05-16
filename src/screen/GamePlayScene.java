package screen;

import java.util.ArrayList;

import gui.BotPane;
import gui.BottomGamePlayPane;
import gui.CenterPane;
import gui.Updatable;
import gui.UserPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import logic.GameLogic;

public class GamePlayScene extends BorderPane{

	private ArrayList<Updatable> updatableItems;
	
	public GamePlayScene() {
		
		this.setPadding(new Insets(10));
		this.setPrefHeight(750);
		this.setPrefWidth(1250);
		
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
		
//		Rotate rotation90 = new Rotate(90, Rotate.Z_AXIS);
//		rotation90.setPivotX(195);
//		rotation90.setPivotY(50);
//		botJesicaPane.getTransforms().add(rotation90);		
		
//		Rotate rotation180 = new Rotate(180, Rotate.Z_AXIS);
//		rotation180.setPivotX(180);
//		rotation180.setPivotY(60);
//		botMagaretPane.getTransforms().add(rotation180);

//		Rotate rotation270 = new Rotate(270, Rotate.Z_AXIS);
//		rotation270.setPivotX(165);
//		rotation270.setPivotY(50);
//		botVandaPane.getTransforms().add(rotation270);

		// set up userPane 
		UserPane userPane = new UserPane(GameLogic.getInstance().getUser());

		// set up bottomGamePlayPane
		BottomGamePlayPane bottomGamePlayPane = new BottomGamePlayPane(userPane);
		this.setBottom(bottomGamePlayPane );
		BorderPane.setAlignment(bottomGamePlayPane , Pos.CENTER);
		
		
		
		CenterPane centerPane = new CenterPane();
		
		
		// set Pane in root
		this.setCenter(centerPane);
		BorderPane.setAlignment(centerPane, Pos.CENTER);

		

		// set up updatableItems
		updatableItems = new ArrayList<Updatable>();
		updatableItems.add(userPane);
		updatableItems.add(botJesicaPane);
		updatableItems.add(botMagaretPane);
		updatableItems.add(botVandaPane);
		updatableItems.add(centerPane);

	}

	public ArrayList<Updatable> getUpdatableItems() {
		return updatableItems;
	}
	
}
