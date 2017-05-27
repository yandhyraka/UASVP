/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

/**
 *
 * @author User
 */
public interface ListenerLogin {

    public DataUser attemptLogin(String username, String password);

    public void loginSucceed(DataUser user);
    
    public void register();
}
