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
import java.util.Date;
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

    public Vector<DataIncome> readByUser(DataUser user) {
        Vector<DataIncome> result = null;
        String query = "select * from pemasukan where username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getNama());
            rs = pstmt.executeQuery();
            result = new Vector<DataIncome>();
            while (rs.next()) {
                DataIncome di = new DataIncome();
                di.setId(rs.getInt("id"));
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
    
    public boolean insertIncome(DataUser user, DataIncome income) {
        int berhasil = 0;
        String query = "insert into pemasukan (jumlah, keterangan, tanggal, username) values (?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, income.getJumlah());
            pstmt.setString(2, income.getKeterangan());
            pstmt.setDate(3, new java.sql.Date(income.getTanggal().getTime()));
            pstmt.setString(4, user.getNama());
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateIncome(DataIncome income) {
        int berhasil = 0;
        String query = "update pemasukan set `jumlah`=?,`keterangan`=?,`tanggal`=? where `id`=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, income.getJumlah());
            pstmt.setString(2, income.getKeterangan());
            pstmt.setDate(3, new java.sql.Date(income.getTanggal().getTime()));
            pstmt.setInt(4, income.getId());
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteIncome(int id) {
        int berhasil = 0;
        String query = "delete from pemasukan where `id`=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
}
