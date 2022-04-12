package entity.card;

import entity.player.Bot;
import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class ChallengeCard extends EffectCard {

	public ChallengeCard() {
		super(15, Color.BLACK);
		this.gameAction = GameAction.CHALLENGE;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public void takeAction() {
		
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			GameLogic.getInstance().shortProcessing();
			Bot bot = (Bot) GameLogic.getInstance().getCurrentPlayer();
			GameLogic.getInstance().setColorState(bot.chooseColor());
		} 
		else {
			GameLogic.getInstance().setColorSelectionState(true);
		}

	}

	@Override
	public String toString() {
		return "Challenge" + "(" + getColor().toString() + ")";
	}
}
