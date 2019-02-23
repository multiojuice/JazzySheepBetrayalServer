package JazzySheepBetrayalServer.Threads;

import java.net.Socket;

public class PlayerThread implements Runnable {
    private Socket socket;

    public PlayerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        
    }
}
