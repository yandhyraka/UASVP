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
public class ServiceIncome {
    private JDBCDaoIncome dao=new JDBCDaoIncome();
    
    public Vector<DataIncome> getIncome(String username) {
        return dao.readByUserId(username);
    }

    public boolean addNewIncome(DataUser user, DataIncome income) {
        return dao.insertIncome(user, income);
    }
}
