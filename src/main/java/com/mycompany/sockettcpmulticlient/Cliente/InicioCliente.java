/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Cliente;


/**
 *
 * @author Usuario
 */
public class InicioCliente {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        CrearClientes cc=new CrearClientes(Integer.parseInt(args[0]),args[1]);
        cc.conectar();
        cc.enviarMensaje();
    }

}
