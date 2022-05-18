package logic;

import entity.card.UnitCard;
import entity.player.Bot;

public class GameRun extends Thread {
	public void run() {
		while (true) {
			System.out.print(""); // must do somthing 555
			while (!GameLogic.getInstance().isRoundEnd()) {
				System.err.println("---- NEW TURN ----");
				System.out.println(">>> " + GameLogic.getInstance().getCurrentPlayer().getName() + " Turn");
//				System.out.println("Turn : " + GameLogic.getInstance().getPlayerTurn());
//				System.out.println("Playable : " + GameLogic.getInstance().getCurrentPlayer().isPlayable());
//				System.out.println("Clockwise : " + GameLogic.getInstance().isClockwise());
//				System.out.println("Next Player: " + GameLogic.getInstance().getNextPlayer().getName());
				if (GameLogic.getInstance().getCurrentPlayer().isPlayable()) {
//					GameLogic.getInstance().getCurrentPlayer().setPlaceableCardList();
//					if (GameLogic.getInstance().getCurrentPlayer().getPlaceableCardList().size() > 0) {
//						for (UnitCard card : GameLogic.getInstance().getCurrentPlayer().getPlaceableCardList()) {
//							System.out.println(card.toString());
//						}
//					}
					GameLogic.getInstance().getCurrentPlayer().play();

				} else {
					System.out.println(" - Skipped - ");
					GameLogic.getInstance().sleepOne();
					GameLogic.getInstance().getCurrentPlayer().setPlayable(true);

				}
				if (GameLogic.getInstance().getCurrentPlayer().isWin()) {
					GameLogic.getInstance().setRoundEnd(true);

					break;
				}
				GameLogic.getInstance().setUpForNewTurn();
				GameLogic.getInstance().sleepOne();
			}
			if (GameLogic.getInstance().getWinner() != null) {
				break;
			}
		}
	}
}
