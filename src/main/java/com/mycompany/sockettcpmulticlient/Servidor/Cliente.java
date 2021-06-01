/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import java.net.Socket;

/**
 *
 * @author Usuario
 */
public class Cliente {
    int id;
    Socket socket;
    AtenderCliente hilo;

    public Cliente(int id,Socket socket, AtenderCliente hilo) {
        this.id=id;
        this.socket = socket;
        this.hilo = hilo;
    }

    public int getId() {
        return id;
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public AtenderCliente getHilo() {
        return hilo;
    }

    public void setHilo(AtenderCliente hilo) {
        this.hilo = hilo;
    }

    
}
