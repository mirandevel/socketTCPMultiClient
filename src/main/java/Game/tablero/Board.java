/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tablero;

import Game.Gamer;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Board {
    
    int[][] matrix;
    String template;
    public LinkedHashMap<Integer, List<Pawn>> pawns = new LinkedHashMap<>();
    
    public Board() {
        Gson parser = new Gson();
        this.template = TemplateBoard.getTemplate(1);
        this.matrix = parser.fromJson(template, int[][].class);
    }
    
    public String getTemplate() {
        return template;
    }
    
    public void addPawns(int gamerId) {
        List<Pawn> list = new LinkedList<>();
        int fil = 0;
        int col = 0;
        String color="";
        switch (pawns.size()) {
            case 0:
                fil = 2;
                col = 2;
                color="yellow";
                break;
            case 1:
                fil = 2;
                col = 11;
                color="blue";
                break;
            case 2:
                fil = 11;
                col = 2;
                color="green";
                break;
            case 3:
                fil = 11;
                col = 11;
                color="red";
                break;
        }
        list.add(new Pawn(fil, col,1,color));
        list.add(new Pawn(fil, col + 1,2,color));
        list.add(new Pawn(fil + 1, col,3,color));
        list.add(new Pawn(fil + 1, col + 1,4,color));
        pawns.put(gamerId, list);
    }
    
    public LinkedHashMap<Integer, List<Pawn>> getPawns() {
        return pawns;        
    }

    public List<Pawn> getPawnsById(int gamerId) {
        return pawns.get(gamerId);        
    }
    
}
