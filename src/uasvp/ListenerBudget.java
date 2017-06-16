/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import javax.swing.JPanel;

/**
 *
 * @author User
 */
public interface ListenerBudget {

    public void cancelBudget(JPanel panel);

    public void addBudget(DataBudget db);

    public void selectBudget(Object[] budget);

    public void editBudget(DataBudget db);

    public void deleteBudget(int id);
}
