package JazzySheepBetrayalServer.Threads;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;
import JazzySheepBetrayalServer.DataStuctures.Type;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController implements Runnable {
    protected GameBoard gameBoard;
    private ServerSocket serverSocket;

    public ServerController() {
        gameBoard = new GameBoard();

        try {
            serverSocket = new ServerSocket(8443);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Starting ServerControllerThread");

        boolean blackSheep = true;
        while(!gameBoard.gameIsOn()) {
            // Listen and get new socket from client
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread thread;
            // Send the socket to a playerThread to handle its interactions
            if (clientSocket != null) {
                if(blackSheep){
                    thread = new Thread(new PlayerThread(clientSocket, gameBoard, "RandomID", Type.BLACK));
                    blackSheep = false;
                }else{
                    thread = new Thread(new PlayerThread(clientSocket, gameBoard, "RandomID", Type.WHITE));
                }
                thread.run();
            }
        }
        Thread roundMonitorThread = new Thread(new RoundMonitorThread(gameBoard));
        roundMonitorThread.run();
    }
}
