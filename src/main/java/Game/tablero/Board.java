/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tablero;

import Game.Gamer;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Board {
    int[][] matrix;
    String template;
    HashMap<Integer,List<Pawn>> pawns=new java.util.HashMap<>();

    public Board() {      
        Gson parser = new Gson();
        this.template=TemplateBoard.getTemplate(1);
        this.matrix = parser.fromJson(template, int[][].class);
    }

    public String getTemplate() {
        return template;
    }
    
    
    
}
