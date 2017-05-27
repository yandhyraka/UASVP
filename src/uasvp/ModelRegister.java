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
public class ModelRegister {

    ServiceUser su = new ServiceUser();

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
}
