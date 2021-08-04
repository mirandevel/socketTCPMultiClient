/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author Usuario
 */
public class Gamer {
   String name;
   String email;
   long clientHash;

    public Gamer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public long getClientHash() {
        return clientHash;
    }

    public void setClientHash(long clientHash) {
        this.clientHash = clientHash;
    }

    
    public String getName() {
        return name;
    }

    public void setNickName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
}
