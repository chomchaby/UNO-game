package entity.player;

import entity.card.ChallengeCard;
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
		setDrawableCardList();
		while (!turnEnd) {
			// have to do something... whyyyy
			// System.out.print("");
			doSomething();
			if (isDrawn()) {
				turnEnd = true;
			} else if (isPicked()) {
				if (drawableCardList.size() == 0) {
					turnEnd = true;
				}
// 				java.util.ConcurrentModificationException...
//				for (UnitCard card : this.getCardList()) {
//					if (card.isDrawable()) {
//						endTurn = false;
//						break;
//					}
//				}
//				Iterator<UnitCard> iterator = this.getCardList().iterator();
//				while (iterator.hasNext()) {
//					UnitCard card = iterator.next();
//					if (card.isDrawable()) {
//						endTurn = false;
//						break;
//					}
//				}
			}
		}
//		System.out.println("Drawn: " + isDrawn());
//		System.out.println("Picked: " + isPicked());
		setDrawn(false);
		setPicked(false);
		turnEnd = false;
		GameLogic.getInstance().shortProcessing();
	}

	@Override
	public void drawCard(UnitCard card) {
		super.drawCard(card);
		// it's not the end of BLACK card, 
		// player must choose color and challenge if necessary
		if (card.getColor() != Color.BLACK)
			setDrawn(true);
	}

	@Override
	public void pick(int n) {
		super.pick(n);
		if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getUser()) {
			setDrawableCardList();
			setPicked(true);
		}
	}

	@Override
	public void challenge() {
		// after select color in ColorSelection pane
		// implement in thread because it needs time sleep
		Thread t = new Thread(() -> {
			// punishment
			GameLogic.getInstance().punishChallenge();
			// to close result text
			GameLogic.getInstance().setChallengeState(false);
			// the action of challenge card ends.
			GameLogic.getInstance().getUser().setDrawn(true);

		});
		t.start();

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

	private void doSomething() {
		System.out.print("");
	}
}
