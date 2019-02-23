package JazzySheepBetrayalServer.Threads;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;

import java.net.Socket;

public class PlayerThread implements Runnable {
    private Socket socket;
    private GameBoard gameBoard;
    private String id;

    public PlayerThread(Socket socket, GameBoard gameBoard, String id) {
        this.socket = socket;
        this.gameBoard = gameBoard;
        this.id = id;
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
