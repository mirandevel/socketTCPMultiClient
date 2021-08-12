/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class Response {
    public static final String LOGIN="login";
    public static final String GAME="game";
    public static final String NEW_GAMER="new_gamer";
    public static final String LAUNCH_DICE="launch_dice";
    public static final String FINISH_DICE="finish_dice";
    public static final String MOVE_PAWN="move_pawn";
    
    
    
    
    String action;
    HashMap<String,Object> data;

    public Response(String action) {
        this.action = action;
        this.data = new HashMap<>();
    }
    public void add(String name,Object obj){
        data.put(name, obj);
    }
     public Object get(String name){
        return data.get(name);
    }
         public boolean isAction(String action){
        return action.compareTo(this.action)==0;
    }
    
}
