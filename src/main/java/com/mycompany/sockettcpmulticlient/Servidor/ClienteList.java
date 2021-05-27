/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Servidor;

import event.EventoListener;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class ClienteList {

    protected LinkedList<Socket> socketList = new LinkedList<>();
    protected LinkedList<EventoListener> eventoList = new LinkedList<>();

    public ClienteList() {

    }

    public synchronized void nuevoCliente(Socket socket, EventoListener eventoListener) {
        eventoList.add(eventoListener);
        socketList.add(socket);
    }

    public synchronized void removerCliente(Socket socket, EventoListener eventoListener) {
        eventoList.remove(eventoListener);
        socketList.remove(socket);
        System.out.println("Cliente removido");
        System.out.println("Cantidad " + cantidad());
    }

    public synchronized int cantidad() {
        return socketList.size();
    }

}
