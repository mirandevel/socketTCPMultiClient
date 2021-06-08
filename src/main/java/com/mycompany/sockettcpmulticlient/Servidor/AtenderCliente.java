/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import com.mycompany.sockettcpmulticlient.event.EventoConexion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.sockettcpmulticlient.event.ConexionListener;
import com.mycompany.sockettcpmulticlient.event.EventoMensaje;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class AtenderCliente extends Thread {

    private int clienteId;
    private DataInputStream in;
    private DataOutputStream out;
    private  LinkedList<MensajeListener> mensajeListeners;
    private  LinkedList<ConexionListener> conexionListeners;
    private boolean atender;

    public AtenderCliente(int clienteId,InputStream in, OutputStream out) {
        this.clienteId=clienteId;
        this.in = new DataInputStream(in);
        this.out = new DataOutputStream(out);
      
        this.mensajeListeners=new LinkedList<>();
         this.conexionListeners=new LinkedList<>();
        atender = true;
    }
    
    public void addMensajeListenner(MensajeListener mensajeListener){
        this.mensajeListeners.add(mensajeListener);
    }
    
    public void removeMensajeListenner(MensajeListener mensajeListener){
        this.mensajeListeners.remove(mensajeListener);
    }
    public void addConexionListenner(ConexionListener conexionListener){
        this.conexionListeners.add(conexionListener);
    }
    
    public void removeConexionListenner(ConexionListener conexionListener){
        this.conexionListeners.remove(conexionListener);
    }
    

    @Override
    public void run() {
        try {
            while (atender) {
                try {
                    String mensaje = in.readUTF();
                    onMessage(mensaje);
                    out.writeUTF("Servidor " + mensaje);
                } catch (EOFException ex) {
                    break;
                }
            }
            onDisconnect();

        } catch (IOException ex) {
            onDisconnect();
        }
    }

    public void detener() {
        try {
            atender = false;
            in.close();
            out.close();
            this.interrupt();
        } catch (IOException ex) {
            Logger.getLogger(AtenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onDisconnect() {
        for(ConexionListener listener: conexionListeners)
            listener.onDisconnect(new EventoConexion(this, clienteId, this));
    }

    private void onMessage(String mensaje) {
        for(MensajeListener listener:mensajeListeners)
        listener.onMessage(new EventoMensaje(this, clienteId, mensaje));

    }
}
