package entity.card;

import entity.player.User;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class DrawCard extends EffectCard {

	public DrawCard(Color color) {
		super(12, color);
	}

	@Override
	public boolean isPlaceable() {
		// same as NormalCard
		if (GameLogic.getInstance().getColorState() == Color.BLACK) {
			return true;
		} else if (GameLogic.getInstance().getColorState() == this.getColor()
				|| GameLogic.getInstance().getNumberState() == this.getNumber()) {
			return true;
		}
		return false;
	}

	@Override
	public void takeAction() {
		if (GameLogic.getInstance().getNextPlayer() instanceof User) {
			GameLogic.getInstance().sleepTwo();
		}
		GameLogic.getInstance().getNextPlayer().drawCard(2);
	}

	@Override
	public String toString() {

		return "Draw Card" + " (" + UnitCard.myColorToString(getColor()) + ")";
	}
}
