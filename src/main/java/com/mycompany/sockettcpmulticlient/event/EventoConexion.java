/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.event;

import java.net.Socket;
import java.util.EventObject;

/**
 *
 * @author Usuario
 */
public class EventoConexion extends EventObject {

    private Socket socket;
    private Thread hilo;
    private long clientHash;

    public EventoConexion(Object source, Socket socket) {
        super(source);
        this.socket = socket;
    }

    public EventoConexion(Object source, long clientHash, Thread hilo) {
        super(source);
        this.clientHash = clientHash;
        this.socket = socket;
        this.hilo = hilo;
    }

    public long getClientHash() {
        return clientHash;
    }    

    public void setClientHash(long clientHash) {
        this.clientHash = clientHash;
    }
    
    

    public Socket getSocket() {
        return socket;
    }

    public Thread getHilo() {
        return hilo;
    }
}
