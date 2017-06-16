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
public class ServiceBudget {

    private JDBCDaoBudget dao = new JDBCDaoBudget();

    public Vector<DataBudget> getBudget(DataUser user) {
        return dao.readByUser(user);
    }

    public boolean addNewBudget(DataUser user, DataBudget budget) {
        return dao.insertBudget(user, budget);
    }

    public boolean editSelectedBudget(DataBudget budget) {
        return dao.updateBudget(budget);
    }

    public boolean deleteSelectedBudget(int id) {
        return dao.deleteBudget(id);
    }
}
