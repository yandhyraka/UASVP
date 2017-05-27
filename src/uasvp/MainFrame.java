package uasvp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class MainFrame extends JFrame implements ActionListener, ListenerLogin, ListenerRegister {

    private JMenuBar registerMB, homeMB, defaultMB;
    private JMenuItem back, backReg, mainMenu, logout, logoutHome;
    private Vector<JPanel> historyPanel = new Vector<JPanel>();
    private DataUser currentUser;

    public void init() {//jalankan frame
        buildMenu();
        registerListener();
        this.setSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ME Budget");
        this.setJMenuBar(homeMB);
        PanelLogin pl = new PanelLogin();
        this.setContentPane(pl);
        pl.addLoginListener(this);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.init();
    }

    public void registerListener() {
        back.addActionListener(this);
        backReg.addActionListener(this);
        mainMenu.addActionListener(this);
        logout.addActionListener(this);
        logoutHome.addActionListener(this);
    }

    private void buildMenu() {
        back = new JMenuItem("Back");
        backReg = new JMenuItem("Back");
        mainMenu = new JMenuItem("Main Menu");
        logout = new JMenuItem("Logout");
        logoutHome = new JMenuItem("Logout");

        registerMB = new JMenuBar();
        registerMB.add(backReg);

        homeMB = new JMenuBar();
        homeMB.add(logoutHome);

        defaultMB = new JMenuBar();
        defaultMB.add(back);
        defaultMB.add(mainMenu);
        defaultMB.add(logout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu)) {
            PanelMainMenu mm = new PanelMainMenu();
            this.setVisible(false);
            this.setJMenuBar(homeMB);
            this.setContentPane(mm);
            this.pack();
            this.setVisible(true);
        }

        if (e.getSource().equals(logout) || e.getSource().equals(logoutHome)) {
            PanelLogin pl = new PanelLogin();
            this.setVisible(false);
            this.setJMenuBar(new JMenuBar());
            this.setContentPane(pl);
            pl.addLoginListener(this);
            this.pack();
            this.setVisible(true);
        }

        if (e.getSource().equals(back)) {

        }
    }

    //START LISTENER LOGIN
    @Override
    public DataUser attemptLogin(String username, String password) {
        ModelLogin ml = new ModelLogin();
        DataUser du = ml.checkLogin(username, password);
        return du;
    }

    @Override
    public void loginSucceed(DataUser user) {
        currentUser = user;
        PanelMainMenu mm = new PanelMainMenu();
        this.setVisible(false);
        this.setJMenuBar(homeMB);
        this.setContentPane(mm);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void register() {
        PanelRegister pr = new PanelRegister();
        this.setVisible(false);
        this.setJMenuBar(registerMB);
        this.setContentPane(pr);
        pr.addListenerRegister(this);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER LOGIN

    //START LISTENER REGISTER
    @Override
    public boolean checkUsername(String username) {
        ModelRegister mr = new ModelRegister();
        boolean b = mr.checkRegister(username);
        return b;
    }

    @Override
    public void registerSucceed(DataUser user) {
        ModelRegister mr = new ModelRegister();
        mr.registerUser(user);

        PanelLogin pl = new PanelLogin();
        this.setVisible(false);
        this.setJMenuBar(new JMenuBar());
        this.setContentPane(pl);
        pl.addLoginListener(this);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER REGISTER
}
