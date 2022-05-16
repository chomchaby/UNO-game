package entity.card;

import entity.player.Bot;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class SkipCard extends EffectCard {

	public SkipCard(Color color) {
		super(10, color);
	}

	@Override
	public boolean isPlaceable() {
		if (GameLogic.getInstance().getColorState() == Color.BLACK) {
			return true;
		}
		else if (GameLogic.getInstance().getColorState() == this.getColor()
				|| GameLogic.getInstance().getNumberState() == this.getNumber()) {
			return true;
		}
		return false;
	}

	@Override
	public void takeAction() {
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			GameLogic.getInstance().shortProcessing();
		}
		GameLogic.getInstance().getNextPlayer().setPlayable(false);;
	}

	@Override
	public String toString() {

		return "Skip Card" + " (" + GameLogic.getInstance().myColorToString(getColor()) + ")";
	}

}
