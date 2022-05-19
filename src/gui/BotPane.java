package gui;

import entity.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
import sharedObject.ImageLoader;

public class BotPane extends VBox implements Updatable {
	
	private Player bot;
	private GridPane cardPane;
	private Text nameText;

	private static final Border NORMAL_BORDER = new Border(
			new BorderStroke(Color.TAN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8)));
	private static final Border GREEN_BORDER = new Border(
			new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8)));
	private static final Border RED_BORDER = new Border(
			new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8)));

	public BotPane(Player bot) {
		this.bot = bot;
		
		// set up BotPane
		this.setMaxWidth(360);
		this.setPrefWidth(360);
		this.setMaxHeight(150);
		this.setPrefHeight(150);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);

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

//		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, new CornerRadii(8), Insets.EMPTY);
//		BackgroundFill[] bgFillA = { bgFill };
//		this.cardPane.setBackground(new Background(bgFillA));
		BackgroundImage bImg = new BackgroundImage(ImageLoader.woodTableImg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background bGround = new Background(bImg);
		this.cardPane.setBackground(bGround);
		
		// create and set up nameText
		this.nameText = new Text("Player : " + bot.getName());
		this.nameText.setStyle("-fx-font-size: 18;");
		this.nameText.maxHeight(25);
		this.nameText.setVisible(true);
		if (bot.getName().equals("Magaret")) {
			this.nameText.setRotate(180);
		}
		
		// add all field into BotPane
		this.getChildren().addAll(nameText,cardPane);
		
		// first time update to complete cardPane
		update();

	}

	@Override
	public void update() {
		// update border
		if (!(GameLogic.getInstance().getCurrentPlayer() == bot)) {
			this.cardPane.setBorder(NORMAL_BORDER);
			this.nameText.setStyle("-fx-font-size: 18;");
			this.nameText.setVisible(true);
		} else if (bot.isPlayable()) {
			if (this.cardPane.getBorder() == NORMAL_BORDER) {
				this.cardPane.setBorder(GREEN_BORDER);
				this.nameText.setStyle("-fx-font-size: 20; -fx-fill: green; -fx-font-weight: bold;");
				this.nameText.setVisible(true);
			} else {
				this.cardPane.setBorder(NORMAL_BORDER);
				this.nameText.setVisible(false);
			}
		} else {
			if (this.cardPane.getBorder() == NORMAL_BORDER) {
				this.cardPane.setBorder(RED_BORDER);
				this.nameText.setStyle("-fx-font-size: 20; -fx-fill: red; -fx-font-weight: bold;");
				this.nameText.setVisible(true);
			} else {
				this.cardPane.setBorder(NORMAL_BORDER);
				this.nameText.setVisible(false);
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
