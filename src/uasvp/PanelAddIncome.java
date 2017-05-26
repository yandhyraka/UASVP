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
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PanelAddIncome extends JPanel implements ActionListener {

    private JLabel title;
    private JTextField day, month, year, desc, amount;
    private JButton today, cancel, save;

    public PanelAddIncome() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Add Income");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        day = new JTextField(2);
        month = new JTextField(2);
        year = new JTextField(4);
        desc = new JTextField(25);
        amount = new JTextField(25);
        today = new JButton("Today");
        cancel = new JButton("Cancel");
        save = new JButton("Save");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(325, 250));
        String column = "20dlu, pref, 10dlu, 10dlu, 5dlu, 10dlu, 5dlu, 10dlu, 10dlu, 5dlu, 42dlu, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 10, "center, center"));
        this.add(new JLabel("Date"), c.xy(2, 4));
        this.add(day, c.xy(4, 4));
        this.add(month, c.xy(6, 4));
        this.add(year, c.xyw(8, 4, 2));
        this.add(today, c.xy(11, 4));
        this.add(new JLabel("Description"), c.xy(2, 6));
        this.add(desc, c.xyw(4, 6, 8));
        this.add(new JLabel("Amount"), c.xy(2, 8));
        this.add(amount, c.xyw(4, 8, 8));
        this.add(cancel, c.xyw(2, 10, 3));
        this.add(save, c.xyw(9, 10, 3));
    }

    public void registerListener() {
        today.addActionListener(this);
        cancel.addActionListener(this);
        save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
