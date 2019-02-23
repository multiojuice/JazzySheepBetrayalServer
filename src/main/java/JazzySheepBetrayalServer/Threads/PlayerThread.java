package JazzySheepBetrayalServer.Threads;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;
import JazzySheepBetrayalServer.DataStuctures.Player;
import JazzySheepBetrayalServer.DataStuctures.Type;

import java.net.Socket;

public class PlayerThread implements Runnable {
    private Socket socket;
    private GameBoard gameBoard;
    private Player player;

    public PlayerThread(Socket socket, GameBoard gameBoard, String id, Type type) {
        this.socket = socket;
        this.gameBoard = gameBoard;
        this.player = new Player(id, type);
    }

    public void run() {
        readyProtocol();
        playProtocol();
        endProtocol();
    }

    private void readyProtocol() {

    }

    private void playProtocol() {

    }

    private void endProtocol() {

    }
}
