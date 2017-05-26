/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public class PanelHistory  extends JPanel implements ActionListener {

    private JLabel title;
    private JTable tabel;

    public PanelHistory() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("History");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        tabel = new JTable();
        tabel.setPreferredSize(new Dimension(250, 150));
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(320, 250));
        String column = "20dlu, pref, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 1, "center, center"));
        this.add(tabel, c.xyw(2, 4, 1, "center, center"));
    }

    public void registerListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
