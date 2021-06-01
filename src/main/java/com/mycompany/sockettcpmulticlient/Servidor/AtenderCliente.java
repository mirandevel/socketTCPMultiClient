/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import com.mycompany.sockettcpmulticlient.event.Evento;
import com.mycompany.sockettcpmulticlient.event.EventoListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AtenderCliente extends Thread {

    private int clienteId;
    private DataInputStream in;
    private DataOutputStream out;
    private final EventoListener eventoListener;
    private boolean atender;

    public AtenderCliente(int clienteId,InputStream in, OutputStream out, EventoListener listener) {
        this.clienteId=clienteId;
        this.in = new DataInputStream(in);
        this.out = new DataOutputStream(out);
        this.eventoListener = listener;
        atender = true;
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
        eventoListener.onDisconnect(new Evento(this, clienteId, this));

    }

    private void onMessage(String mensaje) {
        eventoListener.onMessage(new Evento(this, clienteId, this, mensaje));

    }
}
