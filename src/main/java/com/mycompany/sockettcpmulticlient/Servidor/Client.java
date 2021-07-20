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
public class Client {
    int hash;
    Socket socket;
    AtenderCliente hilo;

    public Client() {
        
    }
    

    public Client(Socket socket, AtenderCliente hilo) {
        this.hash=this.hashCode();
        this.socket = socket;
        this.hilo = hilo;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    
    public int getHash() {
        return hash;
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
