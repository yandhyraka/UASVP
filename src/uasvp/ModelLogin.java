/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.util.Vector;

/**
 *
 * @author User
 */
public class ModelLogin {

    public DataUser checkLogin(String username, String password) {
        ServiceUser su = new ServiceUser();
        Vector<DataUser> users = su.getUsers();

        for (DataUser u : users) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    return u;
                }
            }
        }
        return null;
    }
}
