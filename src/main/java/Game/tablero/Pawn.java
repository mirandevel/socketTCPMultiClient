/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tablero;

import java.awt.Point;

/**
 *
 * @author Usuario
 */
public class Pawn {
     public int x,y,id;
    public String color;
    public Point anterior;

    public Pawn(int x, int y,int id,String color) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.color=color;
        anterior=new Point(0,0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        public boolean inBase(){
        if(x<=3 && y<=3) return true;
        if(x<=3 && y>=11) return true;
        if(x>=11 && y<=3) return true;
        if(x<=11 && y>=11) return true;
        
        return false;
    }
    
    
}
