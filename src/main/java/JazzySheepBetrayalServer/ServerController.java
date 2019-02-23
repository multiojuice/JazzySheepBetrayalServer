package JazzySheepBetrayalServer;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;

import java.net.ServerSocket;

public class ServerController implements Runnable {
    protected GameBoard gameBoard;
    private ServerSocket serverSocket;

    public ServerController() {
        gameBoard = new GameBoard();
        serverSocket = new ServerSocket();
    }

    public void run() {

    }
}
