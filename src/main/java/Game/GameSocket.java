/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.tablero.Board;
import Game.tablero.TemplateBoard;
import com.google.gson.Gson;
import com.mycompany.sockettcpmulticlient.Servidor.Servidor;
import com.mycompany.sockettcpmulticlient.event.ConexionListener;
import com.mycompany.sockettcpmulticlient.event.EventoConexion;
import com.mycompany.sockettcpmulticlient.event.EventoMensaje;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Usuario
 */
public class GameSocket implements ConexionListener, MensajeListener {

    final Servidor servidor;
    final int PORT;
    AuthController authController;
    Game game = new Game();

    public GameSocket(int port) {
        this.PORT = port;
        servidor = new Servidor(this.PORT);
        servidor.addConexionListener(this);
        servidor.addMensajeListener(this);
        authController = new AuthController(servidor);
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
            String action = jsonResult.get("action").toString();
            if (action.compareTo("login") == 0) {
                Thread t = new Thread(() -> {
                    authController.login(jsonResult, event.getClientHash());
                });
                t.start();
            }
            if (action.compareTo("game") == 0) {
                JSONObject obj = new JSONObject();
                game.addGamer(authController.getGamer(event.getClientHash()));

                obj.put("action", "new_gamer");
                obj.put("id", event.getClientHash());
                for (Long key : game.gamers.keySet()) {
                    servidor.send(obj.toJSONString(), key);
                }

                obj = new JSONObject();
                obj.put("action", "game");
                obj.put("gamers", game.getIds());
                obj.put("turn", game.getTurn());
                obj.put("success", true);
                obj.put("template", game.getBoard().getTemplate());

                servidor.send(obj.toJSONString(), event.getClientHash());
            }
            if (action.compareTo("launch_dice") == 0) {
                for (Long key : game.gamers.keySet()) {
                    servidor.send(jsonResult.toJSONString(), key);
                }
            }
             if (action.compareTo("finish_dice") == 0) {
                 jsonResult.put("turn", game.nextTurn());
                for (Long key : game.gamers.keySet()) {
                    servidor.send(jsonResult.toJSONString(), key);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(GameSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
