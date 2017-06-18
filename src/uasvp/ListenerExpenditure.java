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
public interface ListenerExpenditure {

    public void cancelExpenditure(JPanel panel);

    public void addExpenditure(DataExpenditure de);

    public void selectExpenditure(Object[] expend);

    public void editExpenditure(DataExpenditure de);

    public void deleteExpenditure(int id);

    public ModelExpenditure searchDefaultExpenditure();

    public ModelExpenditure searchKeywordExpenditure(String keyword);
}
