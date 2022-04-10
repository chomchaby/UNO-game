package gui;

import entity.card.UnitCard;
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
import logic.GameLogic;

public class UserDeckPane extends GridPane implements Updatable {
	
	private Player owner;
	
	private static final Border NORMAL_BORDER = new Border(
			new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	private static final Border GREEN_BORDER = new Border(
			new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(9)));
	private static final Border RED_BORDER = new Border(
			new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(9)));

	public UserDeckPane(Player owner) {
		this.owner = owner;
		this.setHgap(6);
		this.setVgap(8);
		this.setMaxWidth(900);
		this.setPrefHeight(270);
		this.setPadding(new Insets(10));
		this.setBorder(NORMAL_BORDER);
		this.setAlignment(Pos.CENTER);

		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		update();

	}

	@Override
	public void update() {
		
		// update border
		if (!(GameLogic.getInstance().getCurrentPlayer() == owner)) {
			this.setBorder(NORMAL_BORDER);
		}
		else if (GameLogic.getInstance().isPlayable()){
			if (this.getBorder() == NORMAL_BORDER) {
				this.setBorder(GREEN_BORDER);
			}
			else {
				this.setBorder(NORMAL_BORDER);
			}
		}
		else {
			if (this.getBorder() == NORMAL_BORDER) {
				this.setBorder(RED_BORDER);
			}
			else {
				this.setBorder(NORMAL_BORDER);
			}
		}
		
		//update cards
		this.getChildren().clear();
		int amount = owner.getCardList().size();
		int column = amount / 9;
		int ind = 0;
		for (int col = 0; col <= column; col++) {
			for (int row = 0; row < Math.min(amount, 9); row++) {
				if (ind == amount)
					break;
				UnitCard cardToAdd = owner.getCardList().get(ind);
				this.add(new FontCardPane(cardToAdd), row, col);
				ind++;
			}
		}
	}

}
