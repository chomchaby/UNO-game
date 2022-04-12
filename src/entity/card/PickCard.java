package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;
import entity.player.User;

public class PickCard extends EffectCard {

	public PickCard(Color color) {
		super(12, color);
		this.gameAction = GameAction.PICK;
	}

	@Override
	public boolean isDrawable() {
		if (GameLogic.getInstance().getCardOnTable().getColor() == Color.BLACK) {
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
		if (GameLogic.getInstance().getCurrentPlayer() instanceof User) {
			GameLogic.getInstance().shortProcessing();
		}
		GameLogic.getInstance().getNextPlayer().pick(2);
	}

	@Override
	public String toString() {
		return "Pick" + "(" + getColor().toString() + ")";
	}
}
