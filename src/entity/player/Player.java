package entity.player;

import java.util.ArrayList;
import java.util.Collections;

import entity.card.UnitCard;
import entity.card.EffectCard;
import logic.GameAction;
import logic.GameLogic;

public class Player {
	private String name;
	private ArrayList<UnitCard> cardList;
	private ArrayList<UnitCard> drawableCardList;

	public Player() {
		cardList = new ArrayList<UnitCard>();
	}

	public void play() {
		drawableCardList = new ArrayList<UnitCard>();
		for (UnitCard card : this.getCardList()) {
			if (card.isDrawable())
				this.getDrawableCardList().add(card);
		}
		if (this.getDrawableCardList().size() == 0) {
			UnitCard newCard = this.pick(1).get(0);
			if (newCard.isDrawable())
				this.drawCard(newCard);
		} else {
			UnitCard cardToDraw = this.wiseDraw(this.getDrawableCardList());
			this.drawCard(cardToDraw);
			if (cardToDraw.getAction() != GameAction.NONE)
				((EffectCard) cardToDraw).takeAction();
		}
	}

	private void drawCard(UnitCard card) {
		this.getCardList().remove(card);
		
		GameLogic.getInstance().getCardPile().add(GameLogic.getInstance().getCardOnTable());
		Collections.shuffle(GameLogic.getInstance().getCardPile());
		GameLogic.getInstance().setCardOnTable(card);
	}

	public ArrayList<UnitCard> pick(int n) {
		ArrayList<UnitCard> pickedCard = new ArrayList<UnitCard>();
		for (int i = 0; i < n; i++) {
			UnitCard newCard = GameLogic.getInstance().getCardPile().get(0);
			pickedCard.add(newCard);
			this.getCardList().add(newCard);
			GameLogic.getInstance().getCardPile().remove(newCard);
		}
		return pickedCard;
	}

	// -- To be edit --
	private UnitCard wiseDraw(ArrayList<UnitCard> drawableCardList) {
		return drawableCardList.get(0);
	}

	public boolean isWin() {
		return this.getCardList().size() == 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public ArrayList<UnitCard> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<UnitCard> cardList) {
		this.cardList = cardList;
	}

	public ArrayList<UnitCard> getDrawableCardList() {
		return drawableCardList;
	}

	public void setDrawableCardList(ArrayList<UnitCard> drawableCardList) {
		this.drawableCardList = drawableCardList;
	}

}
