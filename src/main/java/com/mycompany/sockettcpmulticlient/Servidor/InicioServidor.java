/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

/**
 *
 * @author Usuario
 */
public class InicioServidor {

    /**
     * @param args the command line arguments
     */
    private static final int PUERTO=5000;
    public static void main(String[] args) {
        ServidorEscuchar escuchar=new ServidorEscuchar(PUERTO);
        escuchar.start();
    }
    
}
