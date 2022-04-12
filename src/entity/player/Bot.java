package entity.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}

	@Override
	public void play() {
		System.out.println(name + "Turn");
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
		GameLogic.getInstance().longProcessing();
		super.drawCard(card);
	}

	@Override
	public void pick(int n) {
		GameLogic.getInstance().longProcessing();
		super.pick(n);
	}

	private UnitCard wiseDraw(ArrayList<UnitCard> drawableCardList) {
		Collections.shuffle(drawableCardList);
		return drawableCardList.get(0);
	}

	public Color chooseColor() {
		int[] count = { 0, 0, 0, 0 };
		for (UnitCard card : this.getCardList()) {
			if (card.getColor() == Color.BLUE) {
				count[0] += 1;
			}
			if (card.getColor() == Color.GREEN) {
				count[1] += 1;
			}
			if (card.getColor() == Color.RED) {
				count[2] += 1;
			}
			if (card.getColor() == Color.YELLOW) {
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
			return Color.BLUE;
		} else if (ind.get(0) == 1) {
			return Color.GREEN;
		} else if (ind.get(0) == 2) {
			return Color.RED;
		} else {
			return Color.YELLOW;
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
