/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.net.Socket;

/**
 *
 * @author Usuario
 */
public class Evento {
    private final Socket socket;
    private final EventoListener eventoListener;

    public Evento(Socket socket, EventoListener eventoListener) {
        this.socket = socket;
        this.eventoListener = eventoListener;
    }

    public Socket getSocket() {
        return socket;
    }

    public EventoListener getEventoListener() {
        return eventoListener;
    }

    
    
    
}
