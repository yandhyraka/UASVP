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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class PanelMainExpenditure extends JPanel implements ActionListener {

    private JLabel title, balance;
    private JButton add, edit, delete;

    public PanelMainExpenditure() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Your expenditure this month");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        balance = new JLabel("Rp 000.000.000");
        balance.setFont(new Font("Arial", Font.BOLD, 28));
        add = new JButton("Add Income");
        edit = new JButton("Edit");
        delete = new JButton("Delete");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(320, 250));
        String column = "20dlu, 65dlu, 10dlu, 65dlu, 10dlu";
        String row = "15dlu, pref, pref, 50dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 3, "center, center"));
        this.add(balance, c.xyw(2, 3, 3, "center, center"));
        this.add(add, c.xyw(2, 5, 3));
        this.add(edit, c.xy(2, 7));
        this.add(delete, c.xy(4, 7));

    }

    public void registerListener() {
        add.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}