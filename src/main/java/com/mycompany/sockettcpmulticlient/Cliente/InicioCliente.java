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
    private static final int PUERTO=5000;
    private static final String IP="127.0.0.1";
    public static void main(String[] args) {
        CrearClientes cc=new CrearClientes(PUERTO,IP);
        cc.start();
    }

}
