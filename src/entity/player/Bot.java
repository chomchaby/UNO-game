package entity.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import entity.card.EffectCard;
import entity.card.UnitCard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import logic.GameAction;
import logic.GameLogic;
import logic.UpdatableHolder;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}
	
	private void takeItSlowly() {
//		
//		Thread t = new Thread(() -> {
//			Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent actionEvent) {
//					System.out.println("Bot is thinking hard...");
//				}
//			}), new KeyFrame(Duration.seconds(10)));
//			timeline.setCycleCount(1);
//			
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					timeline.play();
//				}
//			});
//		});
//		t.start();
//		try {
//			t.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
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
		}
	}

	@Override
	public void drawCard(UnitCard card) {
		takeItSlowly();
		super.drawCard(card);
	}

	@Override
	public ArrayList<UnitCard> pick(int n) {
		takeItSlowly();
		return super.pick(n);
	}

	private UnitCard wiseDraw(ArrayList<UnitCard> drawableCardList) {
		return drawableCardList.get(0);
	}

}
