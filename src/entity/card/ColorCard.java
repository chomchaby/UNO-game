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
	public boolean isDrawable() {
		return true;
	}

	@Override
	public void takeAction() {

		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			GameLogic.getInstance().processing();
			Bot bot = (Bot) GameLogic.getInstance().getCurrentPlayer();
			GameLogic.getInstance().setColorState(bot.chooseColor());
		} 
		else {
			GameLogic.getInstance().setColorSelectionState(true);	
		}
	}

	@Override
	public String toString() {
		return "Color" + "(" + getColor().toString() + ")";
	}
}
