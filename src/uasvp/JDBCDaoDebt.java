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
public class JDBCDaoDebt {

    private final Connection conn;

    public JDBCDaoDebt() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector<DataDebt> readByUserId(String username) {
        Vector<DataDebt> result = null;
        String query = "select * from hutang where username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            result = new Vector<DataDebt>();
            while (rs.next()) {
                DataDebt dd = new DataDebt();
                dd.setUsernamePenghutang(rs.getString("username2"));
                dd.setTanggal(new java.util.Date(rs.getDate("tanggal").getTime()));
                dd.setJumlah(rs.getInt("jumlah"));
                dd.setStatus(rs.getInt("status"));
                result.add(dd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
}
