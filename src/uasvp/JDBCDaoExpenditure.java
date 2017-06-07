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
public class JDBCDaoExpenditure {

    private final Connection conn;

    public JDBCDaoExpenditure() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector<DataExpenditure> readByUser(DataUser user) {
        Vector<DataExpenditure> result = null;
        String query = "select * from pengeluaran where username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getNama());
            rs = pstmt.executeQuery();
            result = new Vector<DataExpenditure>();
            while (rs.next()) {
                DataExpenditure de = new DataExpenditure();
                de.setId(rs.getInt("id"));
                de.setJumlah(rs.getInt("jumlah"));
                de.setKeterangan(rs.getString("keterangan"));
                de.setTanggal(new java.util.Date(rs.getDate("tanggal").getTime()));
                de.setIdKategori(rs.getInt("id_kategori"));
                result.add(de);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoExpenditure.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoExpenditure.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    public boolean insertExpenditure(DataUser user, DataExpenditure expend) {
        int berhasil = 0;
        String query = "insert into pengeluaran (jumlah, keterangan, tanggal, username, id_kategori) values (?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, expend.getJumlah());
            pstmt.setString(2, expend.getKeterangan());
            pstmt.setDate(3, new java.sql.Date(expend.getTanggal().getTime()));
            pstmt.setString(4, user.getNama());
            pstmt.setInt(5, expend.getIdKategori());
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
    
    public boolean updateExpenditure(DataExpenditure expend) {
        int berhasil = 0;
        String query = "update pengeluaran set `jumlah`=?,`keterangan`=?,`tanggal`=?,`id_kategori`=? where ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, expend.getJumlah());
            pstmt.setString(2, expend.getKeterangan());
            pstmt.setDate(3, new java.sql.Date(expend.getTanggal().getTime()));
            pstmt.setInt(4, expend.getIdKategori());
            pstmt.setInt(5, expend.getId());
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
    
    public boolean deleteExpenditure(DataExpenditure expend) {
        int berhasil = 0;
        String query = "delete from pengeluaran where ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, expend.getId());
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
