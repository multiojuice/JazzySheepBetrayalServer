package JazzySheepBetrayalServer.DataStuctures;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class GameBoard {
    private HashMap<String,Point> board;
    private HashSet<Player> playerIds;

    public GameBoard() {
        board = new HashMap<String, Point>();
        playerIds = new HashSet<Player>();
    }

    public void initalize(){

    }


    public boolean addPlayer(Player player){
        if(uniquePlayer(player)){
            playerIds.add(player);
            return true;
        }else{
            return false;
        }
    }

    public boolean uniquePlayer(Player player){
        if(playerIds.contains(player)){
            return false;
        }else{
            return true;
        }
    }

    public Point getPlayerById(String player){
        return board.get(player);
    }
}
