/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockettcpmulticlient.event;

import java.util.EventListener;

/**
 *
 * @author Usuario
 */
public interface EventoListener extends EventListener {

    public void onConnect(Evento evento);

    public void onMessage(Evento evento);

    public void onDisconnect(Evento evento);


}
