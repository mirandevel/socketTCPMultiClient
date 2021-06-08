/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import com.mycompany.sockettcpmulticlient.event.EventoConexion;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.sockettcpmulticlient.event.ConexionListener;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Escuchador extends Thread {
    
    boolean escuchar = false;
    final ServerSocket serverSocket;
    private LinkedList<ConexionListener> conexionListeners;
    
    public Escuchador(ServerSocket serverSocket) {
        escuchar = true;
        this.serverSocket = serverSocket;
        this.conexionListeners = new LinkedList<>();
    }
    
    @Override
    public void run() {
        while (escuchar) {
            try {
                Socket cliente = serverSocket.accept();
                onConnect(cliente);
            } catch (IOException ex) {
                Logger.getLogger(Escuchador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void addConexionListenner(ConexionListener conexionListener) {
        this.conexionListeners.add(conexionListener);
    }
    
    public void removeConexionListenner(ConexionListener conexionListener) {
        this.conexionListeners.remove(conexionListener);
    }
    
    private void onConnect(Socket cliente) {
        for (ConexionListener listener : conexionListeners) {
            listener.onConnect(new EventoConexion(this, cliente));
        }
    }
    
}
