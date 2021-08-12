/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.tablero.Board;
import Game.tablero.Pawn;
import Game.tablero.TemplateBoard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    Gson gson = new Gson();

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
        Response response = gson.fromJson(event.getMensaje(), Response.class);

        if (response.isAction(Response.LOGIN)) {
            Thread t = new Thread(() -> {
                authController.login(response, event.getClientHash());
            });
            t.start();
        }
        if (response.isAction(Response.GAME)) {
            Response response1 = new Response(Response.NEW_GAMER);
            game.addGamer(authController.getGamer(event.getClientHash()));
            response1.add("gamer", gson.toJson(game.gamers.get(event.getClientHash())));
            response1.add("pawns", gson.toJson(game.getPawnsById(event.getClientHash())));
            for (Integer key : game.gamers.keySet()) {
                servidor.send(gson.toJson(response1), key);
            }

            response1 = new Response(Response.GAME);
            response1.add("game", gson.toJson(game));
            response1.add("template", game.getBoard().getTemplate());

            servidor.send(gson.toJson(response1), event.getClientHash());
        }
        if (response.isAction(Response.LAUNCH_DICE)) {
            for (Integer key : game.gamers.keySet()) {
                servidor.send(event.getMensaje(), key);
            }
        }
        if (response.isAction(Response.FINISH_DICE)) {
            int number = ((Double) response.get("number")).intValue();
             if (game.allPawnsInBase(game.getTurn())) {
                    if (number != 5)  response.add("turn", game.nextTurn());
                    else response.add("turn", game.getTurn());       
                    
             }else response.add("turn", game.getTurn());
             
            
            for (Integer key : game.gamers.keySet()) {
                servidor.send(gson.toJson(response), key);
            }
        }
        if (response.isAction(Response.MOVE_PAWN)) {
            int id = ((Double) response.get("id")).intValue();
            boolean inBase = ((boolean) response.get("in_base"));
            Pawn pawn = gson.fromJson( (String) response.get("pawn"), Pawn.class);
            game.movePawn(id, pawn);
            if(inBase) response.add("turn", game.getTurn());
            else response.add("turn", game.nextTurn());
            
            for (Integer key : game.gamers.keySet()) {
                servidor.send(gson.toJson(response), key);
            }
            
        }

    }

}
