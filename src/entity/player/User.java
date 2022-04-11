package entity.player;

import java.util.ArrayList;
import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class User extends Player {

	private boolean drawn, picked, turnEnd;

	public User() {
		super();
	}

	@Override
	public void play() {
		System.out.println("User Turn");
		drawableCardList = new ArrayList<UnitCard>();
		for (UnitCard card : this.getCardList()) {
			if (card.isDrawable())
				this.getDrawableCardList().add(card);
		}
		while (!turnEnd) {
			if (isDrawn()) {
				turnEnd = true;
			} else if (isPicked()) {
				boolean endTurn = true;
				for (UnitCard card : this.getCardList()) {
					if (card.isDrawable())
						endTurn = false;
					break;
				}
				if (endTurn)
					turnEnd = true;
			}
			System.out.println(turnEnd);
		}
		setDrawn(false);
		setPicked(false);
		turnEnd = false;
		GameLogic.getInstance().processing();
	}

	@Override
	public void drawCard(UnitCard card) {
		super.drawCard(card);
		if (card.getColor() != Color.BLACK)
			setDrawn(true);
	}

	@Override
	public void pick(int n) {
		super.pick(n);
		if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getUser())
			setPicked(true);
	}

	public boolean isDrawn() {
		return drawn;
	}

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}
}
