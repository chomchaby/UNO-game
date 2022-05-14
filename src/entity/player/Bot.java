package entity.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import entity.card.ChallengeCard;
import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;
import sharedObject.ColorLoader;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}

	@Override
	public void play() {
		setDrawableCardList();
		if (drawableCardList.size() == 0) {
			pick(1);
			setDrawableCardList();
			if (drawableCardList.size() > 0)
				drawCard(wiseDraw(drawableCardList));
		} else {
			drawCard(wiseDraw(drawableCardList));
		}
	}

	@Override
	public void drawCard(UnitCard card) {
		// act like bot is thinking...
		GameLogic.getInstance().longProcessing();
		super.drawCard(card);
	}

	@Override
	public void pick(int n) {
		// act like bot is thinking...
		GameLogic.getInstance().longProcessing();
		super.pick(n);
	}

	@Override
	public void challenge() {

		// act like bot is thinking
		GameLogic.getInstance().longProcessing();
		// bot choose color to challenge
		Color colorToChallenge = this.chooseColor();
		GameLogic.getInstance().setChallengeColor(colorToChallenge);
		GameLogic.getInstance().setColorState(colorToChallenge);
		// to create result text
		GameLogic.getInstance().setChallengeState(true);
		GameLogic.getInstance().punishChallenge();
		// to close result text
		GameLogic.getInstance().setChallengeState(false);

	}

	private UnitCard wiseDraw(ArrayList<UnitCard> drawableCardList) {
		// the cleverest :)
		Collections.shuffle(drawableCardList);
		return drawableCardList.get(0);
	}

	public Color chooseColor() {
		// choose the color that has the most cards in list.
		int[] count = { 0, 0, 0, 0 };
		for (UnitCard card : this.getCardList()) {
			if (card.getColor() == ColorLoader.BLUE) {
				count[0] += 1;
			}
			if (card.getColor() == ColorLoader.GREEN) {
				count[1] += 1;
			}
			if (card.getColor() == ColorLoader.RED) {
				count[2] += 1;
			}
			if (card.getColor() == ColorLoader.YELLOW) {
				count[3] += 1;
			}
		}
		Arrays.sort(count);
		int largest = count[3];
		ArrayList<Integer> ind = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (count[i] == largest)
				ind.add(i);
		}
		Collections.shuffle(ind);

		if (ind.get(0) == 0) {
			return ColorLoader.BLUE;
		} else if (ind.get(0) == 1) {
			return ColorLoader.GREEN;
		} else if (ind.get(0) == 2) {
			return ColorLoader.RED;
		} else {
			return ColorLoader.YELLOW;
		}

	}

//		Do not need thread, already in new thread form main.	
// 		Run pass :)

//		Thread t = new Thread(() -> {
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		t.start();
//		try {
//			t.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

}
