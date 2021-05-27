/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Cliente;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CrearClientes extends Thread {

    private final int PUERTO;
    private final String IP;

    LinkedList<Cliente> clientes = new LinkedList<>();

    public CrearClientes(int puerto, String ip) {
        PUERTO = puerto;
        IP = ip;
        for (int i = 0; i < 10; i++) {
            clientes.add(new Cliente());
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < clientes.size(); i++) {
            final int j = i;
            Cliente cliente = clientes.get(i);
            cliente.conectar(IP, PUERTO);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        String respuesta = cliente.enviarMensaje("Cliente " + j + " Hola");
                    } catch (IOException ex) {
                        cliente.reconectar(IP,PUERTO);
                    }
                }
            };
            timer.schedule(task, 0, 2000);
        }
    }  
    

}
