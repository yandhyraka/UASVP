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
public interface ListenerIncome {

    public void cancelIncome(JPanel panel);

    public void addIncome(DataIncome di);
    
    public void selectIncome(Object[] income);

    public void editIncome(DataIncome di);

    public void deleteIncome(int id);
}
