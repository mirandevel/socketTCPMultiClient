/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import com.mycompany.sockettcpmulticlient.event.MensajeListener;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AuthController {    
    boolean login(String data){
        String email=data.split(",")[0].split(":")[1];
        String password=data.split(",")[1].split(":")[1];
        return email.compareTo("correo@gmail.com")==0 && password.compareTo("12345678")==0;
            
    }
    
}
