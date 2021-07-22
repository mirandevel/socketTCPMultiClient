/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import Game.tablero.TemplateBoard;
import com.mycompany.sockettcpmulticlient.Servidor.Servidor;
import com.mycompany.sockettcpmulticlient.event.ConexionListener;
import com.mycompany.sockettcpmulticlient.event.EventoConexion;
import com.mycompany.sockettcpmulticlient.event.EventoMensaje;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
        authController=new AuthController(servidor);
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
    public void onMessage(EventoMensaje event) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResult = (JSONObject) parser.parse(event.getMensaje());
            String action=jsonResult.get("action").toString();
            if(action.compareTo("login")==0){
                Thread t=new Thread(() -> {
                    authController.login(jsonResult, event.getClientHash());
                });
                t.start();
            }
            if(action.compareTo("board")==0){
                servidor.send(TemplateBoard.getTemplate(1), event.getClientHash());
            }
        } catch (ParseException ex) {
            Logger.getLogger(GameSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
