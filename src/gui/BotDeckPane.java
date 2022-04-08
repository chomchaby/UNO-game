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

public class BotDeckPane extends GridPane implements Updatable{
	private Player bot;
	
	public BotDeckPane(Player bot) {
		this.bot = bot;
		this.setHgap(5);
		this.setPrefWidth(500);
		this.setPrefHeight(120);
		this.setPadding(new Insets(10));
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
		int amount = bot.getCardList().size();
		for (int i = 0; i<Math.min(5, amount); i++) {
			BackCardPane backCardPane = new BackCardPane();
			this.add(backCardPane,i,0);
		}
		if (amount > 5) {
			Text cardLeftNumber = new Text("+"+Integer.toString(amount-5)+" cards");
			cardLeftNumber.setStyle("-fx-font-size:20;");
			this.add(cardLeftNumber,5,0);
		}
	}
	
	
	
}
