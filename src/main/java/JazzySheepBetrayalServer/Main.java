package JazzySheepBetrayalServer;

import JazzySheepBetrayalServer.Threads.ServerController;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new ServerController());
        thread.start();
    }
}
