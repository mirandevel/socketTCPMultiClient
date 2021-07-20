/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tablero;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Board {
    Cell[][] matriz=new Cell[15][15];
    Map<Integer,Pawn[][]> Pawns=new HashMap<>();
    String stringBoard;

    public Board() {
         stringBoard=""
                + "1,1,1,1,1,1,0,0,0,2,2,2,2,2,2,"
                + "1,0,0,0,0,1,0,2,2,2,0,0,0,0,2,"
                + "1,0,1,1,0,1,0,2,0,2,0,2,2,0,2,"
                + "1,0,1,1,0,1,0,2,0,2,0,2,2,0,2,"
                + "1,0,0,0,0,1,0,2,0,2,0,0,0,0,2,"
                + "1,1,1,1,1,1,0,2,0,2,2,2,2,2,2,"
                + "0,1,0,0,0,0,5,5,5,0,0,0,0,0,0,"
                + "0,1,1,1,1,1,5,5,5,4,4,4,4,4,4,"
                + "0,0,0,0,0,0,5,5,5,0,0,0,0,4,0,"
                + "3,3,3,3,3,3,0,0,0,4,4,4,4,4,4,"
                + "3,0,0,0,0,3,0,4,4,4,0,0,0,0,4,"
                + "3,0,3,3,0,3,0,4,0,4,0,4,4,0,4,"
                + "3,0,3,3,0,3,0,4,0,4,0,4,4,0,4,"
                + "3,0,0,0,0,3,0,4,0,4,0,0,0,0,4,"
                + "3,3,3,3,3,3,0,4,0,4,4,4,4,4,4";
        //cargar Matriz
        //cargarPeones
    }

    public Cell[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Cell[][] matriz) {
        this.matriz = matriz;
    }

    public Map<Integer, Pawn[][]> getPawns() {
        return Pawns;
    }

    public void setPawns(Map<Integer, Pawn[][]> Pawns) {
        this.Pawns = Pawns;
    }
    
    
    
}
