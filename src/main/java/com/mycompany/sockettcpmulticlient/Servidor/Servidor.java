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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.sockettcpmulticlient.event.ConexionListener;
import com.mycompany.sockettcpmulticlient.event.EventoMensaje;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.io.DataOutputStream;

/**
 *
 * @author Usuario
 */
public class Servidor implements ConexionListener, MensajeListener {

    private ServerSocket serverSocket;
    List<Client> clientes = Collections.synchronizedList(new ArrayList<>());
    List<ConexionListener> conexionListeners = new LinkedList<>();
    List<MensajeListener> mensajeListeners = new LinkedList<>();
    private Escuchador escuchador;
    private int PUERTO;

    public Servidor(int puerto) {
        try {
            PUERTO = puerto;
            serverSocket = new ServerSocket(PUERTO);
            //clientes = new LinkedList<>();
            escuchador = new Escuchador(serverSocket);
            escuchador.addConexionListenner(this);
            escuchador.start();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void detener() {
        try {
            synchronized (this) {
                for (int i = 0; i < clientes.size(); i++) {
                    Client cliente = clientes.get(i);
                    cliente.getSocket().close();
                    cliente.getHilo().detener();
                    clientes.remove(cliente);
                }
                System.out.println("disponibles " + clientes.size());
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addConexionListener(ConexionListener conexionListener) {
        conexionListeners.add(conexionListener);
    }

    public void addMensajeListener(MensajeListener mensajeListener) {
        mensajeListeners.add(mensajeListener);
    }

    @Override
    public void onConnect(EventoConexion evento) {
        try {
            System.out.println("Nuevo cliente");
            Socket clientSocket = evento.getSocket();
            int clientId = clientes.isEmpty() ? 1 : clientes.get(clientes.size() - 1).getId() + 1;
            evento.setClienteId(clientId);
            AtenderCliente ac = new AtenderCliente(clientId, clientSocket.getInputStream(), clientSocket.getOutputStream());
            ac.addConexionListenner(this);
            ac.addMensajeListenner(this);
            Client cliente=new Client(clientId, evento.getSocket(), ac);
            clientes.add(cliente);
            ac.start();
            for (ConexionListener listener : conexionListeners) {
                listener.onConnect(evento);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onDisconnect(EventoConexion evento) {
        synchronized (this) {
            try {
                int cliendId = evento.getClienteId();
                for (int i = 0; i < clientes.size(); i++) {
                    Client cliente = clientes.get(i);
                    if (cliendId == cliente.getId()) {
                        cliente.getSocket().close();
                        cliente.getHilo().removeConexionListenner(this);
                        cliente.getHilo().removeMensajeListenner(this);
                        cliente.getHilo().detener();
                        clientes.remove(cliente);
                    }
                }
                for (ConexionListener listener : conexionListeners) {
                    listener.onDisconnect(evento);
                }
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("disponibles " + clientes.size());
        }
    }

    public void cantidadClientes() {
        clientes.size();
    }

    @Override
    public void onMessage(EventoMensaje evento) {
            for (MensajeListener listener : mensajeListeners) {
            listener.onMessage(evento);
        }
        System.out.println(evento.getMensaje());
    }
}
