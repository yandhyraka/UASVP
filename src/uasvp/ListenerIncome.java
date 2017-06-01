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
public interface ListenerIncome {

    public void cancelIncome();

    public void addIncome(DataIncome di);

    public void editIncome(DataIncome di);

    public void deleteIncome(DataIncome di);
}
