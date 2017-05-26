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
public class ServiceUser {
    private JDBCDaoUser dao = new JDBCDaoUser();

    public Vector<DataUser> getUsers() {
        return dao.readAllUsers();
    }

//    public boolean addNewUser(User user) {
//        return dao.insertUser(user);
//    }
//    
//    public User getUserbyEmail(String email){
//        return dao.readByEmail(email);
//    }
}
