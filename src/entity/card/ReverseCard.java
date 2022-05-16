package entity.card;

import entity.player.Bot;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class ReverseCard extends EffectCard {

	public ReverseCard(Color color) {
		super(11, color);
	}

	@Override
	public boolean isPlaceable() {
		// same as NormalCard
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
		GameLogic.getInstance().setClockwise(!GameLogic.getInstance().isClockwise());
	}

	@Override
	public String toString() {

		return "Reverse Card" + " (" + GameLogic.getInstance().myColorToString(getColor()) + ")";
	}
}
