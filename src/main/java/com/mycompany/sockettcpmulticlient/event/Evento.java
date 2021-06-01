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
public class Evento extends EventObject {

    private Socket socket;
    private Thread hilo;
    private String mensaje;
    private int clienteId;

    public Evento(Object source, Socket socket) {
        super(source);
        this.socket = socket;
    }

    public Evento(Object source, int clienteId, Thread hilo) {
        super(source);
        this.clienteId = clienteId;
        this.socket = socket;
        this.hilo = hilo;

    }

    public Evento(Object source, int clienteId, Thread hilo, String mensaje) {
        super(source);
        this.clienteId = clienteId;
        this.socket = socket;
        this.hilo = hilo;
        this.mensaje = mensaje;
    }

    public int getClienteId() {
        return clienteId;
    }    

    public Socket getSocket() {
        return socket;
    }

    public Thread getHilo() {
        return hilo;
    }

    public String getMensaje() {
        return mensaje;
    }

}
