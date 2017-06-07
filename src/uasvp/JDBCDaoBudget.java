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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class JDBCDaoBudget {

    private final Connection conn;

    public JDBCDaoBudget() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector<DataBudget> readByUserId(String username) {
        Vector<DataBudget> result = null;
        String query = "select * from budget where username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            result = new Vector<DataBudget>();
            while (rs.next()) {
                DataBudget db = new DataBudget();
                db.setId(rs.getInt("id"));
                db.setJumlah(rs.getInt("jumlah"));
                db.setSisa(rs.getInt("sisa"));
                db.setBulan(rs.getString("bulan"));
                db.setIdKategori(rs.getInt("id_kategori"));
                result.add(db);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoBudget.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoBudget.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
}
