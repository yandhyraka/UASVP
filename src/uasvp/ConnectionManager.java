/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ConnectionManager {

    //agar objek dari connection manager cuma 1 dibuatlah konstanta dari kelas ini
    private static ConnectionManager myInstance = new ConnectionManager();
    private Connection conn;

    public static ConnectionManager getInstance() {
        return myInstance;
    }

    private ConnectionManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mebudget", "root", "");
        } catch (InstantiationException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }

//    public static void main(String[] args) {
//        ConnectionManager c = new ConnectionManager();
//        if (c.getConnection() != null) {
//            System.out.println("YEEEY");
//        } else {
//            System.out.println("NOOOOOOOOO");
//        }
//    }
}
