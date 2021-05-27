/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import event.Evento;
import event.EventoListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ServidorEscuchar extends Thread implements EventoListener {

    private ServerSocket serverSocket;
    public boolean escuchando = false;
    ClienteList clienteList;
    private final int PUERTO;

    public ServidorEscuchar(int puerto) {
        PUERTO = puerto;
    }

    @Override
    public void run() {
        escuchando = true;
        clienteList = new ClienteList();
        iniciar();
    }

    public void iniciar() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            while (escuchando) {
                Socket client = serverSocket.accept();
                clienteList.nuevoCliente(client, this);
                new AtenderCliente(client, this).start();
                System.out.println("Nuevo cliente");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorEscuchar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void detener() {
        try {
            escuchando = false;
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorEscuchar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover(Evento evento) {
        clienteList.removerCliente(evento.getSocket(), evento.getEventoListener());
    }

}
