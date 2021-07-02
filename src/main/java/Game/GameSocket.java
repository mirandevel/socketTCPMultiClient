/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import com.mycompany.sockettcpmulticlient.Servidor.Servidor;
import com.mycompany.sockettcpmulticlient.event.ConexionListener;
import com.mycompany.sockettcpmulticlient.event.EventoConexion;
import com.mycompany.sockettcpmulticlient.event.EventoMensaje;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
/**
 *
 * @author Usuario
 */
public class GameSocket implements ConexionListener, MensajeListener{
    final Servidor servidor;
    final int PORT;
    AuthController authController;

    public GameSocket(int port) {
        this.PORT=port;
        servidor=new Servidor(this.PORT);
        servidor.addConexionListener(this);
        servidor.addMensajeListener(this);
        authController=new AuthController();
    }

    @Override
    public void onConnect(EventoConexion evento) {
        System.out.println("GAME: nuevo cliente conectado");
    }

    @Override
    public void onDisconnect(EventoConexion evento) {
         System.out.println("GAME: cliente desconectado");
        
    }

    @Override
    public void onMessage(EventoMensaje evento) {
        System.out.println(authController.login(evento.getMensaje()));
    }
    
}
