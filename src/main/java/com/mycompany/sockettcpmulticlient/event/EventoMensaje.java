/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.event;

import java.util.EventObject;

/**
 *
 * @author Usuario
 */
public class EventoMensaje extends EventObject{
    String mensaje;
    long clientHash;
    public EventoMensaje(Object source,long clientHash,String mensaje) {
        super(source);
        this.mensaje=mensaje;
        this.clientHash=clientHash;
    }

    public long getClientHash() {
        return clientHash;
    }

    public void setClientHash(long clientHash) {
        this.clientHash = clientHash;
    }
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
