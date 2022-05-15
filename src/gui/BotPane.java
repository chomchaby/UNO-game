package gui;

import entity.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;

public class BotPane extends VBox implements Updatable {
	
	private Player bot;
	private GridPane cardPane;
	private Text turnText;

	private static final Border NORMAL_BORDER = new Border(
			new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(8)));
	private static final Border GREEN_BORDER = new Border(
			new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(8)));
	private static final Border RED_BORDER = new Border(
			new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(8)));

	public BotPane(Player bot) {
		this.bot = bot;
		
		// set up BotPane
		this.setMaxWidth(360);
		this.setPrefWidth(360);
		this.setMaxHeight(160);
		this.setPrefHeight(160);
		this.setAlignment(Pos.CENTER);

		// create and set up cardPane
		this.cardPane = new GridPane();
		this.cardPane.setHgap(4);
		this.cardPane.setMaxWidth(360);
		this.cardPane.setPrefWidth(360);
		this.cardPane.setMaxHeight(120);
		this.cardPane.setPrefHeight(120);
		this.cardPane.setPadding(new Insets(10));
		this.cardPane.setBorder(NORMAL_BORDER);
		this.cardPane.setAlignment(Pos.CENTER);

		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, new CornerRadii(8), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.cardPane.setBackground(new Background(bgFillA));
		
		// create and set up turnText
		this.turnText = new Text("Player: " + bot.getName());
		this.turnText.maxHeight(40);
		this.setAlignment(Pos.CENTER);
		this.setVisible(true);
		if (bot.getName().equals("Magaret")) {
			this.turnText.setRotate(180);
		}
		
		// add all field into BotPane
		this.getChildren().addAll(turnText,cardPane);
		
		// first time update to complete cardPane
		update();

	}

	@Override
	public void update() {
		// update border
		if (!(GameLogic.getInstance().getCurrentPlayer() == bot)) {
			this.cardPane.setBorder(NORMAL_BORDER);
			this.turnText.setVisible(true);
		} else if (bot.isPlayable()) {
			if (this.cardPane.getBorder() == NORMAL_BORDER) {
				this.cardPane.setBorder(GREEN_BORDER);
				this.turnText.setVisible(true);
			} else {
				this.cardPane.setBorder(NORMAL_BORDER);
				this.turnText.setVisible(false);
			}
		} else {
			if (this.cardPane.getBorder() == NORMAL_BORDER) {
				this.cardPane.setBorder(RED_BORDER);
				this.turnText.setVisible(true);
			} else {
				this.cardPane.setBorder(NORMAL_BORDER);
				this.turnText.setVisible(false);
			}
		}
		// update cards
		this.cardPane.getChildren().clear();
		int amount = bot.getCardList().size();
		if (amount <= 5) {
			for (int i = 0; i < Math.min(5, amount); i++) {
				BackCardPane backCardPane = new BackCardPane();
				this.cardPane.add(backCardPane, i, 0);
			}
		}

		else {
			for (int i = 0; i < Math.min(4, amount); i++) {
				BackCardPane backCardPane = new BackCardPane();
				this.cardPane.add(backCardPane, i, 0);
			}
			Text cardLeftNumber = new Text(" +" + Integer.toString(amount - 4) + " cards");
			cardLeftNumber.setStyle("-fx-font-size:18;");
			this.cardPane.add(cardLeftNumber, 4, 0);
		}
	}

}
