package uasvp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
public class MainFrame extends JFrame implements ActionListener {

    private JMenuBar registerMB, homeMB, defaultMB;
    private JMenu back, mainMenu, logout;
    private Vector<JPanel> historyPanel = new Vector<JPanel>();

    public void init() {//jalankan frame
        buildMenu();
        registerListener();
        this.setSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ME Budget");
        this.setContentPane(new PanelEditFormDebt());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.init();
    }

    public void registerListener() {
        back.addActionListener(this);
        mainMenu.addActionListener(this);
        logout.addActionListener(this);
    }

    private void buildMenu() {
        back = new JMenu("Back");
        mainMenu = new JMenu("Main Menu");
        logout = new JMenu("Logout");

        registerMB = new JMenuBar();
        registerMB.add(back);

        homeMB = new JMenuBar();
        homeMB.add(logout);

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

        if (e.getSource().equals(logout)) {
            PanelLogin pl = new PanelLogin();
            this.setVisible(false);
            this.setJMenuBar(new JMenuBar());
            this.setContentPane(pl);
            this.pack();
            this.setVisible(true);
        }

        if (e.getSource().equals(back)) {

        }
    }
}
