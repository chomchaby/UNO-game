package entity.card;

import entity.player.Bot;
import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class ColorCard extends EffectCard {

	public ColorCard() {
		super(14, Color.BLACK);
		this.gameAction = GameAction.CHANGECOLOR;
	}

	@Override
	public boolean isPlaceable() {
		return true;
	}

	@Override
	public void takeAction() {
		// for Bot, just change colorReg in status pane
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			GameLogic.getInstance().shortProcessing();
			Bot bot = (Bot) GameLogic.getInstance().getCurrentPlayer();
			GameLogic.getInstance().setColorState(bot.chooseColor());
		} 
		// for User, create ColorSelection pane (interface) in status pane
		else {
			GameLogic.getInstance().setColorSelectionState(true);	
		}
	}

	@Override
	public String toString() {
		return "Color Card";
	}
}
