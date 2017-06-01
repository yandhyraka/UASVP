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
public class ServiceCategory {
    private JDBCDaoCategory dao=new JDBCDaoCategory();
    
    public Vector<DataCategory> getCategory(){
        return dao.readAllCategory();
    }
}
