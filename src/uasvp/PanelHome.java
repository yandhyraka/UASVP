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
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PanelHome extends JPanel implements ActionListener {

    private ImageIcon logo;
    private JLabel label;
    private JButton register, login;
    private ListenerHome listener;

    public void addListenerHome(ListenerHome listener) {
        this.listener = listener;
    }

    public PanelHome() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        logo = new ImageIcon("Mebudget.png");
        label = new JLabel(logo);
        register = new JButton("Register");
        login = new JButton("Login");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(380, 250));
        String column = "20dlu, 75dlu, 25dlu, 75dlu, 10dlu";
        String row = "15dlu, 75dlu, 30dlu, pref";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(label, c.xyw(2, 2, 3));
        this.add(register, c.xy(2, 4));
        this.add(login, c.xy(4, 4));
    }

    public void registerListener() {
        register.addActionListener(this);
        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(register)) {
            listener.register();
        }
        if (e.getSource().equals(login)) {
            listener.login();
        }
    }
}
