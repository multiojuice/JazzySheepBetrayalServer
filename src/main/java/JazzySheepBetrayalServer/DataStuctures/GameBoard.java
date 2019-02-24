package JazzySheepBetrayalServer.DataStuctures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameBoard {
    private HashMap<Player,Point> board;
    private HashSet<Player> players;
    private boolean isOn = false;

    public GameBoard() {
        board = new HashMap<Player, Point>();
        players = new HashSet<Player>();
    }

    public void initalize(){
        ArrayList<Point> points = new ArrayList<Point>();
        for(Player player : players){
            Point point = new Point();
            do{
                point.setLocation((int)(Math.random()*20), (int)(Math.random()*20));
            }while(points.contains(point));
            points.add(point);
            board.put(player, point);
        }
    }

    public JsonObject toJson(){
        JsonObject mainObject = new JsonObject();
        for(Player player : board.keySet()){
            JsonObject playerObject = new JsonObject();
            playerObject.addProperty("ID", player.getId());
            playerObject.addProperty("Type", player.getType().name());
            playerObject.addProperty("x",board.get(player).x);
            playerObject.addProperty("y",board.get(player).y);
            mainObject.add("Positions", playerObject);
        }
        return mainObject;
    }


    public boolean addPlayer(Player player){
        if(uniquePlayer(player)){
            players.add(player);
            return true;
        }else{
            return false;
        }
    }

    public boolean uniquePlayer(Player player){
        if(players.contains(player)){
            return false;
        }else{
            return true;
        }
    }


    public Player getPlayerById(String playerid){
        for(Player player : players) {
            if (player.getId().equals(playerid)) {
                return player;
            }
        }
        return null;
    }

    public String movePlayer(String id, int dx, int dy) throws InterruptedException{
        Player player = getPlayerById(id);
        Point newPoint = new Point();
        Point currentPoint = board.get(player);

        newPoint.setLocation((int)currentPoint.getX()+dx, (int)currentPoint.getY()+dy);
        if(newPoint.getX() >= 20){
            newPoint.setLocation(newPoint.getX()-20, newPoint.getY());
        }
        if(newPoint.getY() >= 20){
            newPoint.setLocation(newPoint.getX(), newPoint.getY()-20);
        }

        board.put(player, newPoint);

        checkCollisions();

        // At the end of functionality we will make it wait before returning the go ahead
        wait();
        return new Gson().toJson(toJson());
    }

    public void checkCollisions(){

        ArrayList<Player> dead = new ArrayList<Player>();
        for(Player player : board.keySet()){
            for(Player player2 : board.keySet()){
                Point pos1 = board.get(player);
                Point pos2 = board.get(player2);
                if(pos1.getLocation().equals(pos2.getLocation())){
                    if(player.getType() == Type.BLACK && player2.getType() == Type.WHITE){
                        dead.add(player2);
                    }else if(player.getType() == Type.WHITE && player2.getType() == Type.BLACK){
                        dead.add(player);
                    }
                }
            }
        }

        for(Player deadPlayer : dead){
            board.remove(deadPlayer);
            players.remove(deadPlayer);
        }
    }

    public void endRound() {
        notifyAll();
    }

    public boolean gameIsOn() {
        return this.isOn;
    }
}
