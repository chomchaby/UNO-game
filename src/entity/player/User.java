package entity.player;

import java.util.ArrayList;
import java.util.Collections;

import entity.card.EffectCard;
import entity.card.UnitCard;
import logic.GameAction;
import logic.GameLogic;

public class User extends Player{
	
	public User() {
		super();
	}
	@Override
	public void play() {
		drawableCardList = new ArrayList<UnitCard>();
		for (UnitCard card : this.getCardList()) {
			if (card.isDrawable())
				this.getDrawableCardList().add(card);
		}
		System.out.println("User Turnnnnnnnnnnnnnnnnn");
//		if (this.getDrawableCardList().size() == 0) {
//			UnitCard newCard = this.pick(1).get(0);
//			if (newCard.isDrawable())
//				this.drawCard(newCard);
//		} else {
//			UnitCard cardToDraw = this.wiseDraw(this.getDrawableCardList());
//			this.drawCard(cardToDraw);
//			if (cardToDraw.getAction() != GameAction.NONE)
//				((EffectCard) cardToDraw).takeAction();
//		}
	}
	@Override
	public void drawCard(UnitCard card) {
		this.getCardList().remove(card);
		GameLogic.getInstance().getCardPile().add(GameLogic.getInstance().getCardOnTable());
		Collections.shuffle(GameLogic.getInstance().getCardPile());
		GameLogic.getInstance().setCardOnTable(card);
		
	}
	@Override
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
}
