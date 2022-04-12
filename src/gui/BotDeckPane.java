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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;

public class BotDeckPane extends GridPane implements Updatable {
	private Player bot;

	private static final Border NORMAL_BORDER = new Border(
			new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(7)));
	private static final Border GREEN_BORDER = new Border(
			new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(7)));
	private static final Border RED_BORDER = new Border(
			new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(7)));

	public BotDeckPane(Player bot) {
		this.bot = bot;
		this.setHgap(4);
		this.setMaxWidth(360);
		this.setPrefWidth(360);
		this.setMaxHeight(120);
		this.setPrefHeight(120);
		this.setPadding(new Insets(10));
		this.setBorder(NORMAL_BORDER);
		this.setAlignment(Pos.CENTER);

		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, new CornerRadii(8), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		update();

	}

	@Override
	public void update() {
		// update border
		if (!(GameLogic.getInstance().getCurrentPlayer() == bot)) {
			this.setBorder(NORMAL_BORDER);
		} else if (bot.isPlayable()) {
			if (this.getBorder() == NORMAL_BORDER) {
				this.setBorder(GREEN_BORDER);
			} else {
				this.setBorder(NORMAL_BORDER);
			}
		} else {
			if (this.getBorder() == NORMAL_BORDER) {
				this.setBorder(RED_BORDER);
			} else {
				this.setBorder(NORMAL_BORDER);
			}
		}
		// update cards
		this.getChildren().clear();
		int amount = bot.getCardList().size();
		for (int i = 0; i < Math.min(4, amount); i++) {
			BackCardPane backCardPane = new BackCardPane();
			this.add(backCardPane, i, 0);
		}
		if (amount > 4) {
			Text cardLeftNumber = new Text(" +" + Integer.toString(amount - 4) + " cards");
			if (amount - 4 == 1)
				cardLeftNumber.setText(" +1card");
			cardLeftNumber.setStyle("-fx-font-size:18;");
			this.add(cardLeftNumber, 4, 0);
		}
	}

}
