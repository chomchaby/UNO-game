package entity.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;

import entity.card.ChallengeCard;
import entity.card.ColorCard;
import entity.card.DrawCard;
import entity.card.NormalCard;
import entity.card.ReverseCard;
import entity.card.SkipCard;
import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;
import sharedObject.AudioLoader;
import sharedObject.ColorLoader;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}

	@Override
	public void play() {
		setPlaceableCardList();
		if (placeableCardList.size() == 0) {
			drawCard(1);
			setPlaceableCardList();
			if (placeableCardList.size() > 0)
				placeCard(wisePlace(placeableCardList));
		} else {
			placeCard(wisePlace(placeableCardList));
		}
	}

	@Override
	public void placeCard(UnitCard card) {
		// act like bot is thinking...
		GameLogic.getInstance().sleepTwo();
		AudioLoader.mouseClick1Sound.play();
		super.placeCard(card);
	}

	@Override
	public void drawCard(int n) {
		if (GameLogic.getInstance().getCurrentPlayer() instanceof User
				&& GameLogic.getInstance().getCardOnTable() instanceof DrawCard) {
			// no waiting if it came from user's draw card
		} else {
			// always wait to show the process
			GameLogic.getInstance().sleepTwo();
		}
		AudioLoader.mouseClick2Sound.play();
		super.drawCard(n);
	}

	@Override
	public void challenge() {

		// act like bot is thinking
		GameLogic.getInstance().sleepTwo();
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

	private UnitCard wisePlace(ArrayList<UnitCard> drawableCardList) {
		Collections.shuffle(drawableCardList);
		ArrayList<UnitCard> normal = new ArrayList<UnitCard>();
		ArrayList<UnitCard> skip = new ArrayList<UnitCard>();
		ArrayList<UnitCard> reverse = new ArrayList<UnitCard>();
		ArrayList<UnitCard> draw = new ArrayList<UnitCard>();
		ArrayList<UnitCard> color = new ArrayList<UnitCard>();
		ArrayList<UnitCard> challenge = new ArrayList<UnitCard>();
		for (UnitCard card : drawableCardList) {
			if (card instanceof NormalCard) {
				normal.add(card);
			} else if (card instanceof SkipCard) {
				skip.add(card);
			} else if (card instanceof ReverseCard) {
				reverse.add(card);
			} else if (card instanceof DrawCard) {
				draw.add(card);
			} else if (card instanceof ColorCard) {
				color.add(card);
			} else if (card instanceof ChallengeCard) {
				challenge.add(card);
			}
		}
		Random rand = new Random();
		// 1 card
		if (GameLogic.getInstance().getNextPlayer().getCardList().size() == 1) {
			if (challenge.size() > 0) {
				return challenge.get(0);
			}
			if (draw.size() > 0) {
				return draw.get(0);
			}
			if (skip.size() > 0 || reverse.size() > 0) {
				if (skip.size() == 0)
					return reverse.get(0);
				else if (reverse.size() == 0)
					return skip.get(0);
				else {
					int i = rand.nextInt(2);
					if (i == 0)
						return skip.get(0);
					else
						return reverse.get(0);
				}
			}
			if (normal.size() > 0)
				return normal.get(0);
			return color.get(0);
		}
		// 2-4 cards
		if (GameLogic.getInstance().getNextPlayer().getCardList().size() < 5) {
			if (GameLogic.getInstance().getBeforePlayer().getCardList().size() < GameLogic.getInstance().getNextPlayer()
					.getCardList().size()) {
				for (UnitCard card : drawableCardList) {
					if (card instanceof ChallengeCard || card instanceof DrawCard || card instanceof SkipCard) {
						return card;
					}
				}
				if (normal.size() > 0)
					return normal.get(0);
				if (reverse.size() > 0)
					return reverse.get(0);	
			} else {
				for (UnitCard card : drawableCardList) {
					if (card instanceof ChallengeCard || card instanceof DrawCard || card instanceof SkipCard
							|| card instanceof ReverseCard) {
						return card;
					}
				}
				if (normal.size() > 0)
					return normal.get(0);
			}
			return color.get(0);
		}
		// > 5 cards
		for (UnitCard card : drawableCardList) {
			if (card.getColor() != Color.BLACK) {
				return card;
			}
		}
		if (color.size() > 0)
			return color.get(0);
		return challenge.get(0);
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
