package uasvp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class MainFrame extends JFrame implements ActionListener, ListenerHome, ListenerLogin, ListenerRegister, ListenerMainMenu, ListenerMainIncome, ListenerMainExpenditure, ListenerMainBudget, ListenerMainDebt, ListenerIncome, ListenerExpenditure, ListenerBudget, ListenerDebt, ListenerHistory {

    private JMenuBar registerLoginMB, homeMB, defaultMB;
    private JMenuItem back, mainMenu, logout, logoutHome;
    private DataUser currentUser;
    private String month;

    private PanelMainBudget pmb;
    private PanelMainDebt pmd;
    private PanelMainExpenditure pme;
    private PanelMainIncome pmi;
    private PanelMainMenu pmm;

    private ModelBudget mb;
    private ModelDebt md;
    private ModelExpenditure me;
    private ModelIncome mi;
    private ModelMainMenu mmm;
    private ModelUser mu;
    private ModelCategory mc;

    private static MainFrame m;

    public static void main(String[] args) {
        m = new MainFrame();
        m.init();
    }

    public void init() {//jalankan frame
        month = new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime());
        mu = new ModelUser();
        mc = new ModelCategory();

        buildMenu();
        registerListener();
        this.setSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ME Budget");
//        this.setContentPane(new PanelHome());
        this.setJMenuBar(new JMenuBar());
        PanelHome ph = new PanelHome();
        this.setContentPane(ph);
        ph.addListenerHome(this);
        this.pack();
        this.setVisible(true);
    }

    public void initMainBudget() {
        mb = new ModelBudget(currentUser);
        me = new ModelExpenditure(currentUser);
        pmb = new PanelMainBudget(month, mb, me);
        pmb.addListenerMainBudget(this);
    }

    public void initMainDebt() {
        md = new ModelDebt(currentUser);
        pmd = new PanelMainDebt(currentUser, md);
        pmd.addListenerMainDebt(this);
    }

    public void initMainExpenditure() {
        me = new ModelExpenditure(currentUser);
        pme = new PanelMainExpenditure(month, me);
        pme.addListenerMainExpenditure(this);
    }

    public void initMainIncome() {
        mi = new ModelIncome(currentUser);
        pmi = new PanelMainIncome(month, mi);
        pmi.addListenerMainIncome(this);
    }

    public void initMainMenu() {
        mmm = new ModelMainMenu(currentUser);
        pmm = new PanelMainMenu(mmm);
        pmm.addListenerMainMenu(this);
    }

    public void registerListener() {
        back.addActionListener(this);
        mainMenu.addActionListener(this);
        logout.addActionListener(this);
        logoutHome.addActionListener(this);
    }

    public void buildMenu() {
        back = new JMenuItem("Back");
        mainMenu = new JMenuItem("Main Menu");
        logout = new JMenuItem("Logout");
        logoutHome = new JMenuItem("Logout");

        registerLoginMB = new JMenuBar();
        registerLoginMB.add(back);

        homeMB = new JMenuBar();
        homeMB.add(logoutHome);

        defaultMB = new JMenuBar();
        defaultMB.add(mainMenu);
        defaultMB.add(logout);
    }

    public void changePanel(JPanel panel) {
        this.setVisible(false);
        this.setJMenuBar(defaultMB);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }

    //START ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu)) {
            initMainMenu();
            changePanel(pmm);
            this.setJMenuBar(homeMB);
        }

        if (e.getSource().equals(logout) || e.getSource().equals(logoutHome) || e.getSource().equals(back)) {
            currentUser = null;
            PanelHome ph = new PanelHome();
            ph.addListenerHome(this);
            changePanel(ph);
            this.setJMenuBar(new JMenuBar());
        }
    }
    //END ACTION LISTENER

    //START LISTENER HOME
    @Override
    public void register() {
        PanelRegister pr = new PanelRegister();
        pr.addListenerRegister(this);
        changePanel(pr);
        this.setJMenuBar(registerLoginMB);
    }

    @Override
    public void login() {
        PanelLogin pl = new PanelLogin();
        pl.addListenerLogin(this);
        changePanel(pl);
        this.setJMenuBar(registerLoginMB);
    }
    // END LISTENER HOME

    //START LISTENER LOGIN
    @Override
    public DataUser attemptLogin(String username, String password) {
        DataUser du = mu.checkLogin(username, password);
        return du;
    }

    @Override
    public void loginSucceed(DataUser user) {
        currentUser = user;
        initMainMenu();
        changePanel(pmm);
        this.setJMenuBar(homeMB);
    }
    //END LISTENER LOGIN

    //START LISTENER REGISTER
    @Override
    public boolean checkUsername(String username) {
        boolean b = mu.checkRegister(username);
        return b;
    }

    @Override
    public void registerSucceed(DataUser user) {
        mu.registerUser(user);
        PanelLogin pl = new PanelLogin();
        pl.addListenerLogin(this);
        changePanel(pl);
        this.setJMenuBar(new JMenuBar());
    }
    //END LISTENER REGISTER

    //START LISTENER MAIN MENU
    @Override
    public void income() {
        initMainIncome();
        changePanel(pmi);
    }

    @Override
    public void expenditure() {
        initMainExpenditure();
        changePanel(pme);
    }

    @Override
    public void debt() {
        initMainDebt();
        changePanel(pmd);
    }

    @Override
    public void budget() {
        initMainBudget();
        changePanel(pmb);
    }

    @Override
    public void transactionHistory() {
        ModelHistory mh = new ModelHistory(currentUser);
        PanelHistory ph = new PanelHistory(currentUser, mh);
        ph.addListenerHistory(this);
        changePanel(ph);
    }
    //END LISTENER MAIN MENU

    //START LISTENER MAIN INCOME
    @Override
    public void addIncome() {
        PanelAddIncome pai = new PanelAddIncome();
        pai.addListenerIncome(this);
        changePanel(pai);
    }

    @Override
    public void editIncome() {
        ModelIncome mi = new ModelIncome(currentUser);
        PanelEditListIncome peli = new PanelEditListIncome(currentUser, mi);
        peli.addListenerIncome(this);
        changePanel(peli);
    }

    @Override
    public void deleteIncome() {
        ModelIncome mi = new ModelIncome(currentUser);
        PanelDeleteIncome pdi = new PanelDeleteIncome(currentUser, mi);
        pdi.addListenerIncome(this);
        changePanel(pdi);
    }
    //END LISTENER MAIN INCOME

    //START LISTENER MAIN EXPENDITURE
    @Override
    public void addExpenditure() {
        PanelAddExpenditure pae = new PanelAddExpenditure(mc);
        pae.addListenerExpenditure(this);
        changePanel(pae);
    }

    @Override
    public void editExpenditure() {
        ModelExpenditure me = new ModelExpenditure(currentUser);
        PanelEditListExpenditure pele = new PanelEditListExpenditure(currentUser, me);
        pele.addListenerExpenditure(this);
        changePanel(pele);
    }

    @Override
    public void deleteExpenditure() {
        ModelExpenditure me = new ModelExpenditure(currentUser);
        PanelDeleteExpenditure pde = new PanelDeleteExpenditure(currentUser, me);
        pde.addListenerExpenditure(this);
        changePanel(pde);
    }
    //END LISTENER MAIN EXPENDITURE

    //START LISTENER MAIN BUDGET
    @Override
    public void addBudget() {
        PanelAddBudget pab = new PanelAddBudget(mc);
        pab.addListenerBudget(this);
        changePanel(pab);
    }

    @Override
    public void editBudget() {
        ModelBudget mb = new ModelBudget(currentUser);
        PanelEditListBudget pelb = new PanelEditListBudget(currentUser, mb);
        pelb.addListenerBudget(this);
        changePanel(pelb);
    }

    @Override
    public void deleteBudget() {
        mb = new ModelBudget(currentUser);
        PanelDeleteBudget pdb = new PanelDeleteBudget(currentUser, mb);
        pdb.addListenerBudget(this);
        changePanel(pdb);
    }
    //END LISTENER MAIN BUDGET

    //START LISTENER MAIN DEBT
    @Override
    public void addDebt() {
        PanelAddDebt pad = new PanelAddDebt(currentUser, mu);
        pad.addListenerDebt(this);
        changePanel(pad);
    }

    @Override
    public void editDebt(Object[] debt) {
        PanelEditFormDebt pefd = new PanelEditFormDebt(debt);
        pefd.addListenerDebt(this);
        changePanel(pefd);
    }

    @Override
    public void deleteDebt(int id) {
        md.deleteDebt(id);
        initMainDebt();
        changePanel(pmd);
    }
    //END LISTENER MAIN DEBT

    //START LISTENER INCOME
    @Override
    public void cancelIncome(JPanel panel) {
        if (panel instanceof PanelEditFormIncome) {
            ModelIncome mi = new ModelIncome(currentUser);
            PanelEditListIncome peli = new PanelEditListIncome(currentUser, mi);
            peli.addListenerIncome(this);
            changePanel(peli);
        } else {
            changePanel(pmi);
        }
    }

    @Override
    public void addIncome(DataIncome di) {
        mi.inputIncome(currentUser, di);
        initMainIncome();
        changePanel(pmi);
    }

    @Override
    public void selectIncome(Object[] income) {
        PanelEditFormIncome pefi = new PanelEditFormIncome(income);
        pefi.addListenerIncome(this);
        changePanel(pefi);
    }

    @Override
    public void editIncome(DataIncome di) {
        mi.editIncome(di);
        initMainIncome();
        changePanel(pmi);
    }

    @Override
    public void deleteIncome(int id) {
        mi.deleteIncome(id);
        initMainIncome();
        changePanel(pmi);
    }

    @Override
    public ModelIncome searchDefaultIncome() {
        ModelIncome mi = new ModelIncome(currentUser);
        return mi;
    }

    @Override
    public ModelIncome searchKeywordIncome(String keyword) {
        ModelIncome mi = new ModelIncome(currentUser);
        mi.searchIncome(keyword);
        return mi;
    }
    //END LISTENER INCOME

    //START LISTENER EXPENDITURE
    @Override
    public void cancelExpenditure(JPanel panel) {
        if (panel instanceof PanelEditFormExpenditure) {
            ModelExpenditure me = new ModelExpenditure(currentUser);
            PanelEditListExpenditure pele = new PanelEditListExpenditure(currentUser, me);
            pele.addListenerExpenditure(this);
            changePanel(pele);
        } else {
            changePanel(pme);
        }
    }

    @Override
    public void addExpenditure(DataExpenditure de) {
        me.inputExpenditure(currentUser, de);
        initMainExpenditure();
        changePanel(pme);
    }

    @Override
    public void selectExpenditure(Object[] expend) {
        PanelEditFormExpenditure pefe = new PanelEditFormExpenditure(expend, mc);
        pefe.addListenerExpenditure(this);
        changePanel(pefe);
    }

    @Override
    public void editExpenditure(DataExpenditure de) {
        me.editExpenditure(de);
        initMainExpenditure();
        changePanel(pme);
    }

    @Override
    public void deleteExpenditure(int id) {
        me.deleteExpenditure(id);
        initMainExpenditure();
        changePanel(pme);
    }

    @Override
    public ModelExpenditure searchDefaultExpenditure() {
        ModelExpenditure me = new ModelExpenditure(currentUser);
        return me;
    }

    @Override
    public ModelExpenditure searchKeywordExpenditure(String keyword) {
        ModelExpenditure me = new ModelExpenditure(currentUser);
        me.searchExpenditure(keyword);
        return me;
    }
    //END LISTENER EXPENDITURE

    //START LISTENER BUDGET
    @Override
    public void cancelBudget(JPanel panel) {
        if (panel instanceof PanelEditFormBudget) {
            ModelBudget mb = new ModelBudget(currentUser);
            PanelEditListBudget pelb = new PanelEditListBudget(currentUser, mb);
            pelb.addListenerBudget(this);
            changePanel(pelb);
        } else {
            changePanel(pmb);
        }
    }

    @Override
    public void addBudget(DataBudget db) {
        mb.inputBudget(currentUser, db);
        initMainBudget();
        changePanel(pmb);
    }

    @Override
    public void selectBudget(Object[] budget) {
        PanelEditFormBudget pefb = new PanelEditFormBudget(budget, mc);
        pefb.addListenerBudget(this);
        changePanel(pefb);
    }

    @Override
    public void editBudget(DataBudget db) {
        mb.editBudget(db);
        initMainBudget();
        changePanel(pmb);
    }

    @Override
    public void deleteBudget(int id) {
        mb.deleteBudget(id);
        initMainBudget();
        changePanel(pmb);
    }
    //END LISTENER BUDGET

    //START LISTENER DEBT
    @Override
    public void cancelDebt(JPanel panel) {
        changePanel(pmd);
    }

    @Override
    public void addDebt(DataDebt dd) {
        md.inputDebt(dd);
        initMainDebt();
        changePanel(pmd);
    }

    @Override
    public void editDebt(DataDebt dd) {
        md.editDebt(dd);
        initMainDebt();
        changePanel(pmd);
    }
    //END LISTENER DEBT    

    //START LISTENER HISTORY
    @Override
    public ModelHistory searchDefaultHistory() {
        ModelHistory mh = new ModelHistory(currentUser);
        return mh;
    }

    @Override
    public ModelHistory searchKeywordHistory(String keyword) {
        ModelHistory mh = new ModelHistory(currentUser);
        mh.searchHistory(keyword);
        return mh;
    }
    //END LISTENER HISTORY

}
