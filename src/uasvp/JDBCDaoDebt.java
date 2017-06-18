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

    public Vector<DataDebt> readByUser(DataUser user) {
        Vector<DataDebt> result = null;
        String query1 = "select * from hutang where username1 = ?";
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        try {
            pstmt1 = conn.prepareStatement(query1);
            pstmt1.setString(1, user.getUsername());
            rs1 = pstmt1.executeQuery();
            result = new Vector<DataDebt>();
            while (rs1.next()) {
                DataDebt dd = new DataDebt();
                dd.setId(rs1.getInt("id"));
                dd.setUsername1(rs1.getString("username1"));
                dd.setUsername2(rs1.getString("username2"));
                dd.setTanggal(new java.util.Date(rs1.getDate("tanggal").getTime()));
                dd.setJumlah(rs1.getInt("jumlah"));
                dd.setStatus(rs1.getInt("status"));
                result.add(dd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                    pstmt1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        String query2 = "select * from hutang where username2 = ?";
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        try {
            pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, user.getUsername());
            rs2 = pstmt2.executeQuery();
            while (rs2.next()) {
                DataDebt dd = new DataDebt();
                dd.setId(rs2.getInt("id"));
                dd.setUsername1(rs2.getString("username1"));
                dd.setUsername2(rs2.getString("username2"));
                dd.setTanggal(new java.util.Date(rs2.getDate("tanggal").getTime()));
                dd.setJumlah(rs2.getInt("jumlah"));
                dd.setStatus(rs2.getInt("status"));
                result.add(dd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs2 != null) {
                try {
                    rs2.close();
                    pstmt2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public boolean insertDebt(DataDebt debt) {
        int berhasil = 0;
        String query = "insert into hutang(`username1`, `username2`, `tanggal`, `jumlah`, `status`) values (?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, debt.getUsername1());
            pstmt.setString(2, debt.getUsername2());
            pstmt.setDate(3, new java.sql.Date(debt.getTanggal().getTime()));
            pstmt.setInt(4, debt.getJumlah());
            pstmt.setInt(5, debt.getStatus());
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateDebt(DataDebt debt) {
        int berhasil = 0;
        String query = "update hutang set `username1`=?,`username2`=?,`tanggal`=?,`jumlah`=?,`status`=? where `id`=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, debt.getUsername1());
            pstmt.setString(2, debt.getUsername2());
            pstmt.setDate(3, new java.sql.Date(debt.getTanggal().getTime()));
            pstmt.setInt(4, debt.getJumlah());
            pstmt.setInt(5, debt.getStatus());
            pstmt.setInt(6, debt.getId());
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteDebt(int id) {
        int berhasil = 0;
        String query = "delete from hutang where `id`=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            berhasil = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (berhasil > 0) {
            return true;
        } else {
            return false;
        }
    }
}
