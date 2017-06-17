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
public class ModelUser {

    private ServiceUser su = new ServiceUser();

    public Vector<String> getUser() {
        Vector<String> user = new Vector<String>();
        for (DataUser dc : su.getUsers()) {
            user.add(dc.getUsername());
        }
        return user;
    }

    public boolean checkRegister(String username) {
        Vector<DataUser> users = su.getUsers();

        for (DataUser u : users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean registerUser(DataUser user) {
        return su.addNewUser(user);
    }

    public DataUser checkLogin(String username, String password) {
        for (DataUser u : su.getUsers()) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    return u;
                }
            }
        }
        return null;
    }
}
