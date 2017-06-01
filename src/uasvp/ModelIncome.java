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
public class ModelIncome {

    ServiceIncome si = new ServiceIncome();

    public Vector<DataIncome> getIncome(String username) {
        return si.getIncome(username);
    }

    public boolean InputIncome(DataUser user, DataIncome income) {
        return si.addNewIncome(user, income);
    }
}
