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
public class ServiceExpenditure {
    private JDBCDaoExpenditure dao=new JDBCDaoExpenditure();
    
    public Vector<DataExpenditure> getExpenditure(String username) {
        return dao.readByUserId(username);
    }

    public boolean addNewExpenditure(DataUser user, DataExpenditure expend) {
        return dao.insertExpenditure(user, expend);
    }
}
