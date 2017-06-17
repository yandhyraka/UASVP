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
public interface ListenerDebt {

    public void cancelDebt(JPanel panel);

    public void addDebt(DataDebt dd);

    public void editDebt(DataDebt dd);

}
