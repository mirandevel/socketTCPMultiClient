/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import com.mycompany.sockettcpmulticlient.Servidor.Servidor;

/**
 *
 * @author Usuario
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameSocket gameSocket=new GameSocket(Integer.parseInt(args[0]));
    }
    
}
