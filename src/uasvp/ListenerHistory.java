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
public interface ListenerHistory {

    public ModelHistory searchDefaultHistory();

    public ModelHistory searchKeywordHistory(String keyword);
}
