package JazzySheepBetrayalServer.Threads;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;

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
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Thread thread = new Thread(new );
        thread.run();


    }
}
