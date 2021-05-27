/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import event.Evento;
import event.EventoListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import java.net.Socket;


/**
 *
 * @author Usuario
 */
public class AtenderCliente extends Thread {

    private final Socket clientSocket;
    private final EventoListener eventoListener;
    private DataOutputStream out;
    private DataInputStream in;

    public AtenderCliente(Socket socket,EventoListener listener) {
        this.clientSocket = socket;
        this.eventoListener=listener;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            
            while (!clientSocket.isClosed()) {
                try {
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);
                    out.writeUTF("Servidor "+mensaje);
                } catch (EOFException ex) {
                    break;
                }
            }
            
            if (clientSocket.isClosed()) {
                System.out.println("El cliente ha finalizo");
            } else {
                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Cliente finalizado desde el servidor");
            }
            
            removerCliente();
            
        } catch (IOException ex) {
            removerCliente();
            //System.out.println(ex.getMessage());
        }
    }
    private void removerCliente(){
        eventoListener.remover(new Evento(clientSocket,eventoListener));
    }
}
