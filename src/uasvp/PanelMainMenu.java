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
public class PanelMainMenu extends JPanel implements ActionListener {

    private JLabel title, balance;
    private JButton income, expenditure, debt, budget, history;

    public PanelMainMenu() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Balance");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        balance = new JLabel("Rp 000.000.000");
        balance.setFont(new Font("Arial", Font.BOLD, 28));
        income = new JButton("Income");
        expenditure = new JButton("Expenditure");
        debt = new JButton("Debt");
        budget = new JButton("Budget");
        history = new JButton("Transaction History");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(320, 250));
        String column = "20dlu, 65dlu, 10dlu, 65dlu, 10dlu";
        String row = "15dlu, pref, pref, 15dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 3, "center, center"));
        this.add(balance, c.xyw(2, 3, 3, "center, center"));
        this.add(income, c.xy(2, 5));
        this.add(expenditure, c.xy(4, 5));
        this.add(debt, c.xy(2, 7));
        this.add(budget, c.xy(4, 7));
        this.add(history, c.xyw(2, 9, 3));

    }

    public void registerListener() {
        income.addActionListener(this);
        expenditure.addActionListener(this);
        debt.addActionListener(this);
        budget.addActionListener(this);
        history.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
