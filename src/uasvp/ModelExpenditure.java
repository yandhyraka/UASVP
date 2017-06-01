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
public class ModelExpenditure {
    ServiceExpenditure se = new ServiceExpenditure();

    public Vector<DataExpenditure> getExpenditure(String username) {
        return se.getExpenditure(username);
    }

    public boolean InputExpenditure(DataUser user, DataExpenditure expend) {
        return se.addNewExpenditure(user, expend);
    }
}
