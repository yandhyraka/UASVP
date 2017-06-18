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
public class ServiceDebt {

    private JDBCDaoDebt dao = new JDBCDaoDebt();

    public Vector<DataDebt> getDebt(DataUser user) {
        return dao.readByUser(user);
    }

    public boolean addNewDebt(DataDebt debt) {
        return dao.insertDebt(debt);
    }

    public boolean editSelectedDebt(DataDebt debt) {
        return dao.updateDebt(debt);
    }

    public boolean deleteSelectedDebt(int id) {
        return dao.deleteDebt(id);
    }
}
