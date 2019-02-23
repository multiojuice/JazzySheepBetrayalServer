package JazzySheepBetrayalServer.Threads;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;

public class RoundMonitorThread implements Runnable {
    private GameBoard gameBoard;

    public RoundMonitorThread(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }

            gameBoard.endRound();
        }
    }
}
