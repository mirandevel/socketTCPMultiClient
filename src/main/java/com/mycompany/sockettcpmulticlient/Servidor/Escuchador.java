/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import com.mycompany.sockettcpmulticlient.event.Evento;
import com.mycompany.sockettcpmulticlient.event.EventoListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Escuchador extends Thread {

    boolean escuchar=false;
    final ServerSocket serverSocket;
    final EventoListener eventoListener;

    public Escuchador(ServerSocket serverSocket,EventoListener eventoListener) {
        escuchar=true;
        this.serverSocket=serverSocket;
        this.eventoListener=eventoListener;
    }
    
    
    @Override
    public void run() {
            while (escuchar) {
                try {
                    Socket cliente = serverSocket.accept();
                    eventoListener.onConnect(new Evento(this,cliente));
                } catch (IOException ex) {
                    Logger.getLogger(Escuchador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     
    }
}
