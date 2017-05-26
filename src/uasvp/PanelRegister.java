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
public class PanelRegister extends JPanel implements ActionListener {

    private JLabel title;
    private JTextField fullname, username, password;
    private JButton register;

    public PanelRegister() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Register");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        fullname = new JTextField(25);
        username = new JTextField(20);
        password = new JTextField(25);
        register = new JButton("Register");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(300, 250));
        String column = "20dlu, pref, 10dlu, 15dlu, 10dlu, 50dlu, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(3, 2, 4));
        this.add(new JLabel("Fullname"), c.xy(2, 4));
        this.add(fullname, c.xyw(4, 4, 4));
        this.add(new JLabel("Username"), c.xy(2, 6));
        this.add(username, c.xyw(4, 6, 4));
        this.add(new JLabel("Password"), c.xy(2, 8));
        this.add(password, c.xyw(4, 8, 4));
        this.add(register, c.xyw(6, 10, 2));
    }

    public void registerListener() {
        register.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
