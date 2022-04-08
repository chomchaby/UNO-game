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

public class UserDeckPane extends GridPane implements Updatable {
	private Player owner;

	public UserDeckPane(Player owner) {
		this.owner = owner;
		this.setHgap(10);
		this.setVgap(10);
		this.setPrefWidth(900);
		this.setPrefHeight(300);
		this.setPadding(new Insets(20));
		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setAlignment(Pos.CENTER);

		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		updateCardInPane();

	}
	@Override
	public void updateCardInPane() {
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
