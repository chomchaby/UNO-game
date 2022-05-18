package entity.player;

import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class User extends Player {

	private boolean isPlaced, isDrawn, turnEnd;

	public User() {
		super();
	}

	@Override
	public void play() {
		setPlaceableCardList();
		while (!turnEnd) {
			// have to do something... whyyyy
			// System.out.print("");
			doSomething();
			if (isPlaced()) {
				turnEnd = true;
			} else if (isDrawn()) {
				if (placeableCardList.size() == 0) {
					System.out.println("");
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
//		System.out.println("Place: " + isPlaced());
//		System.out.println("Draw: " + isDrawn());
		setPlaced(false);
		setDrawn(false);
		turnEnd = false;
		GameLogic.getInstance().sleepOne();
	}

	@Override
	public void placeCard(UnitCard card) {
		super.placeCard(card);
		if (card.getColor() != Color.BLACK)
			setPlaced(true);
		// it's not the end of BLACK card, 
		// player must choose color and challenge if necessary
	}

	@Override
	public void drawCard(int n) {
		super.drawCard(n);
		if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getUser()) {
			setPlaceableCardList();
			setDrawn(true);
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
			GameLogic.getInstance().getUser().setPlaced(true);

		});
		t.start();

	}

	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}

	public boolean isDrawn() {
		return isDrawn;
	}

	public void setDrawn(boolean isDrawn) {
		this.isDrawn = isDrawn;
	}

	private void doSomething() {
		System.out.print("");
	}
}
