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

    public Vector<DataBudget> readByUser(DataUser user) {
        Vector<DataBudget> result = null;
        String query = "select * from budget where username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
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
    
    public boolean insertBudget(DataUser user, DataBudget budget) {
        int berhasil = 0;
        String query = "insert into budget (`jumlah`, `sisa`, `bulan`, `username`, `id_kategori`) values (?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, budget.getJumlah());
            pstmt.setInt(2, budget.getSisa());
            pstmt.setString(3, budget.getBulan());
            pstmt.setString(4, user.getUsername());
            pstmt.setInt(5, budget.getIdKategori());
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoBudget.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateBudget(DataBudget budget) {
        int berhasil = 0;
        String query = "update budget set `jumlah`=?,`sisa`=?,`bulan`=?,`id_kategori`=? where `id`=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, budget.getJumlah());
            pstmt.setInt(2, budget.getSisa());
            pstmt.setString(3, budget.getBulan());
            pstmt.setInt(4, budget.getIdKategori());
            pstmt.setInt(5, budget.getId());
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoBudget.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteBudget(int id) {
        int berhasil = 0;
        String query = "delete from budget where `id`=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoBudget.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
}
