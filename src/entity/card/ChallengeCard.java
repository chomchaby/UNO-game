package entity.card;

import entity.player.Bot;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class ChallengeCard extends EffectCard {

	public ChallengeCard() {
		super(15, Color.BLACK);
	}

	@Override
	public boolean isPlaceable() {
		return true;
	}

	@Override
	public void takeAction() {

		// for Bot, only challenge result is shown
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			GameLogic.getInstance().getCurrentPlayer().challenge();

		}
		// for User, create ColorSelection pane (interface) in status pane
		// also challenge result after finishing the color selection
		else {
			GameLogic.getInstance().setColorSelectionState(true);
			GameLogic.getInstance().setChallengeState(true);
		}

	}

	@Override
	public String toString() {
		return "Challenge Card";
	}
}
