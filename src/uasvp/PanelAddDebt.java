/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JCalendar;
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
public class PanelAddDebt extends JPanel implements ActionListener, KeyListener {

    private JLabel title;
    private JTextField amount;
    private JButton cancel, save;
    private JComboBox from, to;
    private JCalendar calendar;
    private ListenerDebt listener;
    private DataUser currentUser;
    private ModelUser mu;
    private int indexUser, totalUser;

    public void addListenerDebt(ListenerDebt listener) {
        this.listener = listener;
    }

    public PanelAddDebt(DataUser currentUser, ModelUser mu) {
        this.mu = mu;
        this.currentUser = currentUser;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Add Debt");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        amount = new JTextField(25);
        cancel = new JButton("Cancel");
        save = new JButton("Save");
        totalUser = mu.getTotalUser();
        from = new JComboBox(mu.getUser());
        to = new JComboBox(mu.getUser());
        calendar = new JCalendar();
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(390, 390));
        String column = "20dlu, 45dlu, 5dlu, 50dlu, 5dlu, 25dlu, 45dlu, 5dlu";
        String row = "15dlu, pref, 15dlu, pref, 1dlu, pref, 10dlu, pref, 20dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 7, "center, center"));
        this.add(new JLabel("Date"), c.xyw(2, 4, 1, "left, top"));
        this.add(calendar, c.xyw(4, 4, 5));
        this.add(new JLabel("From"), c.xy(2, 6));
        this.add(from, c.xy(4, 6));
        this.add(new JLabel("To"), c.xy(6, 6));
        this.add(to, c.xyw(7, 6, 2));
        this.add(new JLabel("Amount"), c.xy(2, 8));
        this.add(amount, c.xyw(4, 8, 5));
        this.add(cancel, c.xyw(2, 10, 2));
        this.add(save, c.xyw(7, 10, 2));
    }

    public void registerListener() {
        amount.addKeyListener(this);
        cancel.addActionListener(this);
        save.addActionListener(this);
        from.addActionListener(this);
        to.addActionListener(this);
    }

    public int getIndexUser() {
        indexUser = 0;
        for (String a : mu.getUser()) {
            if (a.equalsIgnoreCase(currentUser.getUsername())) {
                return indexUser;
            } else {
                indexUser++;
            }
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(from)) {
            if (from.getSelectedIndex() == getIndexUser()) {
                if (from.getSelectedIndex() == to.getSelectedIndex()) {
                    if (getIndexUser() + 1 > totalUser) {
                        to.setSelectedIndex(getIndexUser() - 1);
                    } else {
                        to.setSelectedIndex(getIndexUser() + 1);
                    }
                }
            } else {
                to.setSelectedIndex(getIndexUser());
            }
        }

        if (e.getSource().equals(to)) {
            if (to.getSelectedIndex() == getIndexUser()) {
                if (from.getSelectedIndex() == to.getSelectedIndex()) {
                    if (getIndexUser() + 1 > totalUser) {
                        from.setSelectedIndex(getIndexUser() - 1);
                    } else {
                        from.setSelectedIndex(getIndexUser() + 1);
                    }
                }
            } else {
                from.setSelectedIndex(getIndexUser());
            }
        }

        if (e.getSource().equals(save)) {
            DataDebt dd = new DataDebt();
            dd.setTanggal(calendar.getDate());
            dd.setUsername1(from.getSelectedItem().toString());
            dd.setUsername2(to.getSelectedItem().toString());
            dd.setJumlah(Integer.parseInt(amount.getText()));
            dd.setStatus(0);
            listener.addDebt(dd);
        }

        if (e.getSource().equals(cancel)) {
            listener.cancelDebt(this);
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
