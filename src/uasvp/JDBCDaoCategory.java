/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class JDBCDaoCategory {

    private final Connection conn;

    public JDBCDaoCategory() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector<DataCategory> readAllCategory() {
        Vector<DataCategory> result = null;
        String query = "select * from kategori";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            result = new Vector<DataCategory>();
            while (rs.next()) {
                DataCategory dc = new DataCategory();
                dc.setId(rs.getInt("id"));
                dc.setNama(rs.getString("nama"));
                result.add(dc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoCategory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoCategory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public DataCategory readCategoryById(int id) {
        DataCategory result = null;
        String query = "select * from kategori where id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            result = new DataCategory();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setNama(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoCategory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoCategory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

}
