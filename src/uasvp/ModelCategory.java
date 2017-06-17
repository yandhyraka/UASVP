/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.util.Vector;

/**
 *
 * @author User
 */
public class ModelCategory {

    private ServiceCategory sc = new ServiceCategory();

    public Vector<String> getCategory() {
        Vector<String> kategori = new Vector<String>();
        for (DataCategory dc : sc.getCategory()) {
            kategori.add(dc.getNama());
        }
        return kategori;
    }

    public DataCategory getCategoryById(int id) {
        return sc.getCategoryById(id);
    }
}
