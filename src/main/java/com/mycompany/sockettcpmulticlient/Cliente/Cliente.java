/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Cliente {

    private Socket clientSocket;
    DataInputStream in;
    DataOutputStream out;

    public Cliente() {
    }

    public void conectar(String ip, int port) {

        try {
            clientSocket = new Socket(ip, port);
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

    public String enviarMensaje(String mensaje) throws IOException {
            out.writeUTF(mensaje);
            String respuesta = in.readUTF();
            System.out.println(respuesta);
            return respuesta;
    }
    

    public void detenerConexion() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reconectar(String ip,int puerto) {
        System.out.println("Reconectando...");
        conectar(ip, puerto);
    }
}
