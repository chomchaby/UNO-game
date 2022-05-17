package logic;

public class GameRun extends Thread {
	public void run() {
		while (true) {
			System.out.println(""); // must do somthing 555
			while (!GameLogic.getInstance().isRoundEnd()) {
				System.err.println("---- NEW TURN ----");
				System.out.println(">>> " + GameLogic.getInstance().getCurrentPlayer().getName() + " Turn");
//				System.out.println("Turn : " + GameLogic.getInstance().getPlayerTurn());
//				System.out.println("Playable : " + GameLogic.getInstance().getCurrentPlayer().isPlayable());
//				System.out.println("Clockwise : " + GameLogic.getInstance().isClockwise());
//				System.out.println("Next Player: " + GameLogic.getInstance().getNextPlayer().getName());
				if (GameLogic.getInstance().getCurrentPlayer().isPlayable()) {
					GameLogic.getInstance().getCurrentPlayer().play();

				} else {
					System.out.println(" - Blocked - ");
					GameLogic.getInstance().sleepTwo();
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
