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
    int clienteId;
    public EventoMensaje(Object source,int clienteId,String mensaje) {
        super(source);
        this.mensaje=mensaje;
        this.clienteId=clienteId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
