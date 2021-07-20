/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import com.mycompany.sockettcpmulticlient.Servidor.Servidor;
import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author Usuario
 */
public class AuthController extends Thread {

    final Servidor servidor;
    List<Gamer> gamers=new LinkedList<>();

    public AuthController(Servidor servidor) {
        this.servidor = servidor;
    }

    void login(JSONObject data, int clientHash) {
        String email = data.get("email").toString();
        String password = data.get("password").toString();
        boolean sw = email.compareTo("correo@gmail.com") == 0 && password.compareTo("12345678") == 0;
        
        if(sw){
        Gamer gamer=new Gamer("sin nombre",email);
        gamer.setClientHash(clientHash);
        gamers.add(gamer);
        }
        JSONObject obj = new JSONObject();
        obj.put("action", "login");
        obj.put("success", sw);
        servidor.send(obj.toJSONString(), clientHash);

    }

    void register(JSONObject data, int clientHash) {
        String name = data.get("name").toString();
        String email = data.get("email").toString();
        String password = data.get("password").toString();
        Gamer gamer=new Gamer(name,email);
        gamer.setClientHash(clientHash);
        gamers.add(gamer);
        JSONObject obj = new JSONObject();
        obj.put("action", "register");
        obj.put("success", true);
        servidor.send(obj.toJSONString(), clientHash);
    }

}
