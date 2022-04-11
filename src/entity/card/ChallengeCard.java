package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class ChallengeCard extends EffectCard {

	public ChallengeCard() {
		super(-1, Color.WHITE);
		this.gameAction = GameAction.CHALLENGE;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public void takeAction() {
		GameLogic.getInstance().setColorSelectionState(true);
		GameLogic.getInstance().setNumberState(-1);
		
	}

	@Override
	public String toString() {
		return "Challenge" + "(" + getColor().toString() + ")";
	}
}
