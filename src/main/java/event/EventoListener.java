/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.EventListener;

/**
 *
 * @author Usuario
 */
public interface EventoListener extends EventListener{
    public void remover(Evento evento);
}
