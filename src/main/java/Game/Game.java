/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.tablero.Board;
import Game.tablero.Pawn;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Game {

    HashMap<Integer, Gamer> gamers = new HashMap<>();
    Board board;
    int turnId;

    public Game() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public int getTurn() {
        return turnId;
    }

    public long nextTurn() {
        int i = 0;
        for (long id : getIds()) {
            if (turnId == id) {
                if (i == gamers.size() - 1) {
                    i = -1;
                }
                i++;
                break;
            }
            i++;
        }
        turnId = getIds().get(i);
        return turnId;
    }

    public void addGamer(Gamer gamer) {
        gamers.put(gamer.clientHash, gamer);
        board.addPawns(gamer.clientHash);
        if (gamers.size() == 1) {
            turnId = getIds().getFirst();
        }
    }

    public void removeGamer(Gamer gamer) {
        gamers.remove(gamer.clientHash);
    }

    public LinkedList<Integer> getIds() {
        LinkedList<Integer> ids = new LinkedList<>(gamers.keySet());
        return ids;
    }

    HashMap<Integer, List<Pawn>> getPawns() {
        return board.getPawns();
    }
    public List<Pawn> getPawnsById(int gamerId) {
    return board.getPawnsById(gamerId);
    }
    
    public void movePawn(int gamerId,Pawn pawn){
        
        for(Pawn p:board.getPawnsById(gamerId)){
            if(p.id==pawn.id){
                p.x=pawn.x;
                p.y=pawn.y;
                p.anterior=pawn.anterior;
            }
        }
    }
    
        public boolean allPawnsInBase(int gamerId) {

        for (Pawn p : board.pawns.get(gamerId)) {
             if(!p.inBase()){
                 return false;
             }
        }
        return true;
    }

}
