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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PanelLogin extends JPanel implements ActionListener {

    private JLabel title;
    private JTextField username;
    private JPasswordField password;
    private JButton login;
    private ListenerLogin listener;

    public void addListenerLogin(ListenerLogin listener) {
        this.listener = listener;
    }

    public PanelLogin() {
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        username = new JTextField(20);
        password = new JPasswordField(25);
        login = new JButton("Login");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(300, 210));
        String column = "20dlu, pref, 10dlu, 15dlu, 10dlu, 50dlu, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(4, 2, 4));
        this.add(new JLabel("Username"), c.xy(2, 4));
        this.add(username, c.xyw(4, 4, 4));
        this.add(new JLabel("Password"), c.xy(2, 6));
        this.add(password, c.xyw(4, 6, 4));
        this.add(login, c.xyw(6, 8, 2));
    }

    public void registerListener() {
        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(login)) {
            DataUser du = listener.attemptLogin(username.getText(), String.valueOf(password.getPassword()));
            if (du != null) {
                listener.loginSucceed(du);
            } else {
                System.out.println("FAIL");
            }
        }
    }
}
