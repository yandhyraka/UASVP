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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PanelEditFormBudget extends JPanel implements ActionListener, KeyListener {

    private JLabel title;
    private JTextField amount;
    private JButton cancel, save;
    private JComboBox month, category;
    private String[] bulan = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private Object[] budget;
    private ListenerBudget listener;
    private ModelCategory mc;

    public void addListenerBudget(ListenerBudget listener) {
        this.listener = listener;
    }

    public PanelEditFormBudget(Object[] budget, ModelCategory mc) {
        this.budget = budget;
        this.mc=mc;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Edit Budget");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        amount = new JTextField(String.valueOf(budget[1]), 25);
        cancel = new JButton("Cancel");
        save = new JButton("Save");
        month = new JComboBox(bulan);
        month.setSelectedIndex(getIdBulan(String.valueOf(budget[0])));
        category = new JComboBox(mc.getCategory());
        category.setSelectedIndex(Integer.parseInt(String.valueOf(budget[3])) - 1);
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(315, 260));
        String column = "20dlu, pref, 10dlu, 10dlu, 5dlu, 10dlu, 5dlu, 10dlu, 10dlu, 5dlu, 42dlu, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 10, "center, center"));
        this.add(new JLabel("Month"), c.xy(2, 4));
        this.add(month, c.xyw(4, 4, 8));
        this.add(new JLabel("Amount"), c.xy(2, 6));
        this.add(amount, c.xyw(4, 6, 8));
        this.add(new JLabel("Category"), c.xy(2, 8));
        this.add(category, c.xyw(4, 8, 8));
        this.add(cancel, c.xyw(2, 10, 3));
        this.add(save, c.xyw(9, 10, 3));
    }

    public int getIdBulan(String bulan) {
        for (int i = 0; i < 12; i++) {
            if (bulan.equalsIgnoreCase(this.bulan[i])) {
                return i;
            }
        }
        return -1;
    }

    public String getNamaBulan(int a) {
        return bulan[a];
    }

    public void registerListener() {
        amount.addKeyListener(this);
        cancel.addActionListener(this);
        save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(save)) {
            DataBudget db = new DataBudget();
            db.setId(Integer.parseInt(String.valueOf(budget[5])));
            db.setBulan(getNamaBulan(month.getSelectedIndex()));
            db.setJumlah(Integer.parseInt(amount.getText()));
            db.setSisa((Integer.parseInt(String.valueOf(budget[4]))) - (Integer.parseInt(String.valueOf(budget[1]))) + (Integer.parseInt(amount.getText())));
            db.setIdKategori(category.getSelectedIndex() + 1);
            listener.editBudget(db);
        }

        if (e.getSource().equals(cancel)) {
            listener.cancelBudget(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(amount)) {
            char chara = e.getKeyChar();
            if (!(((chara >= '0') && (chara <= '9') || (chara == KeyEvent.VK_BACK_SPACE) || (chara == KeyEvent.VK_DELETE)))) {
                getToolkit().beep();
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }
}
