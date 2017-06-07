/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

/**
 *
 * @author User
 */
public interface ListenerExpenditure {

    public void cancelExpenditure();

    public void addExpenditure(DataExpenditure de);
    
    public void selectExpenditure(Object[] expend);

    public void editExpenditure(DataExpenditure de);

    public void deleteExpenditure(DataExpenditure de);
}
