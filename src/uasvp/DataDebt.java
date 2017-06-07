/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.util.Date;

/**
 *
 * @author User
 */
public class DataDebt {

    private String usernamePenghutang;
    private Date tanggal;
    private int id, jumlah , status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernamePenghutang() {
        return usernamePenghutang;
    }

    public void setUsernamePenghutang(String usernamePenghutang) {
        this.usernamePenghutang = usernamePenghutang;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
