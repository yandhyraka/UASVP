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
    
    public void changePanel(JPanel panel){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu)) {
            PanelMainMenu mm = new PanelMainMenu();
            this.setVisible(false);
            this.setJMenuBar(homeMB);
            this.setContentPane(mm);
            mm.addListenerMainMenu(this);
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
        changePanel(mm);
        this.setVisible(false);
        this.setJMenuBar(homeMB);
        this.setContentPane(mm);
        mm.addListenerMainMenu(this);
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

    //START LISTENER MAIN MENU
    @Override
    public void income() {
        PanelMainIncome pmi = new PanelMainIncome();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pmi);
        pmi.addListenerMainIncome(this);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void expenditure() {
        PanelMainExpenditure pme = new PanelMainExpenditure();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pme);
        pme.addListenerMainExpenditure(this);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void debt() {
        PanelMainDebt pmd = new PanelMainDebt();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pmd);
        pmd.addListenerMainDebt(this);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void budget() {
        PanelMainBudget pmb = new PanelMainBudget();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pmb);
        pmb.addListenerMainBudget(this);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void transactionHistory() {
        PanelHistory ph = new PanelHistory();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(ph);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER MAIN MENU

    //START LISTENER MAIN INCOME
    @Override
    public void addIncome() {
        PanelAddIncome pai = new PanelAddIncome();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pai);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void editIncome() {
        PanelEditListIncome peli = new PanelEditListIncome();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(peli);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void deleteIncome() {
        PanelDeleteIncome pdi = new PanelDeleteIncome();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pdi);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER MAIN INCOME

    //START LISTENER MAIN EXPENDITURE
    @Override
    public void addExpenditure() {
        PanelAddExpenditure pae = new PanelAddExpenditure();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pae);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void editExpenditure() {
        PanelEditListExpenditure pele = new PanelEditListExpenditure();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pele);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void deleteExpenditure() {
        PanelDeleteExpenditure pde = new PanelDeleteExpenditure();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pde);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER MAIN EXPENDITURE

    //START LISTENER MAIN BUDGET
    @Override
    public void addBudget() {
        PanelAddBudget pab = new PanelAddBudget();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pab);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void editBudget() {
        PanelEditListBudget pelb = new PanelEditListBudget();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pelb);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void deleteBudget() {
        PanelDeleteBudget pdb = new PanelDeleteBudget();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pdb);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER MAIN BUDGET

    //START LISTENER MAIN DEBT
    @Override
    public void addDebt() {
        PanelAddDebt pad = new PanelAddDebt();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pad);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void editDebt() {
        PanelEditFormDebt pefd = new PanelEditFormDebt();
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(pefd);
        this.pack();
        this.setVisible(true);
    }
    //END LISTENER MAIN DEBT
}
