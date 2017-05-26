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
public class JDBCDaoIncome {

    private final Connection conn;

    public JDBCDaoIncome() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector<DataIncome> readByUserId(String username) {
        Vector<DataIncome> result = null;
        String query = "select * from pemasukan where username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            result = new Vector<DataIncome>();
            while (rs.next()) {
                DataIncome di = new DataIncome();
                di.setJumlah(rs.getInt("jumlah"));
                di.setKeterangan(rs.getString("keterangan"));
                di.setTanggal(new java.util.Date(rs.getDate("tanggal").getTime()));
                result.add(di);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoIncome.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoIncome.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
}
