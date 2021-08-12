/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tablero;

import com.google.gson.Gson;

/**
 *
 * @author Usuario
 */
public class TemplateBoard {
    static String template1 = ""
                + "[[-1,-6,-6,-6,-6,-6,0,222,0,-2,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,2,22,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,10,2,0,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,2,0,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,2,0,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,2,0,-6,-6,-6,-6,-6,-6],"
                + "[0,11,0,0,0,0,-5,-6,-6,0,0,0,10,0,0],"
                + "[111,1,1,1,1,1,-6,-6,-6,4,4,4,4,4,444],"
                + "[0,0,10,0,0,0,-6,-6,-6,0,0,0,0,44,0],"
                + "[-3,-6,-6,-6,-6,-6,0,3,0,-4,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,3,0,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,3,0,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,3,10,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,33,3,0,-6,-6,-6,-6,-6,-6],"
                + "[-6,-6,-6,-6,-6,-6,0,333,0,-6,-6,-6,-6,-6,-6]]";
    
    
    public static String getTemplate(int numberTemplate){
        switch (numberTemplate) {
            case 1:
                return template1;
            default:
                return template1;
        }
    }
    
}
