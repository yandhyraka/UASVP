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
public class MainFrame extends JFrame implements ActionListener, ListenerLogin, ListenerRegister, ListenerMainMenu, ListenerMainIncome, ListenerMainExpenditure, ListenerMainBudget, ListenerMainDebt {

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
        this.setJMenuBar(new JMenuBar());
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

    public void buildMenu() {
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

    public void changePanel(JPanel panel) {
        this.setVisible(false);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
        this.setJMenuBar(defaultMB);
    }

    //START ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu)) {
            PanelMainMenu mm = new PanelMainMenu();
            mm.addListenerMainMenu(this);
            changePanel(mm);
            this.setJMenuBar(homeMB);
        }

        if (e.getSource().equals(logout) || e.getSource().equals(logoutHome)) {
            currentUser=null;
            PanelLogin pl = new PanelLogin();
            pl.addLoginListener(this);
            changePanel(pl);
            this.setJMenuBar(new JMenuBar());
        }

        if (e.getSource().equals(back)) {

        }
    }
    //END ACTION LISTENER

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
        mm.addListenerMainMenu(this);
        changePanel(mm);
        this.setJMenuBar(homeMB);
    }

    @Override
    public void register() {
        PanelRegister pr = new PanelRegister();
        pr.addListenerRegister(this);
        changePanel(pr);
        this.setJMenuBar(registerMB);
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
        pl.addLoginListener(this);
        changePanel(pl);
        this.setJMenuBar(new JMenuBar());
    }
    //END LISTENER REGISTER

    //START LISTENER MAIN MENU
    @Override
    public void income() {
        PanelMainIncome pmi = new PanelMainIncome();
        pmi.addListenerMainIncome(this);
        changePanel(pmi);
    }

    @Override
    public void expenditure() {
        PanelMainExpenditure pme = new PanelMainExpenditure();
        pme.addListenerMainExpenditure(this);
        changePanel(pme);
    }

    @Override
    public void debt() {
        PanelMainDebt pmd = new PanelMainDebt();
        pmd.addListenerMainDebt(this);
        changePanel(pmd);
    }

    @Override
    public void budget() {
        PanelMainBudget pmb = new PanelMainBudget();
        pmb.addListenerMainBudget(this);
        changePanel(pmb);
    }

    @Override
    public void transactionHistory() {
        PanelHistory ph = new PanelHistory();
        changePanel(ph);
    }
    //END LISTENER MAIN MENU

    //START LISTENER MAIN INCOME
    @Override
    public void addIncome() {
        PanelAddIncome pai = new PanelAddIncome();
        changePanel(pai);
    }

    @Override
    public void editIncome() {
        PanelEditListIncome peli = new PanelEditListIncome();
        changePanel(peli);
    }

    @Override
    public void deleteIncome() {
        PanelDeleteIncome pdi = new PanelDeleteIncome();
        changePanel(pdi);
    }
    //END LISTENER MAIN INCOME

    //START LISTENER MAIN EXPENDITURE
    @Override
    public void addExpenditure() {
        PanelAddExpenditure pae = new PanelAddExpenditure();
        changePanel(pae);
    }

    @Override
    public void editExpenditure() {
        PanelEditListExpenditure pele = new PanelEditListExpenditure();
        changePanel(pele);
    }

    @Override
    public void deleteExpenditure() {
        PanelDeleteExpenditure pde = new PanelDeleteExpenditure();
        changePanel(pde);
    }
    //END LISTENER MAIN EXPENDITURE

    //START LISTENER MAIN BUDGET
    @Override
    public void addBudget() {
        PanelAddBudget pab = new PanelAddBudget();
        changePanel(pab);
    }

    @Override
    public void editBudget() {
        PanelEditListBudget pelb = new PanelEditListBudget();
        changePanel(pelb);
    }

    @Override
    public void deleteBudget() {
        PanelDeleteBudget pdb = new PanelDeleteBudget();
        changePanel(pdb);
    }
    //END LISTENER MAIN BUDGET

    //START LISTENER MAIN DEBT
    @Override
    public void addDebt() {
        PanelAddDebt pad = new PanelAddDebt();
        changePanel(pad);
    }

    @Override
    public void editDebt() {
        PanelEditFormDebt pefd = new PanelEditFormDebt();
        changePanel(pefd);
    }
    //END LISTENER MAIN DEBT
}
