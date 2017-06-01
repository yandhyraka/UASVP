/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PanelAddExpenditure extends JPanel implements ActionListener {

    private JLabel title;
    private JTextField desc, amount;
    private JButton cancel, save;
    private JComboBox category;
    private JCalendar calendar;

    public PanelAddExpenditure() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Add Expenditure");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        desc = new JTextField(25);
        amount = new JTextField(25);
        cancel = new JButton("Cancel");
        save = new JButton("Save");
        category = new JComboBox();
        calendar = new JCalendar();
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(390, 430));
        String column = "20dlu, 45dlu, 5dlu, 80dlu, 45dlu, 5dlu";
        String row = "15dlu, pref, 15dlu, pref, 1dlu, pref, 10dlu, pref, 10dlu, pref, 20dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 5, "center, center"));
        this.add(new JLabel("Date"), c.xyw(2, 4, 1, "left, top"));
        this.add(calendar, c.xyw(4, 4, 3));
        this.add(new JLabel("Description"), c.xy(2, 6));
        this.add(desc, c.xyw(4, 6, 3));
        this.add(new JLabel("Amount"), c.xy(2, 8));
        this.add(amount, c.xyw(4, 8, 3));
        this.add(new JLabel("Category"), c.xy(2, 10));
        this.add(category, c.xyw(4, 10, 3));
        this.add(cancel, c.xyw(2, 12, 2));
        this.add(save, c.xyw(5, 12, 2));
    }

    public void registerListener() {
        cancel.addActionListener(this);
        save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
