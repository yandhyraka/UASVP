/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class JDBCDaoUser {

    private final Connection conn;

    public JDBCDaoUser() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector<DataUser> readAllUsers() {
        Vector<DataUser> result = null;
        String query = "select * from user";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            result = new Vector<DataUser>();
            while (rs.next()) {
                DataUser auser = new DataUser();
                auser.setUsername(rs.getString("username"));
                auser.setPassword(rs.getString("password"));
                auser.setNama(rs.getString("nama"));
                result.add(auser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
}
