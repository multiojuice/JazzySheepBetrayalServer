package JazzySheepBetrayalServer.DataStuctures;

import com.google.gson.Gson;

import java.awt.*;
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

    public String getJsonPosition(){
        Gson gson = new Gson();
        return gson.toJson(board);
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


    public Point getPlayerById(String player){
        return board.get(player);
    }

    public String movePlayer() throws InterruptedException{


        // At the end of functionality we will make it wait before returning the go ahead
        wait();
        return "A string of the gameboard";
    }

    public void endRound() {
        notifyAll();
    }

    public boolean gameIsOn() {
        return this.isOn;
    }
}
