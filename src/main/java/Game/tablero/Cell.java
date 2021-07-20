/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tablero;

/**
 *
 * @author Usuario
 */
public class Cell {
    String color;
    String option;

    public Cell(String color, String option) {
        this.color = color;
        this.option = option;
    }

    

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
    

}
