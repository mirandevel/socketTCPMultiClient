/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import com.google.gson.Gson;
import com.mycompany.sockettcpmulticlient.Servidor.Servidor;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author Usuario
 */
public class AuthController extends Thread {

    final Servidor servidor;
    HashMap<Integer,Gamer> gamers=new HashMap<>();
    Gson gson = new Gson();

    public AuthController(Servidor servidor) {
        this.servidor = servidor;
    }
    

    void login(Response response, int clientHash) {
        String email = (String) response.get("email");
        String password = (String) response.get("password");
        boolean sw = email.compareTo("correo@gmail.com") == 0 && password.compareTo("12345678") == 0;
        
        if(sw){
        Gamer gamer=new Gamer("sin nombre",email);
        gamer.setClientHash(clientHash);
        gamers.put(clientHash,gamer);
        }
        Response response1=new Response(Response.LOGIN);
        response1.add("id", clientHash);
        response1.add("success", sw);
        servidor.send(gson.toJson(response1), clientHash);

    }

    void register(JSONObject data, int clientHash) {
        String name = data.get("name").toString();
        String email = data.get("email").toString();
        String password = data.get("password").toString();
        Gamer gamer=new Gamer(name,email);
        gamer.setClientHash(clientHash);
        gamers.put(clientHash,gamer);
        JSONObject obj = new JSONObject();
        obj.put("action", "register");
        obj.put("success", true);
        servidor.send(obj.toJSONString(), clientHash);
    }
    
    public Gamer getGamer(int hash){
        return gamers.get(hash);
    }

}
