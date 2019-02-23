package JazzySheepBetrayalServer.Threads;

import JazzySheepBetrayalServer.DataStuctures.GameBoard;
import JazzySheepBetrayalServer.DataStuctures.Player;
import JazzySheepBetrayalServer.DataStuctures.Type;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.server.ExportException;

public class PlayerThread implements Runnable {
    private Socket socket;
    private GameBoard gameBoard;
    private Player player;
    private BufferedReader in;
    private PrintWriter out;

    public PlayerThread(Socket socket, GameBoard gameBoard, String id, Type type) {
        this.socket = socket;
        this.gameBoard = gameBoard;
        this.player = new Player(id, type);
        gameBoard.addPlayer(player);

        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            readyProtocol();
            boolean isGameOn = true;
            while (isGameOn) {
                playProtocol();
            }
            endProtocol();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readyProtocol() throws IOException {
        JsonObject playerObject = player.toJson();
        playerObject.addProperty("protocol", "ready");
        out.println(playerObject.toString());
        String readyString = in.readLine();
        if (!readyString.equals("true")) System.out.println("ERROR: NOT READY");
        JsonObject obj = gameBoard.toJson();
        obj.addProperty("protocol", "ready");
        out.println(obj.toString());
    }

    private boolean playProtocol() throws Exception {
        String movement = in.readLine();
        String gameBoardString = gameBoard.movePlayer("id", 1, 1);
        if (gameBoardString.equals("false")) return false;
        out.println(gameBoardString);
        return true;
    }

    private void endProtocol() {
        out.println("{protocol: end}");
    }
}
