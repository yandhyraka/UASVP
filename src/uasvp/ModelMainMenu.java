/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author User
 */
public class ModelMainMenu {

    private ServiceExpenditure se = new ServiceExpenditure();
    private ServiceIncome si = new ServiceIncome();
    private Vector<DataExpenditure> datae;
    private Vector<DataIncome> datai;
    private int totalIncome, totalExpenditure;

    public ModelMainMenu(DataUser user) {
        datae = se.getExpenditure(user);
        datai = si.getIncome(user);
    }

    public int getAllTimeExpenditure() {
        totalExpenditure = 0;
        for (DataExpenditure a : datae) {
            totalExpenditure = totalExpenditure + a.getJumlah();
        }
        return totalExpenditure;
    }

    public int getAllTimeIncome() {
        totalIncome = 0;
        for (DataIncome a : datai) {
            totalIncome = totalIncome + a.getJumlah();
        }
        return totalIncome;
    }

    public int getBalance() {
        return getAllTimeIncome() - getAllTimeExpenditure();
    }
}
