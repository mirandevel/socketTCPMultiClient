/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.tablero.Board;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Game {

    HashMap<Long, Gamer> gamers = new HashMap<>();
    Board board;
    long turnId;

    public Game() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public long getTurn() {
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
        if (gamers.size() == 1) {
            turnId = getIds().getFirst();
        }
    }

    public void removeGamer(Gamer gamer) {
        gamers.remove(gamer.clientHash);
    }

    public LinkedList<Long> getIds() {
        LinkedList<Long> ids = new LinkedList<>(gamers.keySet());
        return ids;
    }

}
